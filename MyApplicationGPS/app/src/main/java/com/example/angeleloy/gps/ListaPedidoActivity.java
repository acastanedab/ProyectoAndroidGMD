package com.example.angeleloy.gps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.angeleloy.gps.Adapter.PedidoAdapter;
import com.example.angeleloy.gps.Entidades.PedidoBE;
import com.example.angeleloy.gps.R;
import com.example.angeleloy.gps.Servicios.PedidoService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListaPedidoActivity extends AppCompatActivity {

    private ListView listPedidos;
    private ArrayList<PedidoBE> ObjPedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapedido);

        listPedidos = (ListView) findViewById(R.id.lv_deslizable);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.kallpasedano.com/proyecto/api/")
                .build();

        PedidoService pedidoService = retrofit.create(PedidoService.class);

        pedidoService.getPedidos(new Callback<List<PedidoBE>>() {
            @Override
            public void onResponse(Call<List<PedidoBE>> call, Response<List<PedidoBE>> response) {
                PedidoAdapter pedidoAdapter = new PedidoAdapter(ListaPedidoActivity.this, (List)call);
                listPedidos.setAdapter(pedidoAdapter);
            }
            @Override
            public void onFailure(Call<List<PedidoBE>> call, Throwable throwable) {
                Toast.makeText(ListaPedidoActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}
