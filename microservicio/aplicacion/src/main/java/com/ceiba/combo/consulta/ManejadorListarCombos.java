package com.ceiba.combo.consulta;

import com.ceiba.combo.modelo.dto.DtoCombo;
import com.ceiba.combo.puerto.dao.DaoCombo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarCombos {

    public final DaoCombo daoCombo;

    public ManejadorListarCombos(DaoCombo daoCombo) {
        this.daoCombo = daoCombo;
    }

    public List<DtoCombo> ejecutar(){
        return this.daoCombo.listar();
    }
}
