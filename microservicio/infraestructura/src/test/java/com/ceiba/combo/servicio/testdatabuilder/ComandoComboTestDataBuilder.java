package com.ceiba.combo.servicio.testdatabuilder;

import com.ceiba.combo.comando.ComandoCombo;

import java.util.UUID;

public class ComandoComboTestDataBuilder {


    private Long id;
    private String nombre;
    private double precio;

    public ComandoComboTestDataBuilder() {
        this.nombre = "combo 1";
        this.precio = 100000.0;
    }

    public ComandoComboTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }
    public ComandoComboTestDataBuilder conPrecio(Double precio){
        this.precio = precio;
        return this;
    }
    public ComandoCombo build(){
        return new ComandoCombo(id,nombre,precio);
    }
}
