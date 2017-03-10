package com.proyecto.wasa.proyectoandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proyecto.wasa.proyectoandroid.Adapter.ArrayAdapterFactory;
import com.proyecto.wasa.proyectoandroid.Entidades.Articulo;
import com.proyecto.wasa.proyectoandroid.Entidades.Pedido;
import com.proyecto.wasa.proyectoandroid.Servicios.ArticuloService;
import com.proyecto.wasa.proyectoandroid.Servicios.PedidoService;

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
    private long idSpinner=0;
    List<SelectType> lstArticulo;
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
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        btnRegistrarPedido.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                        long codigoUsuario = sharedpreferences.getLong("Codigo",0);
                        Pedido pedido = new Pedido();
                        pedido.setDireccionPedido(eteDireccion.getText().toString());
                        //pedido.getUsuario().setCodigoUsuario(1);
                        pedido.getUsuario().setCodigoUsuario(codigoUsuario);
                        pedido.getPedidoDetalle().getArticulo().setCodigoArticulo(idSpinner);
                        pedido.getPedidoDetalle().setCantidadPedidoDetalle(Integer.parseInt(eteCantidad.getText().toString()));
                        PedidoService pedidoService = retrofit.create(PedidoService.class);
                        Call<Pedido> callPedido = pedidoService.RegistrarPedido(pedido);
                        callPedido.enqueue(new Callback<Pedido>() {
                            @Override
                            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                                if(response.body().getEstado()==1) {
                                    Toast.makeText(RegistrarPedidoActivity.this, "Se grabo el pedido con éxito.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(RegistrarPedidoActivity.this, response.body().getMensaje(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Pedido> call, Throwable t) {
                                Toast.makeText(RegistrarPedidoActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
        );




        ArticuloService articuloService = retrofit.create(ArticuloService.class);
        Call<List<Articulo>> call = articuloService.getArticulo();

        call.enqueue(new Callback<List<Articulo>>() {
            @Override
            public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                List<Articulo> articulos = response.body();
                lstArticulo = new ArrayList<SelectType>();
                for(Articulo articulo: articulos){
                    SelectType item= new SelectType();
                    item.setId(articulo.getCodigoArticulo());
                    item.setName(articulo.getNombreArticulo());
                    lstArticulo.add(item);
                }
                ArrayAdapter<SelectType> adapter = new ArrayAdapter<SelectType>(getApplicationContext(),R.layout.spinner_dropdownlist, lstArticulo);
                adapter.setDropDownViewResource(R.layout.spinner_dropdownlist);
                spArticulo.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable throwable) {
                Toast.makeText(RegistrarPedidoActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        spArticulo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idSpinner = new Long(lstArticulo.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }
    private class SelectType {
        private long id;
        private String name;

        public SelectType(){

        }

        public SelectType(long id,String name){
            this.id= id;
            this.name= name;
        }

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



