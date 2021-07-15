package com.ceiba.combo.servicio;

import com.ceiba.combo.modelo.entidad.Combo;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;

/**
 * Clase que representa el servicio para actualizar un combo
 */
public class ServicioActualizarCombo {

    private final RepositorioCombo repositorioCombo;

    public ServicioActualizarCombo(RepositorioCombo repositorioCombo) {
        this.repositorioCombo = repositorioCombo;
    }

    /**
     * Método que ejecuta la acción para actualizar un combo
     * @param combo
     */
    public void ejecutar(Combo combo){
        this.repositorioCombo.actualizar(combo);
    }
}
