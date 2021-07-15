package com.ceiba.combo.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Clase que representa un combo de la entidad combo
 */
public class Combo {
    private final String NOMBRE_COMBO_OBLIGATORIO = "El nombre del combo es obligatorio";
    private final String PRECIO_COMBO_OBLIGATORIO = "El precio del combo es obligatorio";

    private Long id;
    private String nombre;
    private double precio;

    public Combo(Long id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
}
