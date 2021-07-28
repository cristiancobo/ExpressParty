package com.ceiba.combo.adaptador.dao;

import com.ceiba.combo.modelo.dto.DtoCombo;
import com.ceiba.combo.puerto.dao.DaoCombo;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    @SqlStatement(namespace = "combo", value = "encontrar_combo_por_id")
    private static String sqlEncontrarComboPorId;


    @SqlStatement(namespace="combo", value="obtener_precio_combo")
    private static String sqlObtenerPrecioCombo;

    public DaoComboMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoCombo> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoCombo());
    }

    public DtoCombo encontrarComboPorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlEncontrarComboPorId,paramSource ,new MapeoCombo());
    }

    @Override
    public double obtenerPrecioCombo(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPrecioCombo,paramSource, Double.class);
    }

}
