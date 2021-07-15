package com.ceiba.combo.adaptador.dao;

import com.ceiba.combo.modelo.dto.DtoCombo;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que representa el mapeador de un combo
 */
public class MapeoCombo implements RowMapper<DtoCombo>, MapperResult {
    /**
     * Método para mapear a un dto
     * @param resultSet
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public DtoCombo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        Double precio = resultSet.getDouble("precio");
        return new DtoCombo(id,nombre,precio);
    }
}