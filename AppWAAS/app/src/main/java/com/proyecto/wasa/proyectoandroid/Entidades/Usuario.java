package com.proyecto.wasa.proyectoandroid.Entidades;

import java.io.Serializable;

/**
 * Created by Administrador on 02/03/2017.
 */

public class Usuario implements Serializable {

    private long CodigoUsuario;
    private String NombreUsuario;
    private String ContraseniaUsuario;
    private String CorreoUsuario;
    private String CelularUsuario;

    private int Estado;
    private String Mensaje;


    public long getCodigoUsuario() {
        return CodigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        CodigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public String getContraseniaUsuario() {
        return ContraseniaUsuario;
    }

    public void setContraseniaUsuario(String contraseniaUsuario) {
        ContraseniaUsuario = contraseniaUsuario;
    }

    public String getCorreoUsuario() {
        return CorreoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        CorreoUsuario = correoUsuario;
    }

    public String getCelularUsuario() {
        return CelularUsuario;
    }

    public void setCelularUsuario(String celularUsuario) {
        CelularUsuario = celularUsuario;
    }


    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }


    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }
}
