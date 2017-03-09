package com.proyecto.wasa.proyectoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proyecto.wasa.proyectoandroid.Adapter.ArrayAdapterFactory;
import com.proyecto.wasa.proyectoandroid.Adapter.ArticuloAdapter;
import com.proyecto.wasa.proyectoandroid.Entidades.Articulo;
import com.proyecto.wasa.proyectoandroid.Servicios.ArticuloService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarPedidoActivity extends AppCompatActivity {

    private Spinner spArticulo ;
    private EditText eteCantidad;
    private EditText eteDireccion;
    private Button btnRegistrarPedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pedido);

        eteCantidad= (EditText) findViewById(R.id.eteCantidad);
        spArticulo = (Spinner) findViewById(R.id.spArticulo);
        eteDireccion = (EditText) findViewById(R.id.eteDireccion);
        btnRegistrarPedido = (Button) findViewById(R.id.btnRegistrarPedido);

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ArrayAdapterFactory())
                .create();

        String URL = getString(R.string.url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ArticuloService articuloService = retrofit.create(ArticuloService.class);
        Call<List<Articulo>> call = articuloService.getArticulo();

        call.enqueue(new Callback<List<Articulo>>() {
            @Override
            public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                List<Articulo> articulos = response.body();
                List<SelectType> resultado = new ArrayList<SelectType>();

                for(Articulo articulo: articulos){
                    SelectType item= new SelectType();
                    item.setId(articulo.getCodigoArticulo());
                    item.setName(articulo.getNombreArticulo());
                    resultado.add(item);
                }
                ArrayAdapter<SelectType> adapter = new ArrayAdapter<SelectType>(getApplicationContext(),R.layout.spinner_dropdownlist, resultado);
                adapter.setDropDownViewResource(R.layout.spinner_dropdownlist);
                spArticulo.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable throwable) {
                Toast.makeText(RegistrarPedidoActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private class SelectType {
        private long id;
        private String name;


        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString(){
            return name;
        }
    }
}



