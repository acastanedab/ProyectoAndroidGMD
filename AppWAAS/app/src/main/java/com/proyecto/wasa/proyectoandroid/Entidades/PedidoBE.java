package com.proyecto.wasa.proyectoandroid.Entidades;

import java.io.Serializable;

/**
 * Created by AngelEloy on 28/02/2017.
 */

public class PedidoBE implements Serializable {
    private Integer CodigoArticulo;
    private String NombreArticulo;
    private String DescripcionArticulo;
    private Double PrecioArticulo;
    private String Estado;


    public Integer getCodigoArticulo() {
        return CodigoArticulo;
    }

    public void setCodigoArticulo(Integer codigoArticulo) {
        CodigoArticulo = codigoArticulo;
    }

    public String getNombreArticulo() {
        return NombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        NombreArticulo = nombreArticulo;
    }

    public String getDescripcionArticulo() {
        return DescripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        DescripcionArticulo = descripcionArticulo;
    }

    public Double getPrecioArticulo() {
        return PrecioArticulo;
    }

    public void setPrecioArticulo(Double precioArticulo) {
        PrecioArticulo = precioArticulo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
