package com.ceiba.combo.servicio;

import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
/**
 * Clase que representa el servicio para eliminar un combo
 */
public class ServicioEliminarCombo {

    private final RepositorioCombo repositorioCombo;

    public ServicioEliminarCombo(RepositorioCombo repositorioCombo) {
        this.repositorioCombo = repositorioCombo;
    }
    /**
     * Método que ejecuta la acción para eliminar un combo
     * @param id
     * @return
     */
    public void ejecutar (Long id){
        this.repositorioCombo.eliminar(id);
    }
}
