package com.example.primerapi;

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
                Log.v("Json", isr.toString());
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
