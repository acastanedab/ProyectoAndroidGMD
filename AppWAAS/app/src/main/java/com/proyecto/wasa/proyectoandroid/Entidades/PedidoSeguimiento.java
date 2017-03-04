package com.proyecto.wasa.proyectoandroid.Entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by GMD on 28/02/2017.
 */

public class PedidoSeguimiento implements Serializable {
    private String FechaPedidoSeguimiento;
    private String LatitudPedidoSeguimiento;
    private String LongitudPedidoSeguimiento;



    public PedidoSeguimiento() {

    }


    public String getFechaPedidoSeguimiento() {
        return FechaPedidoSeguimiento;
    }

    public void setFechaPedidoSeguimiento(String fechaPedidoSeguimiento) {
        FechaPedidoSeguimiento = fechaPedidoSeguimiento;
    }

    public String getLatitudPedidoSeguimiento() {
        return LatitudPedidoSeguimiento;
    }

    public void setLatitudPedidoSeguimiento(String latitudPedidoSeguimiento) {
        LatitudPedidoSeguimiento = latitudPedidoSeguimiento;
    }

    public String getLongitudPedidoSeguimiento() {
        return LongitudPedidoSeguimiento;
    }

    public void setLongitudPedidoSeguimiento(String longitudPedidoSeguimiento) {
        LongitudPedidoSeguimiento = longitudPedidoSeguimiento;
    }
}
