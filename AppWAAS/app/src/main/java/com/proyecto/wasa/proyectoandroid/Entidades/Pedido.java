package com.proyecto.wasa.proyectoandroid.Entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrador on 02/03/2017.
 */

public class Pedido  implements Serializable {
    private long CodigoPedido;
    private Date FechaPedido;
    private String DireccionPedido;
    private double PrecioPedido;
    private Usuario Usuario;


    public long getCodigoPedido() {
        return CodigoPedido;
    }

    public void setCodigoPedido(long codigoPedido) {
        CodigoPedido = codigoPedido;
    }

    public Date getFechaPedido() {
        return FechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        FechaPedido = fechaPedido;
    }

    public String getDireccionPedido() {
        return DireccionPedido;
    }

    public void setDireccionPedido(String direccionPedido) {
        DireccionPedido = direccionPedido;
    }

    public double getPrecioPedido() {
        return PrecioPedido;
    }

    public void setPrecioPedido(double precioPedido) {
        PrecioPedido = precioPedido;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario usuario) {
        Usuario = usuario;
    }
}
