package com.ceiba.combo.modelo.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
/**
 * Clase que representa un combo de la entidad combo
 */
public class Combo {
    private static final String NOMBRE_COMBO_OBLIGATORIO = "El nombre del combo es obligatorio";
    private static final String PRECIO_COMBO_OBLIGATORIO = "El precio del combo es obligatorio";
    private static final String PRECIO_NEGATIVO = "El precio de un combo no puede ser negativo";
    private static final int NOMBRE_LONGITUD_MINIMA = 1;
    private static final int NOMBRE_LOGITUD_MAXIMA = 20;
    private static final String NOMBRE_NO_CUMPLE_CON_LOGITUD_MINIMA = "El nombre no cumple la longitud mínima";
    private static final String NOMBRE_NO_CUMPLE_CON_LOGITUD_MAXIMA = "El nombre no cumple la longitud máxima";

    private Long id;
    private String nombre;
    private Double precio;

    public Combo(Long id, String nombre, Double precio) {
        validarObligatorio(nombre,NOMBRE_COMBO_OBLIGATORIO);
        validarObligatorio(precio,PRECIO_COMBO_OBLIGATORIO);
        validarPositivo(precio,PRECIO_NEGATIVO);
        validarLongitudMinima(nombre,NOMBRE_LONGITUD_MINIMA,NOMBRE_NO_CUMPLE_CON_LOGITUD_MINIMA);
        validarLongitudMaxima(nombre,NOMBRE_LOGITUD_MAXIMA,NOMBRE_NO_CUMPLE_CON_LOGITUD_MAXIMA);
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }



}
