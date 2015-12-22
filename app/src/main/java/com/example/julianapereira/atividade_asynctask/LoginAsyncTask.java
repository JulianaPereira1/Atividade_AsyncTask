package com.example.julianapereira.atividade_asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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
    public class LoginAsyncTask extends AsyncTask<String, Void, HttpURLConnection>{

        Context context;

        public LoginAsyncTask(Context activity) {

            this.context = activity;
        }

        @Override
        protected void onPreExecute() {

            Log.i("NotificationWearApp", "OnPreExecute");
        }

        @Override
        protected HttpURLConnection doInBackground(String... valores) {

            Log.i("NotificationWearApp", "doInBackground: " + valores[0]);

            HttpURLConnection connection = null;

            try {

                connection = HttpService.sendGetRequest("servicoservlet");

            } catch (MalformedURLException ex) {

                Log.e("NotificationWearApp","MalformedURLException");

            } catch (IOException ex) {

                Log.e("NotificationWearApp","MalformedURLException");
            }

            return connection;
        }

        @Override
        protected void onPostExecute(HttpURLConnection connection) {

            try {

                int status = connection.getResponseCode();

                Log.i("NotificationWearApp", "Status HTTP-Response: " + status);

                String contentValue = HttpService.getHttpContent(connection);
                JSONObject json = new JSONObject(contentValue);

                String nome = json.getString("nome");
                Toast.makeText(context, nome, Toast.LENGTH_LONG).show();

            } catch (IOException e) {

                e.printStackTrace();

            } catch (JSONException e) {

                Log.e("NotificationWearApp", "JSONException");
            }
        }
}

