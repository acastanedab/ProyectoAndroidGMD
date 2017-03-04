package com.proyecto.wasa.proyectoandroid;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.proyecto.wasa.proyectoandroid.Adapter.ArrayAdapterFactory;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
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



    public void btnGetPlaces(View view) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ArrayAdapterFactory())
                .create();
        String URL = getString(R.string.url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        EditText txtPostalCode = (EditText) view.findViewById(R.id.txtPostalCode);
        int a = Integer.parseInt(txtPostalCode.getEditableText().toString());
        PedidoService pedidoService = retrofit.create(PedidoService.class);
        Call<List<Pedido>> call =  pedidoService.ListarPedidoSeguimiento((long) a);

        call.enqueue(new Callback<List<Pedido>>() {
                         @Override
                         public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                       /*      List<PedidoSeguimiento> pedidoSeguimientos= new List<PedidoSeguimiento>();

                             for(int i=0; i<response.body().size();i++) {

                                 response.body().get(1).getPedidoSeguimiento().getLatitudPedidoSeguimiento();
                             }

*/
                         }
                         @Override
                         public void onFailure(Call<List<Pedido>> call, Throwable t) {
                             //Toast.makeText(OrderFragment.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     });


                //new ReadPlacesFeedTask().execute("http://localhost:1270/api/Seguimiento/BuscarSeguimientoArticulo/" + a);
//        new ReadPlacesFeedTask().execute("http://www.kallpasedano.com/proyecto/api/Seguimiento/BuscarSeguimientoArticulo/" + a);
    }
    public String readJSONFeed(String desiredUrl) {

        URL url;
        HttpURLConnection urlConnection = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            url = new URL(desiredUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();

            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }
    private class ReadPlacesFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                /*String Seguimiento = jsonObject.getString("Seguimiento");
                JSONObject c = new JSONObject(Seguimiento);
                String LongitudSeguimiento = c.getString("LongitudSeguimiento");
                String CodigoSeguimiento = c.getString("CodigoSeguimiento");
                String FechaSeguimiento = c.getString("FechaSeguimiento");
                String LatitudSeguimiento = c.getString("LatitudSeguimiento");
                String Articulo = c.getString("Articulo");
                Toast.makeText(getBaseContext(), LongitudSeguimiento,  Toast.LENGTH_LONG).show();*/
                JSONArray postalCodesItems = new JSONArray("["+jsonObject.getString("Seguimiento")+"]");
                //---print out the content of the json feed---
                for (int i = 0; i < postalCodesItems.length(); i++) {
                    JSONObject postalCodesItem = postalCodesItems.getJSONObject(i);
                    //Toast.makeText(getBaseContext(), postalCodesItem.getString("LongitudSeguimiento"),  Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.d("ReadPlacesFeedTask", e.getLocalizedMessage());
            }
        }
    }

}
