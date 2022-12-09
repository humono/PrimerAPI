package com.example.primerapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //     Peticion p = new Peticion();         // Creamos un hilo Petición para poder recuperar la información de una API sin parar el Activity
        //     p.start();


        //Para añadir una imagen, click derecho en app, new Image Asset


        ArrayList<Farmacia> lista = new ArrayList<>();

        PeticionEjercicio pe = new PeticionEjercicio(lista);
        pe.start();

        RecyclerView rv = findViewById(R.id.listaFarmacias);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        try {
            pe.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        MiAdaptador adaptador = new MiAdaptador(lista);
        rv.setAdapter(adaptador);
    }
}