package com.ceiba.combo.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.combo.comando.ComandoCombo;
import com.ceiba.combo.comando.fabrica.FabricaCombo;
import com.ceiba.combo.modelo.entidad.Combo;
import com.ceiba.combo.servicio.ServicioCrearCombo;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearCombo implements ManejadorComandoRespuesta<ComandoCombo, ComandoRespuesta<Long>> {

    private final FabricaCombo fabricaCombo;
    private final ServicioCrearCombo servicioCrearCombo;

    public ManejadorCrearCombo(FabricaCombo fabricaCombo, ServicioCrearCombo servicioCrearCombo) {
        this.fabricaCombo = fabricaCombo;
        this.servicioCrearCombo = servicioCrearCombo;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoCombo comandoCombo) {
        Combo combo = this.fabricaCombo.crear(comandoCombo);
        return new ComandoRespuesta<>(this.servicioCrearCombo.ejecutar(combo));
    }
}
