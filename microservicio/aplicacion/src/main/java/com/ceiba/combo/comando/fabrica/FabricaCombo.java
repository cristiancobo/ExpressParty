package com.ceiba.combo.comando.fabrica;

import com.ceiba.combo.comando.ComandoCombo;
import com.ceiba.combo.modelo.entidad.Combo;
import org.springframework.stereotype.Component;

@Component
public class FabricaCombo {

    public Combo crear(ComandoCombo comandoCombo){
        return new Combo(comandoCombo.getId(),
                comandoCombo.getNombre(),
                comandoCombo.getPrecio()
        );
    }
}