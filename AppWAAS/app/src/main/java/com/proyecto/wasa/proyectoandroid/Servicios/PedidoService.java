package com.proyecto.wasa.proyectoandroid.Servicios;

import com.proyecto.wasa.proyectoandroid.Entidades.PedidoBE;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by AngelEloy on 28/02/2017.
 */

public interface PedidoService {
    @GET("articulo/listar")
    void getPedidos(Callback<List<PedidoBE>> callback);

    @GET("articulo/listar")
    List<PedidoBE> getPedidos();
}
