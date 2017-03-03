package com.proyecto.wasa.proyectoandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.proyecto.wasa.proyectoandroid.Entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnFragmentInteractionListener {

    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        getSupportActionBar().setTitle(R.string.app_name_home);

        HomeFragment homeFragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.FrameMain, homeFragment, homeFragment.getTag()
        ).commit();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id == R.id.action_perfil) {
            getSupportActionBar().setTitle(R.string.app_name_perfil);
            ProfileFragment profileFragment = new ProfileFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out)
                    .replace(R.id.FrameMain, profileFragment, profileFragment.getTag()
                    ).commit();

            return true;
        }
        if (id == R.id.action_pedidos) {
            getSupportActionBar().setTitle(R.string.app_name_pedido);
            OrderFragment orderFragment = new OrderFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out)
                    .replace(R.id.FrameMain, orderFragment, orderFragment.getTag()
                    ).commit();
            return true;
        }
        if (id == R.id.action_articulos) {
            getSupportActionBar().setTitle(R.string.app_name_artitulo);
            ArticleFragment articleFragment = new ArticleFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out)
                    .replace(R.id.FrameMain, articleFragment, articleFragment.getTag()
                    ).commit();
            return true;
        }
        if (id == R.id.action_session) {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onFragmentInteraction(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

}
