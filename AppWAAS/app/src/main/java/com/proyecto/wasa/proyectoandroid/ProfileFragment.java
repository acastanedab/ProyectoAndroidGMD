package com.proyecto.wasa.proyectoandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import java.util.ArrayList;
import android.content.SharedPreferences;
import android.content.Intent;
import static android.app.Activity.*;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences sharedpreferences = this.getActivity().getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);


        final ArrayList<ListaGenerica> products = new ArrayList<>();
        ListaGenerica p1 = new ListaGenerica("Nombre ", sharedpreferences.getString("Usuario", null));
        ListaGenerica p2 = new ListaGenerica("Email ", sharedpreferences.getString("Email", null));
        ListaGenerica p3 = new ListaGenerica("Nº Celular ",sharedpreferences.getString("Celular", null));
        products.add(p1);
        products.add(p2);
        products.add(p3);
        BindDictionary<ListaGenerica> dictionary = new BindDictionary<>();
        dictionary.addStringField(R.id.tvName, new StringExtractor<ListaGenerica>() {
            @Override
            public String getStringValue(ListaGenerica list, int position) {
                return list.getName();
            }
        });
        dictionary.addStringField(R.id.tvValue, new StringExtractor<ListaGenerica>() {
            @Override
            public String getStringValue(ListaGenerica list, int position) {
                return "" + list.getValues();
            }
        });

        FunDapter adapter = new FunDapter(ProfileFragment.this.getActivity(), products, R.layout.perfile_layout, dictionary);

        ListView lvProduct = (ListView)view.findViewById(R.id.lvPerfil);
        lvProduct.setAdapter(adapter);
        return view;
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
