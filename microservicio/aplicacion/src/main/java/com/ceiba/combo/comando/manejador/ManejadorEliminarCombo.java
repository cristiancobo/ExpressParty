package com.ceiba.combo.comando.manejador;

import com.ceiba.combo.servicio.ServicioEliminarCombo;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarCombo  implements ManejadorComando<Long> {

    private final ServicioEliminarCombo servicioEliminarCombo;

    public ManejadorEliminarCombo(ServicioEliminarCombo servicioEliminarCombo) {
        this.servicioEliminarCombo = servicioEliminarCombo;
    }

    @Override
    public void ejecutar(Long id) {
        this.servicioEliminarCombo.ejecutar(id);
    }
}