package com.ceiba.combo.adaptador.repositorio;


import com.ceiba.combo.modelo.entidad.Combo;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * Clase que representa el repositorio mysql de un combo
 */
@Repository
public class RepositorioComboMysql implements RepositorioCombo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="combo", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="combo", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="combo", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace="combo", value="existe")
    private static String sqlExiste;


    public RepositorioComboMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Combo combo) {
        return this.customNamedParameterJdbcTemplate.crear(combo,sqlCrear);
    }

    @Override
    public void actualizar(Combo combo) {
        this.customNamedParameterJdbcTemplate.actualizar(combo,sqlActualizar);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar,mapSqlParameterSource);
    }

    @Override
    public boolean existe(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);

    }


}