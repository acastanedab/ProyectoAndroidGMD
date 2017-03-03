package com.proyecto.wasa.proyectoandroid.Servicios;

import com.proyecto.wasa.proyectoandroid.Entidades.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by GMD on 02/03/2017.
 */

public interface UsuarioService {
    @POST("usuario/obtener/")
    Call<Usuario> obtenerUsuario(@Body Usuario usuario);
}
