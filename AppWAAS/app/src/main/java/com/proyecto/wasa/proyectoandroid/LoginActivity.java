package com.proyecto.wasa.proyectoandroid;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proyecto.wasa.proyectoandroid.Adapter.ArrayAdapterFactory;
import com.proyecto.wasa.proyectoandroid.Entidades.Usuario;
import com.proyecto.wasa.proyectoandroid.Servicios.UsuarioService;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.content.Context;
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    public static final String MyPREFERENCES = "MyPrefs" ;
    ArrayList<HashMap<String, String>> userList;
    private FragmentManager fragmentManager;

    SharedPreferences sharedpreferences;
    @Bind(R.id.etxt_email) EditText etxt_email;
    @Bind(R.id.etxt_password) EditText etxt_password;
    @Bind(R.id.btn_login) Button btn_login;
    @Bind(R.id.link_signup) TextView link_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarsesion();
            }
        });
        link_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SingAccountActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }

    private void iniciarsesion(){
        if (!validate()) {
            return ;
        }
        else {
            btn_login.setEnabled(false);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new ArrayAdapterFactory())
                    .create();
            final ProgressDialog progressDialog  = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();

            String URL = getString(R.string.url);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            String email = etxt_email.getText().toString();
            String pass = etxt_password.getText().toString();

            UsuarioService usuarioService = retrofit.create(UsuarioService.class);

            Usuario user = new Usuario();
            user.setCorreoUsuario(email);
            user.setContraseniaUsuario(pass);
            Call<Usuario> call = usuarioService.obtenerUsuario(user);

            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    int estado = response.body().getEstado();
                    if(estado==1) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        String usuario =response.body().getNombreUsuario();
                        String email = response.body().getCorreoUsuario();
                        String celuar = response.body().getCelularUsuario();
                        editor.putString("Usuario", usuario);
                        editor.putString("Email", email);
                        editor.putString("Celular", celuar);
                        editor.commit();

                        Toast.makeText(LoginActivity.this, "Acceso con existo al Usuario: " + response.body().getNombreUsuario() , Toast.LENGTH_LONG).show();
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        onLoginSuccess();
                                        progressDialog.dismiss();
                                    }
                                }, 3000);
                    }
                    else {
                        btn_login.setEnabled(true);
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, response.body().getMensaje() , Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable throwable) {
                    Toast.makeText(LoginActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Error",throwable.getMessage());
                }
            });

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
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






}
