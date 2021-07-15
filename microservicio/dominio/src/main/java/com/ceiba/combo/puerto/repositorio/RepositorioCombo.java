package com.ceiba.combo.puerto.repositorio;

import com.ceiba.combo.modelo.entidad.Combo;

/**
 * Interfaz que representa el repositorio de la entidad combo
 */
public interface RepositorioCombo {
    /**
     * Método para crear un nuevo combo
     * @param combo
     * @return
     */
    public Long crear(Combo combo);

    /**
     * Método para actualizar un combo
     * @param combo
     */
    public void actualizar(Combo combo);

    /**
     * Método para eliminar un combo
     * @param id
     */
    public void eliminar(Long id);

    /**
     * Método para verificar la existencia de un combo
     * @param id
     * @return
     */
    public boolean existe(Long id);

    /**
     * Método para obtener el precio o valor de un combo
     * @param id
     * @return
     */
    public double obtenerPrecioCombo(Long id);
}
