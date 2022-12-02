package com.example.primerapi;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Peticion extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL("https://api.github.com/");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//            conn.setRequestProperty("user","javier");         PROPIEDADES DE LA CONEXIÓN, COMO POR EJEMPLO USERS Y PASS
            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
            if (conn.getResponseCode() == 200) {                  // SI EL CÓDIGO DEVUELTO ES 200 LA RESPUESTA ES CORRECTA
                InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                JsonReader jr = new JsonReader(isr);
                //Log.v("Json", isr.toString());
                //          Empiezo a leer el objeto del Json
                jr.beginObject();
                //          NextName solo lee el nombre de la clave, no avanza el puntero.
                // String clave = jr.nextName();
                //          SkipValue salta el valor siguiente.
                // jr.skipValue();
                //          Con next string leo el siguiente valor y avanzo el puntero.

                //          Voy leyendo el Json y recupero la clavve-valor y la imprimo en el Log.
                while (jr.hasNext()) {
                    String clave = jr.nextName();
                    //    String valor = jr.nextString();
                    if (clave.equals("organization_url")) {
                        String valor = jr.nextString();
                        Log.v("elemento del Json", "CLAVE: " + clave + " - VALOR: " + valor);
                    } else if (clave.equals("keys_url")) {
                        String valor = jr.nextString();
                        Log.v("elemento del Json", "CLAVE: " + clave + " - VALOR: " + valor);
                    } else {
                        jr.skipValue();
                    }
                }
                jr.endObject();


// https://www.zaragoza.es/sede/servicio/mascotas?rf=html&start=0&rows=50 VAMOS A LA URL DE LA API DE ZARAGOZA para probar un array
                URL url2 = new URL("https://www.zaragoza.es/sede/servicio/mascotas?rf=html&start=0&rows=50");
                HttpsURLConnection conn2 = (HttpsURLConnection) url2.openConnection();
                conn2.setRequestProperty("Accept", "application/json");
                if (conn2.getResponseCode() == 200) {
                    InputStream is2 = conn2.getInputStream();
                    InputStreamReader isr2 = new InputStreamReader(is2, "UTF-8");
                    JsonReader jr2 = new JsonReader(isr2);
                    jr2.beginObject();
                    while (jr2.hasNext()) {
                        String clave2 = jr2.nextName();
                        String nombre = "", raza = "";
                        if (clave2.equals("result")) {
                            jr2.beginArray();
                            while (jr2.hasNext()) {
                                jr2.beginObject();
                                while (jr2.hasNext()) {
                                    String clave3 = jr2.nextName();
                                    if (clave3.equals("nombre")) {
                                        nombre = jr2.nextString();
                                    } else if (clave3.equals("raza")) {
                                        raza = jr2.nextString();
                                    } else {
                                        jr2.skipValue();
                                    }
                                    Log.v("Animal", "NOMBRE: " + nombre + " - RAZA: " + raza);
                                }
                                jr2.endObject();
                            }
                            jr2.endArray();
                        } else {
                            jr2.skipValue();
                        }
                    }
                    jr2.endObject();
                }
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
