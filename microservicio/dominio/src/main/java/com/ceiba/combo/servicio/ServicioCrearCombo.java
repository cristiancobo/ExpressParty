package com.ceiba.combo.servicio;

import com.ceiba.combo.modelo.entidad.Combo;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;

/**
 * Clase que representa el servicio para crear un nuevo combo
 */
public class ServicioCrearCombo {

    private final RepositorioCombo repositorioCombo;


    public ServicioCrearCombo(RepositorioCombo repositorioCombo) {
        this.repositorioCombo = repositorioCombo;
    }

    /**
     * Método que ejecuta la acción para crear un nuevo combo
     * @param combo
     * @return
     */
    public Long ejecutar(Combo combo){
        return this.repositorioCombo.crear(combo);
    }
}
