package com.proyecto.wasa.proyectoandroid.Entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by GMD on 28/02/2017.
 */

public class PedidoSeguimiento implements Serializable {
    private Date fechaPedidoSeguimiento;
    private String latitudPedidoSeguimiento;
    private String longitudPedidoSeguimiento;

    public PedidoSeguimiento() {

    }

    public Date getFechaPedidoSeguimiento() {
        return fechaPedidoSeguimiento;
    }

    public void setFechaPedidoSeguimiento(Date fechaPedidoSeguimiento) {
        this.fechaPedidoSeguimiento = fechaPedidoSeguimiento;
    }

    public String getLatitudPedidoSeguimiento() {
        return latitudPedidoSeguimiento;
    }

    public void setLatitudPedidoSeguimiento(String latitudPedidoSeguimiento) {
        this.latitudPedidoSeguimiento = latitudPedidoSeguimiento;
    }

    public String getLongitudPedidoSeguimiento() {
        return longitudPedidoSeguimiento;
    }

    public void setLongitudPedidoSeguimiento(String longitudPedidoSeguimiento) {
        this.longitudPedidoSeguimiento = longitudPedidoSeguimiento;
    }

}
