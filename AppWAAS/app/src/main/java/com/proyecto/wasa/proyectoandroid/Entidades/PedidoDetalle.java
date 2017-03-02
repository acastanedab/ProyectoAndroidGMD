package com.proyecto.wasa.proyectoandroid.Entidades;

import java.io.Serializable;

/**
 * Created by Administrador on 02/03/2017.
 */

public class PedidoDetalle implements Serializable{
    private long CodigoPedidoDetalle;
    private double PrecioPedidoDetalle;
    private int CantidadPedidoDetalle;
    private double SubTotalPedidoDetalle;
    private Pedido Pedido;
    private Articulo Articulo;


    public long getCodigoPedidoDetalle() {
        return CodigoPedidoDetalle;
    }

    public void setCodigoPedidoDetalle(long codigoPedidoDetalle) {
        CodigoPedidoDetalle = codigoPedidoDetalle;
    }

    public double getPrecioPedidoDetalle() {
        return PrecioPedidoDetalle;
    }

    public void setPrecioPedidoDetalle(double precioPedidoDetalle) {
        PrecioPedidoDetalle = precioPedidoDetalle;
    }

    public int getCantidadPedidoDetalle() {
        return CantidadPedidoDetalle;
    }

    public void setCantidadPedidoDetalle(int cantidadPedidoDetalle) {
        CantidadPedidoDetalle = cantidadPedidoDetalle;
    }

    public double getSubTotalPedidoDetalle() {
        return SubTotalPedidoDetalle;
    }

    public void setSubTotalPedidoDetalle(double subTotalPedidoDetalle) {
        SubTotalPedidoDetalle = subTotalPedidoDetalle;
    }

    public Pedido getPedido() {
        return Pedido;
    }

    public void setPedido(Pedido pedido) {
        Pedido = pedido;
    }

    public Articulo getArticulo() {
        return Articulo;
    }

    public void setArticulo(Articulo articulo) {
        Articulo = articulo;
    }
}
