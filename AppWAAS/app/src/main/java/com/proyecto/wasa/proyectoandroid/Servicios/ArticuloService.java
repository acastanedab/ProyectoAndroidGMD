package com.proyecto.wasa.proyectoandroid.Servicios;

import com.proyecto.wasa.proyectoandroid.Entidades.Articulo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by AngelEloy on 28/02/2017.
 */

public interface ArticuloService {
    @GET("articulo/listar")
    Call<List<Articulo>> getArticulo();


}
