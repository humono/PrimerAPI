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
                    String valor = jr.nextString();
                    Log.v("elemento", clave + " - " + valor);
                }
                jr.endObject();
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
