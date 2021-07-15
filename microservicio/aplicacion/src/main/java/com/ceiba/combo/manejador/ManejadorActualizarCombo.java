package com.ceiba.combo.manejador;

import com.ceiba.combo.ComandoCombo;
import com.ceiba.combo.fabrica.FabricaCombo;
import com.ceiba.combo.modelo.entidad.Combo;
import com.ceiba.combo.servicio.ServicioActualizarCombo;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarCombo implements ManejadorComando<ComandoCombo> {

    private final FabricaCombo fabricaCombo;
    private final ServicioActualizarCombo servicioActualizarCombo;

    public ManejadorActualizarCombo(FabricaCombo fabricaCombo, ServicioActualizarCombo servicioActualizarCombo) {
        this.fabricaCombo = fabricaCombo;
        this.servicioActualizarCombo = servicioActualizarCombo;
    }

    @Override
    public void ejecutar(ComandoCombo comandoCombo) {
        Combo combo = this.fabricaCombo.crear(comandoCombo);
        this.servicioActualizarCombo.ejecutar(combo);
    }
}
