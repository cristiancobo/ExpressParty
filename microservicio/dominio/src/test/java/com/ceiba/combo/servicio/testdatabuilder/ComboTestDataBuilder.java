package com.ceiba.combo.servicio.testdatabuilder;

import com.ceiba.combo.modelo.entidad.Combo;

public class ComboTestDataBuilder {

    private Long id;
    private String nombre;
    private Double precio;

    public ComboTestDataBuilder() {
        this.id = 1L;
        this.nombre = "combo 1";
        this.precio = 100000.0;
    }

    public ComboTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }
    public ComboTestDataBuilder conPrecio(Double precio){
        this.precio = precio;
        return this;
    }
    public Combo build(){
        return new Combo(id,nombre,precio);
    }


}
