package com.proyecto.wasa.proyectoandroid;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.proyecto.wasa.proyectoandroid.Entidades.PedidoSeguimiento;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<PedidoSeguimiento> ruta = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(getIntent() != null && getIntent().hasExtra("com.proyecto.wasa.proyectoandroid.Seguimiento")) {
            ruta = ( ArrayList<PedidoSeguimiento>)getIntent().getExtras().getSerializable("com.proyecto.wasa.proyectoandroid.Seguimiento");
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng base = null;
        LatLng baseactual = null;
        LatLng recorrido = null;
        ArrayList<LatLng> points=new ArrayList<LatLng>();


        PolylineOptions lineOptions = null;

        mMap = googleMap;

        for(int i=0; i< ruta.size(); i++) {
            //points.add(new LatLng(Double.parseDouble(ruta.get(i).getLatitud()), Double.parseDouble(ruta.get(i).getLongitud())));

            double lat = Double.parseDouble(ruta.get(i).getLatitudPedidoSeguimiento());
            double lng = Double.parseDouble(ruta.get(i).getLongitudPedidoSeguimiento());

            if(i == 0)
            {
                base = new LatLng(lat, lng);
            }
            LatLng position = new LatLng(lat, lng);
            points.add(position);
        }


        mMap.addMarker(new MarkerOptions()
                .position(base)
                .title("Base")
        );


        //LatLng comida = new LatLng(-12.0951727,-77.0317987);

        mMap.addPolyline(new PolylineOptions().addAll(points).width(8).color(Color.RED));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(base,18));


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }




}
