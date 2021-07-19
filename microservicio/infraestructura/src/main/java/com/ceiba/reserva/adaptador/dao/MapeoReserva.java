package com.ceiba.reserva.adaptador.dao;


import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {
    @Override
    public DtoReserva mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");
        Long comboId = resultSet.getLong("combo_id");
        double precioFinalReserva = resultSet.getDouble("precio");
        LocalDateTime fechaCreacionReserva = extraerLocalDateTime(resultSet,"fecha_creacion");
        LocalDateTime fechaReserva = extraerLocalDateTime(resultSet,"fecha_reserva");
        LocalDateTime fechaExpiracionReserva = extraerLocalDateTime(resultSet,"fecha_expiracion");
        String nombrePersonaReserva = resultSet.getString("nombre_persona");
        String idPersonaReserva = resultSet.getString("id_persona");
        String telefonoPersonaReserva = resultSet.getString("telefono_persona");
        String direccionPersonaReserva = resultSet.getString("direccion_persona");


        return new DtoReserva(id, comboId,precioFinalReserva, fechaCreacionReserva,fechaReserva,fechaExpiracionReserva,nombrePersonaReserva,idPersonaReserva,telefonoPersonaReserva,direccionPersonaReserva);

    }
}