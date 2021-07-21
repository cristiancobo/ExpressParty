package com.ceiba.combo.consulta;

import com.ceiba.combo.modelo.dto.DtoCombo;
import com.ceiba.combo.puerto.dao.DaoCombo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorObtenerCombo {

    private final DaoCombo daoCombo;

    public ManejadorObtenerCombo(DaoCombo daoCombo) {
        this.daoCombo = daoCombo;
    }

    public DtoCombo ejecutar(Long id){
       return daoCombo.encontrarComboPorId(id);
    }
}
