package com.proyecto.wasa.proyectoandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proyecto.wasa.proyectoandroid.Adapter.ArrayAdapterFactory;
import com.proyecto.wasa.proyectoandroid.Entidades.Usuario;
import com.proyecto.wasa.proyectoandroid.Servicios.UsuarioService;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingAccountActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @Bind(R.id.input_name) EditText _nameText;
    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_mobile) EditText _mobileText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.input_reEnterPassword) EditText _reEnterPasswordText;
    @Bind(R.id.btn_signup) Button _signupButton;
    @Bind(R.id.link_login) TextView _loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_account);
        ButterKnife.bind(this);
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SingAccountActivity.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();
        String URL = getString(R.string.url);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        UsuarioService usuarioService = retrofit.create(UsuarioService.class);
        Usuario user = new Usuario();
        user.setNombreUsuario(name);
        user.setCorreoUsuario(email);
        user.setCelularUsuario(mobile);
        user.setContraseniaUsuario(password);
        Call<Usuario> call = usuarioService.RegistrarUsuario(user);
        call.enqueue(new Callback<Usuario>() {
                         @Override
                         public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                             int estado = response.body().getEstado();
                             if(estado==1) {
                                 Toast.makeText(SingAccountActivity.this, "Se Registró con existo el Usuario: " + response.body().getNombreUsuario() , Toast.LENGTH_LONG).show();
                                 overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                                 new android.os.Handler().postDelayed(
                                         new Runnable() {
                                             public void run() {
                                                 Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                                 startActivity(intent);
                                                 onSignupSuccess();
                                                 progressDialog.dismiss();
                                             }
                                         }, 3000);
                             }
                             else {
                                 progressDialog.dismiss();
                                 Toast.makeText(SingAccountActivity.this, response.body().getMensaje() , Toast.LENGTH_LONG).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<Usuario> call, Throwable throwable) {
                             Toast.makeText(SingAccountActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     });         

    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("Ingrese nombre con más de 3 caracteres");
           return false;
        } else {
            _nameText.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Ingrese un Email valido");
            _emailText.setFocusable(true);
            _emailText.requestFocus();
            return false;
        } else {
            _emailText.setError(null);
        }

        if (mobile.isEmpty() || ( mobile.length()<9 && mobile.length()>9 )) {
            _mobileText.setError("Ingrese un Nº celular valido de 9 caracteres");
            _mobileText.setFocusable(true);
            _mobileText.requestFocus();
            return false;
        } else {
            _mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Ingrese password entre 4 y 10 caracteres.");
            _passwordText.setFocusable(true);
            _passwordText.requestFocus();
            return false;
        } else {
            _passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            _reEnterPasswordText.setError("Password no coenciden");
            _reEnterPasswordText.setFocusable(true);
            _reEnterPasswordText.requestFocus();
            return false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return true;
    }

}
