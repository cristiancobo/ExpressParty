package com.ceiba.combo.adaptador.dao;

import com.ceiba.combo.modelo.dto.DtoCombo;
import com.ceiba.combo.puerto.dao.DaoCombo;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase que representa el dao mysql de un combo
 */
@Component
public class DaoComboMysql implements DaoCombo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "combo", value = "listar")
    private static String sqlListar;


    public DaoComboMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoCombo> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoCombo());
    }
}
