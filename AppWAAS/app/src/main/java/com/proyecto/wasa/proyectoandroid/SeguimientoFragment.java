package com.proyecto.wasa.proyectoandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proyecto.wasa.proyectoandroid.Adapter.ArrayAdapterFactory;
import com.proyecto.wasa.proyectoandroid.Adapter.PedidoAdapter;
import com.proyecto.wasa.proyectoandroid.Entidades.Pedido;
import com.proyecto.wasa.proyectoandroid.Servicios.PedidoService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SeguimientoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SeguimientoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeguimientoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listPedidos;

    public SeguimientoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeguimientoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SeguimientoFragment newInstance(String param1, String param2) {
        SeguimientoFragment fragment = new SeguimientoFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_seguimiento, container, false);
        listPedidos = (ListView) view.findViewById(R.id.lv_deslizable);

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ArrayAdapterFactory())
                .create();

        String URL = getString(R.string.url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //variable temporal eliminar apenas se implemente el Intent Usuario
        //long codigoUsuario=1;
        SharedPreferences sharedpreferences = this.getActivity().getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        long codigoUsuario =sharedpreferences.getLong("Codigo",0);
        PedidoService pedidoService = retrofit.create(PedidoService.class);
        Call<List<Pedido>> call = pedidoService.ListarPedido(codigoUsuario);

        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                List<Pedido> pedidos = response.body();
                if(response.body().size()>0) {
                    PedidoAdapter pedidoAdapter = new PedidoAdapter(SeguimientoFragment.this.getActivity(), pedidos);
                    listPedidos.setAdapter(pedidoAdapter);
                }else{
                    List<Pedido> lista = new ArrayList<Pedido>();
                    PedidoAdapter pedidoAdapter = new PedidoAdapter(SeguimientoFragment.this.getActivity(), lista);
                    listPedidos.setAdapter(pedidoAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Toast.makeText(SeguimientoFragment.this.getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
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

}
