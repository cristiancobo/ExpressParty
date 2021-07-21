package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DaoReservaMysql implements DaoReserva {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "reserva", value = "listar")
    private static String sqlListar;

    @SqlStatement(namespace = "reserva", value = "numero_reservas_para_fecha")
    private static String sqlNumeroReservasParaFecha;

    @SqlStatement(namespace = "reserva", value = "encontrar_ultima_fecha_reserva_por_id_persona")
    private static String sqlEncontrarUltimaFechaCreacionReserva;

    @SqlStatement(namespace = "reserva", value = "encontrar_fecha_creacion_reserva")
    private static String sqlEncontrarFechaCreacion;

    public DaoReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }
    @Override
    public List<DtoReserva> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoReserva());
    }
    @Override
    public int numeroReservasParaUnaFecha(LocalDate fecha) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha",fecha);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlNumeroReservasParaFecha,paramSource, new MapeoNumeroReservasParaUnaFecha());
    }
    @Override
    public LocalDateTime encontrarUltimaFechaReservaPorIdPersona(String idPersonaReserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idPersonaReserva",idPersonaReserva);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlEncontrarUltimaFechaCreacionReserva,paramSource, new MapeoUltimaFechaReservaPersona());
    }
    @Override
    public LocalDateTime encontrarFechaCreacionReserva(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlEncontrarFechaCreacion,paramSource, new MapeoFechaCreacion());
    }


}