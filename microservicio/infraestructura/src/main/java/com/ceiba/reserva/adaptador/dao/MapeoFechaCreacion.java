package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoFechaCreacion implements RowMapper<LocalDateTime>, MapperResult {
    @Override
    public LocalDateTime mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return extraerLocalDateTime(resultSet,"fecha_creacion");
    }
}
