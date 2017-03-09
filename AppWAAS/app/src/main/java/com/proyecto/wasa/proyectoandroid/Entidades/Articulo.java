package com.proyecto.wasa.proyectoandroid.Entidades;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AngelEloy on 28/02/2017.
 */
public class Articulo  implements Serializable {

    private long CodigoArticulo;
    private String NombreArticulo;
    private String DescripcionArticulo;
    private Double PrecioArticulo;
    private int Estado;

    public long getCodigoArticulo() {
        return CodigoArticulo;
    }

    public void setCodigoArticulo(long codigoArticulo) {
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


    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

    @Override
    public String toString(){
        return NombreArticulo;
    }

}
