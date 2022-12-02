package com.example.primerapi;

import android.util.JsonReader;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PeticionEjercicio extends Thread {
    @Override
    public void run() {
        super.run();

        try {
            String nombre = "";
            String[] datosFarmacia = {};
            URL url = new URL("https://www.zaragoza.es/sede/servicio/farmacia?rf=html&srsname=wgs84&tipo=guardia&start=0&rows=50&distance=500");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json'");
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                JsonReader jr = new JsonReader(isr);
                while (jr.hasNext()) {
                    String clave = jr.nextName();
                    if (clave.equals("features")) {
                        jr.beginArray();
                        while (jr.hasNext()) {
                            String clave2 = jr.nextName();
                            if (clave2.equals("properties")) {
                                jr.beginObject();
                                while (jr.hasNext()) {
                                    String clave3 = jr.nextName();
                                    if (clave3.equals("title")) {
                                        nombre = jr.nextString();
                                        Log.v("Nombre farmacia", nombre);
                                    } else if (clave3.equals("guardia")) {
                                        jr.beginObject();
                                        while (jr.hasNext()) {
                                            int i = 0;
                                            while (jr.hasNext()) {
                                                datosFarmacia[i] = jr.nextName();
                                                i++;
                                            }
                                            Log.v("Datos farmacia", datosFarmacia.toString());
                                        }
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
                        Log.v("Farmacia", "NOMBRE: " + nombre + " | fecha: " + datosFarmacia[0] +
                                " | turno: " + datosFarmacia[1] + " | horario: " + datosFarmacia[2] + " | sector: " + datosFarmacia[3]);
                        jr.endArray();
                    } else {
                        jr.skipValue();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
