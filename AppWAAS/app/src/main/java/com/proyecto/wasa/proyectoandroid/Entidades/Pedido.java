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
    private double PrecioTotalPedido;
    private Usuario Usuario;
    private PedidoDetalle PedidoDetalle;
    private PedidoSeguimiento PedidoSeguimiento;


    public  Pedido() {
        setPedidoDetalle(new PedidoDetalle());
        setPedidoSeguimiento(new PedidoSeguimiento());
    }

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

    public double getPrecioTotalPedido() {

        return PrecioTotalPedido;
    }

    public void setPrecioTotalPedido(double precioTotalPedido) {

        PrecioTotalPedido = precioTotalPedido;
    }

    public Usuario getUsuario() {

        return Usuario;
    }

    public void setUsuario(Usuario usuario) {

        Usuario = usuario;
    }

    public PedidoDetalle getPedidoDetalle() {

        return PedidoDetalle;
    }

    public void setPedidoDetalle(PedidoDetalle pedidoDetalle) {

        PedidoDetalle = pedidoDetalle;
    }

    public PedidoSeguimiento getPedidoSeguimiento() {

        return PedidoSeguimiento;
    }

    public void setPedidoSeguimiento(PedidoSeguimiento pedidoSeguimiento) {
        PedidoSeguimiento = pedidoSeguimiento;
    }
}
