package com.proyecto.wasa.proyectoandroid;

/**
 * Created by SISTEMAS on 19/02/2017.
 */

public class ListaGenerica {
    private String name;
    private String values;

    public ListaGenerica() {
    }

    public ListaGenerica(String name, String values ) {
        this.name = name;
        this.values = values;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValues() {
        return values;
    }
    public void setValues(String values) {
        this.values = values;
    }

}
