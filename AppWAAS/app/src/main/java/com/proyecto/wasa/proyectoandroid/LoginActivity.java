package com.proyecto.wasa.proyectoandroid;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    ArrayList<HashMap<String, String>> userList;

    @Bind(R.id.etxt_email) EditText etxt_email;
    @Bind(R.id.etxt_password) EditText etxt_password;
    @Bind(R.id.btn_login) Button btn_login;
    @Bind(R.id.link_signup) TextView link_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        link_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SingAccountActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            //onLoginFailed();
            return ;
        }
        else {
            btn_login.setEnabled(false);

            final ProgressDialog progressDialog  = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            String email = etxt_email.getText().toString();
            String password = etxt_password.getText().toString();
            //new GetLoginClass().execute();
            new ReadPlacesFeedTask().execute("http://www.kallpasedano.com/proyecto/api/usuario/obtener/usuario");
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            onLoginSuccess();
                            progressDialog.dismiss();
                        }
                    }, 3000);

            // TODO: Implement your own authentication logic here.

        }
    }
    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        btn_login.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn_login.setEnabled(true);
    }

    public boolean validate() {

        String email = etxt_email.getText().toString();
        String password = etxt_password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etxt_email.setError("Ingrese un Email Valido");
            etxt_email.setFocusable(true);
            etxt_email.requestFocus();
            return false;
        } else {
            etxt_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            etxt_password.setError("La cadena debe contener entre 4 and 10 caracteres.");
            etxt_password.setFocusable(true);
            etxt_password.requestFocus();
            return false;
        } else {
            etxt_password.setError(null);
        }

        return true;
    }


    private class GetLoginClass extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(LoginActivity.this,"Json Data is  downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String url = "http://www.kallpasedano.com/proyecto/api/usuario/obtener/usuario";// + email;
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    //JSONArray contacts = jsonObj.getJSONArray("Usuario");
                    //JSONArray contacts = new JSONArray("["+jsonObj.getString("Usuario")+"]");
                    //int datos=jsonObj.length();//.getString("Usuario");
                    //Toast.makeText(getApplicationContext(),  datos,  Toast.LENGTH_LONG).show();
                    /*for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String CodigoUsuario = c.getString("CodigoUsuario");
                        String NombreUsuario = c.getString("NombreUsuario");
                        String CorreoUsuario = c.getString("CorreoUsuario");
                        String CelularUsuario = c.getString("CelularUsuario");
                        HashMap<String, String> contact = new HashMap<>();
                        contact.put("CodigoUsuario", CodigoUsuario);
                        contact.put("NombreUsuario", NombreUsuario);
                        contact.put("CorreoUsuario", CorreoUsuario);
                        contact.put("CelularUsuario", CelularUsuario);
                        userList.add(contact);
                    }*/

                    Toast.makeText(getApplicationContext(),  "POR FIN",  Toast.LENGTH_LONG).show();


                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),  "Json parsing error: " + e.getMessage(),  Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } else {
                Log.e(TAG, "No se pudo obtener json desde el servidor.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "No se pudo obtener json desde el servidor. Revise LogCat para ver si hay errores!",
                                Toast.LENGTH_LONG).show();
                    }
                });
                Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    private class ReadPlacesFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                /*String Seguimiento = jsonObject.getString("Seguimiento");
                JSONObject c = new JSONObject(Seguimiento);
                String LongitudSeguimiento = c.getString("LongitudSeguimiento");
                String CodigoSeguimiento = c.getString("CodigoSeguimiento");
                String FechaSeguimiento = c.getString("FechaSeguimiento");
                String LatitudSeguimiento = c.getString("LatitudSeguimiento");
                String Articulo = c.getString("Articulo");
                Toast.makeText(getBaseContext(), LongitudSeguimiento,  Toast.LENGTH_LONG).show();*/
                JSONArray postalCodesItems = new JSONArray("["+jsonObject.getString("Usuario")+"]");
                //---print out the content of the json feed---
                for (int i = 0; i < postalCodesItems.length(); i++) {
                    JSONObject postalCodesItem = postalCodesItems.getJSONObject(i);
                    Toast.makeText(getBaseContext(), postalCodesItem.getString("NombreUsuario"),  Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.d("ReadPlacesFeedTask", e.getLocalizedMessage());
            }
        }
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
    }

}
