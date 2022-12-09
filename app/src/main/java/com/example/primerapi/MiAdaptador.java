package com.example.primerapi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MyViewHolder> {
    ArrayList<Farmacia> lista;

    public MiAdaptador(ArrayList<Farmacia> lista) {
        this.lista = lista;
    }

    //Creamos la clase MyViewHolder que usaremos dentro de nuestro adaptador personalizado.
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtFarmacia;
        ImageView img;

        //Configuramos el holder con los elementos inflados.
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFarmacia = itemView.findViewById(R.id.txtFarmacia);
            img = itemView.findViewById(R.id.imageView);

            //Programamos el evento que sucede cuando pulsa:
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder ab = new AlertDialog.Builder(itemView.getContext());
                    ab.setTitle("Borrar empleado");
                    ab.setMessage("¿Seguro que desea borrar el empleado " + txtFarmacia.getText().toString() + "?");
                    ab.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    });
                    ab.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(itemView.getContext(), "Empleado " + txtFarmacia.getText().toString() + " no borrado.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    ab.show();
                    return false;
                }
            });


            //Importante que este evento esté en el click listener corto
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {


                }
            });
        }
    }

    //Creamos un elemento.xml en layout y después hacemos click derecho a constraint layout y cambiamos a linear layout.
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Hacemos que el adaptador infle a partir de elemento.xml tantas veces como diga el método getItemCount.
        LayoutInflater inflador = LayoutInflater.from(parent.getContext()); // es el this de activityLista
        View v = inflador.inflate(R.layout.elemento, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    //Este es el código que se realiza cada una de las iteraciones.
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Farmacia f = lista.get(position);
        holder.txtFarmacia.setText(f.getResumen());
        if (f.getTurno().equals("14-B")){
            holder.img.setImageDrawable(holder.itemView.getResources().getDrawable(R.mipmap.ic_verde_round));
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}