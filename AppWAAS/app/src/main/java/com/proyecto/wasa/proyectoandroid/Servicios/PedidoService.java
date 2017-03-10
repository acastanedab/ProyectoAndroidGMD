package com.proyecto.wasa.proyectoandroid.Servicios;

import com.proyecto.wasa.proyectoandroid.Entidades.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by GMD on 03/03/2017.
 */

public interface PedidoService {
    @GET("pedido/listar/{codigoUsuario}")
    Call<List<Pedido>> ListarPedido(@Path("codigoUsuario") long codigoUsuario);

    @GET("pedidodetalle/listar/{codigoUsuario}")
    Call<List<Pedido>> ListarPedidoDetalle(@Path("codigoUsuario") long codigoUsuario);

    @GET("pedidoseguimiento/listar/{codigoUsuario}")
    Call<List<Pedido>> ListarPedidoSeguimiento(@Path("codigoPedido") long codigoPedido);


    @POST("pedido/registrar")
    Call<Pedido> RegistrarPedido(@Body Pedido pedido);
}
