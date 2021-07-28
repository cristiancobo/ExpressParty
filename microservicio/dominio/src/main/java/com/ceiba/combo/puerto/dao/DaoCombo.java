package com.ceiba.combo.puerto.dao;

import com.ceiba.combo.modelo.dto.DtoCombo;

import java.util.List;

/**
 * Interfaz que representa un dao para la entidad combo
 */
public interface DaoCombo {
    /**
     * Método para listar todos los combos disponibles
     * @return
     */
    List<DtoCombo> listar();

    DtoCombo encontrarComboPorId(Long id);

    /**
     * Método para obtener el precio o valor de un combo
     * @param id
     * @return
     */
    public double obtenerPrecioCombo(Long id);
}
