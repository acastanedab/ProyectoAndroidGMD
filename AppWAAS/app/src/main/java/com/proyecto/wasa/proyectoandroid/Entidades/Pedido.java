package com.proyecto.wasa.proyectoandroid.Entidades;

import java.io.Serializable;

/**
 * Created by Administrador on 02/03/2017.
 */

public class Pedido  implements Serializable {
    private long CodigoPedido;
    private String FechaPedido;
    private String DireccionPedido;
    private double PrecioTotalPedido;
    private Usuario Usuario;
    private PedidoDetalle PedidoDetalle;
    private PedidoSeguimiento PedidoSeguimiento;


    public  Pedido() {
        setPedidoDetalle(new PedidoDetalle());
        setPedidoSeguimiento(new PedidoSeguimiento());
        setPedidoDetalle(new PedidoDetalle());
        setPedidoSeguimiento(new PedidoSeguimiento());
    }


    public long getCodigoPedido() {
        return CodigoPedido;
    }

    public void setCodigoPedido(long codigoPedido) {
        CodigoPedido = codigoPedido;
    }

    public String getFechaPedido() {

        return FechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {

        FechaPedido = fechaPedido;
    }

    public String getDireccionPedido() {
        return DireccionPedido;
    }

    public void setDireccionPedido(String direccionPedido) {
        DireccionPedido = direccionPedido;
    }

    public double getPrecioTotalPedido() {

        return PrecioTotalPedido;
    }

    public void setPrecioTotalPedido(double precioTotalPedido) {

        PrecioTotalPedido = precioTotalPedido;
    }

    public com.proyecto.wasa.proyectoandroid.Entidades.Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(com.proyecto.wasa.proyectoandroid.Entidades.Usuario usuario) {
        Usuario = usuario;
    }

    public com.proyecto.wasa.proyectoandroid.Entidades.PedidoDetalle getPedidoDetalle() {
        return PedidoDetalle;
    }

    public void setPedidoDetalle(com.proyecto.wasa.proyectoandroid.Entidades.PedidoDetalle pedidoDetalle) {
        PedidoDetalle = pedidoDetalle;
    }

    public com.proyecto.wasa.proyectoandroid.Entidades.PedidoSeguimiento getPedidoSeguimiento() {
        return PedidoSeguimiento;
    }

    public void setPedidoSeguimiento(com.proyecto.wasa.proyectoandroid.Entidades.PedidoSeguimiento pedidoSeguimiento) {
        PedidoSeguimiento = pedidoSeguimiento;
    }
}
