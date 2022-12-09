package com.example.primerapi;

import android.util.JsonReader;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class PeticionEjercicio extends Thread {
    public ArrayList<Farmacia> lista;

    public PeticionEjercicio(ArrayList<Farmacia> lista) {
        this.lista = lista;
    }

    @Override
    public void run() {
        super.run();

        try {
            URL url = new URL("https://www.zaragoza.es/sede/servicio/farmacia?rf=html&srsname=wgs84&tipo=guardia&start=0&rows=50&distance=500");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/geo+json");
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                //.v("Json", isr.toString());
                JsonReader jr = new JsonReader(isr);
                //Log.v("json",jr.toString());
                jr.beginObject();
                while (jr.hasNext()) {
                    String clave = jr.nextName();
                    if (clave.equals("features")) {
                        jr.beginArray();
                        while (jr.hasNext()) {
                            Farmacia f = new Farmacia();
                            jr.beginObject();
                            while (jr.hasNext()) {
                                String clave2 = jr.nextName();
                                if (clave2.equals("properties")) {
                                    jr.beginObject();
                                    while (jr.hasNext()) {
                                        String clave3 = jr.nextName();
                                        if (clave3.equals("title")) {
                                            String titulo = jr.nextString();
                                            Log.v("Nombre de farmacia", titulo);
                                            f.setNombre(titulo);
                                        } else if (clave3.equals("guardia")) {
                                            jr.beginObject();
                                            while (jr.hasNext()) {
                                                String clave4 = jr.nextName();
                                                if (clave4.equals("fecha")) {
                                                    String valor = jr.nextString();
                                                    f.setFecha(valor);
                                                    Log.v("Fecha", valor);
                                                } else if (clave4.equals("turno")) {
                                                    String valor = jr.nextString();
                                                    f.setTurno(valor);
                                                    Log.v("Turno", valor);
                                                } else if (clave4.equals("horario")) {
                                                    String valor = jr.nextString();
                                                    f.setHorario(valor);
                                                    Log.v("Horario", valor);
                                                } else if (clave4.equals("sector")) {
                                                    String valor = jr.nextString();
                                                    f.setSector(valor);
                                                    Log.v("Sector", valor);
                                                } else {
                                                    jr.skipValue();
                                                }
                                            }
                                            Log.v("Fin de guardia", "");
                                            jr.endObject();
                                        } else {
                                            jr.skipValue();
                                        }
                                    }
                                    jr.endObject();
                                } else {
                                    jr.skipValue();
                                }
                            }
                            lista.add(f);
                            jr.endObject();
                        }
                        jr.endArray();
                    } else {
                        jr.skipValue();
                    }
                }
                jr.endObject();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
