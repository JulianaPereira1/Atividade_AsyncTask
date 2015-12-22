package com.example.julianapereira.atividade_asynctask;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Juliana Pereira on 22/12/2015.
 */

public class HttpService {


    private static final String URL_CONTEXT = "http://192.168.25.75:8080/rest-servlet-service/";

    public static HttpURLConnection sendGetRequest(String service)
            throws MalformedURLException, IOException{

        HttpURLConnection connection = null;

        try {

            URL url = new URL(URL_CONTEXT + service);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(15000);
            connection.connect();

        } finally {

            connection.disconnect();
        }

        return connection;
    }

    public void sendJsonPostRequest() {

    }

    public static String getHttpContent(HttpURLConnection connection) {

        StringBuilder builder = new StringBuilder();

        try {

            InputStream content = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    content, "iso-8859-1"), 8);

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            content.close();

        } catch (IOException e) {

            Log.e("NotificationWearApp", "IOException: " + e);
        }

        return builder.toString();
    }
}
