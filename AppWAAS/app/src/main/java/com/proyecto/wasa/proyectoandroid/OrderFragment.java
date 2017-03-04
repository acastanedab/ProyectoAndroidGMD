package com.proyecto.wasa.proyectoandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proyecto.wasa.proyectoandroid.Adapter.ArrayAdapterFactory;
import com.proyecto.wasa.proyectoandroid.Entidades.Articulo;
import com.proyecto.wasa.proyectoandroid.Entidades.Pedido;
import com.proyecto.wasa.proyectoandroid.Entidades.PedidoSeguimiento;
import com.proyecto.wasa.proyectoandroid.Servicios.PedidoService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class OrderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText txtPostalCode;
    Button button;

    private ArrayList<PedidoSeguimiento> Listaseguimiento = new ArrayList<PedidoSeguimiento>();


    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        txtPostalCode = (EditText) getView().findViewById(R.id.txtPostalCode);
        button = (Button) getView().findViewById(R.id.obtenerruta);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                long a = Integer.parseInt(txtPostalCode.getEditableText().toString());
                btnGetPlaces(a);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_order, container, false);

        return RootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void btnGetPlaces(long a) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ArrayAdapterFactory())
                .create();
        String URL = getString(R.string.url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PedidoService pedidoService = retrofit.create(PedidoService.class);

        Call<List<Pedido>> call =  pedidoService.ListarPedidoSeguimiento(a);

        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                List<Pedido> pedido= response.body();

                for(int i=0; i<response.body().size();i++) {



                    PedidoSeguimiento seguimiento = new PedidoSeguimiento();
                    seguimiento.setLatitudPedidoSeguimiento(response.body().get(i).getPedidoSeguimiento().getLatitudPedidoSeguimiento());
                    seguimiento.setLongitudPedidoSeguimiento(response.body().get(i).getPedidoSeguimiento().getLongitudPedidoSeguimiento());

                    Listaseguimiento.add(seguimiento);
                }

                gotoActivity(Listaseguimiento);

            }
            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                //Toast.makeText(OrderFragment.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void gotoActivity(ArrayList<PedidoSeguimiento> seguimiento) {
        Intent intent= new Intent(OrderFragment.this.getActivity(), MapsActivity.class);
        intent.putExtra("com.proyecto.wasa.proyectoandroid.Seguimiento", seguimiento);
        startActivity(intent);
    }


}
