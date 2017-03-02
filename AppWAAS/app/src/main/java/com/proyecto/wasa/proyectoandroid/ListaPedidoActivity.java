package com.proyecto.wasa.proyectoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.proyecto.wasa.proyectoandroid.Adapter.ArrayAdapterFactory;
import com.proyecto.wasa.proyectoandroid.Adapter.PedidoAdapter;
import com.proyecto.wasa.proyectoandroid.Entidades.Articulo;
import com.proyecto.wasa.proyectoandroid.Servicios.PedidoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaPedidoActivity extends AppCompatActivity {

    private ListView listPedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedido);

        listPedidos = (ListView) findViewById(R.id.lv_deslizable);

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ArrayAdapterFactory())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.kallpasedano.com/proyecto/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PedidoService pedidoService = retrofit.create(PedidoService.class);
        Call<List<Articulo>> call = pedidoService.getArticulo();

        call.enqueue(new Callback<List<Articulo>>() {
            @Override
            public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
              Log.i("Hola: ", "ok");
              PedidoAdapter pedidoAdapter = new PedidoAdapter(ListaPedidoActivity.this, response.body());
              listPedidos.setAdapter(pedidoAdapter);
            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable throwable) {
                Toast.makeText(ListaPedidoActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("CTM: ", throwable.getMessage());
            }
        });

    }
}
