package com.proyecto.wasa.proyectoandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    public String readJSONFeed(String desiredUrl) {

        URL url;
        HttpURLConnection urlConnection = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            url = new URL(desiredUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();

            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }

    private class ReadPlacesFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray postalCodesItems = new
                        JSONArray(jsonObject.getString("cliente"));

                //---print out the content of the json feed---
                for (int i = 0; i < postalCodesItems.length(); i++) {
                    JSONObject postalCodesItem =
                            postalCodesItems.getJSONObject(i);

                    Toast.makeText(getBaseContext(),
                            postalCodesItem.getString("segCor"),
                            Toast.LENGTH_SHORT).show();



                }
            } catch (Exception e) {
                Log.d("ReadPlacesFeedTask", e.getLocalizedMessage());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnGetPlaces(View view) {
        EditText txtPostalCode = (EditText) findViewById(R.id.txtPostalCode);
        int a = Integer.parseInt(txtPostalCode.getEditableText().toString());
        new ReadPlacesFeedTask().execute("http://192.168.1.102:8081/Restful/cliente/listarTodos?num=" + a);
    }
}
