package com.ceiba.combo.modelo.entidad;

import lombok.Getter;
import lombok.Setter;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

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
        validarObligatorio(nombre,NOMBRE_COMBO_OBLIGATORIO);
        validarObligatorio(precio,PRECIO_COMBO_OBLIGATORIO);
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }



}
