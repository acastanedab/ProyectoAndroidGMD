package com.proyecto.wasa.proyectoandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proyecto.wasa.proyectoandroid.Adapter.ArrayAdapterFactory;
import com.proyecto.wasa.proyectoandroid.Adapter.ArticuloAdapter;
import com.proyecto.wasa.proyectoandroid.Entidades.Articulo;
import com.proyecto.wasa.proyectoandroid.Servicios.ArticuloService;

import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistrarPedido.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistrarPedido#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrarPedido extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Spinner spArticulo;

    //@Bind(R.id.spArticulo) Spinner spArticulo;
    @Bind(R.id.eteDireccion) EditText eteDireccion;
    @Bind(R.id.npCantidad) NumberPicker npCantidad;
    @Bind(R.id.btnRegistrarPedido) Button btnRegistrarPedido;




    public RegistrarPedido() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrarPedido.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrarPedido newInstance(String param1, String param2) {
        RegistrarPedido fragment = new RegistrarPedido();
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
        View v =  inflater.inflate(R.layout.fragment_registrar_pedido, container, false);




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
                List<Articulo> result= response.body();


            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable throwable) {
                //Toast.makeText(ListaArticuloActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
