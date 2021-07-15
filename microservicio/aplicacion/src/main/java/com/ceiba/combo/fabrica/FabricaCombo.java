package com.ceiba.combo.fabrica;

import com.ceiba.combo.ComandoCombo;
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