package com.proyecto.wasa.proyectoandroid.Servicios;

import com.proyecto.wasa.proyectoandroid.Entidades.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by GMD on 03/03/2017.
 */

public interface PedidoService {
    @GET
    Call<List<Pedido>> ListarPedidoSeguimiento(@Path("codigoPedido") long codigoPedido);
    @GET("pedido/listar/{codigoUsuario}")
    Call<List<Pedido>> ListarPedido(@Path("codigoUsuario") long codigoUsuario);

}
