package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoNumeroReservasParaUnaFecha implements RowMapper<Integer>, MapperResult {
    @Override
    public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        return  resultSet.getInt("count(*)");
    }
}
