package com.proyecto.wasa.proyectoandroid.Servicios;

import com.proyecto.wasa.proyectoandroid.Entidades.Articulo;
import com.proyecto.wasa.proyectoandroid.Entidades.PedidoSeguimientoBE;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AngelEloy on 28/02/2017.
 */

public interface PedidoService {
    @GET("articulo/listar")
    Call<List<Articulo>> getArticulo();


}
