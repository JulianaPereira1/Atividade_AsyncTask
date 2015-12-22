package com.example.julianapereira.atividade_asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;



import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;


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

            Log.i("Login", "OnPreExecute");
        }

        @Override
        protected HttpURLConnection doInBackground(String... valores) {

            Log.i("Login", "doInBackground: " + valores[0]);

            HttpURLConnection connection = null;

            try {

                connection = HttpService.sendGetRequest("servicoservlet");

            } catch (MalformedURLException ex) {

                Log.e("Login","MalformedURLException");

            } catch (IOException ex) {

                Log.e("Login","MalformedURLException");
            }

            return connection;
        }

        @Override
        protected void onPostExecute(HttpURLConnection connection) {

            try {

                int status = connection.getResponseCode();

                Log.i("Login", "Status HTTP-Response: " + status);

                String contentValue = HttpService.getHttpContent(connection);

                Toast.makeText(context, "Login realizado com sucesso", Toast.LENGTH_LONG).show();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
}

