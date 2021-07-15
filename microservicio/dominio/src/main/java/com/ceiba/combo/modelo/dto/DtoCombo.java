package com.ceiba.combo.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * Clase que representa el dto de la entidad combo
 */
public class DtoCombo {

    private Long id;
    private String nombre;
    private double precio;
}
