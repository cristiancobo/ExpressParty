package com.ceiba.reserva.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public class RepositorioReservaMysql implements RepositorioReserva {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="reserva", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="reserva", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="reserva", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace="reserva", value="numero_reservas_para_fecha")
    private static String sqlNumeroReservasParaFecha;

    @SqlStatement(namespace="reserva", value="encontrar_ultima_fecha_reserva_por_id_persona")
    private static String sqlUltimaFechaReservaPorIdPersona;

    @SqlStatement(namespace="reserva", value="existe_reserva_por_id_persona")
    private static String sqlExisteReservaIdPersona;

    @SqlStatement(namespace="reserva", value="encontrar_fecha_creacion_reserva")
    private static String sqlEncontrarFechaCreacionReserva;

    public RepositorioReservaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Reserva reserva) {
        return this.customNamedParameterJdbcTemplate.crear(reserva,sqlCrear);
    }

    @Override
    public void actualizar(Reserva reserva) {
        this.customNamedParameterJdbcTemplate.actualizar(reserva,sqlActualizar);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id",id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar,mapSqlParameterSource);

    }

    @Override
    public int numeroReservasParaUnaFecha(LocalDate fecha) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("fecha",fecha);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlNumeroReservasParaFecha,paramSource, Integer.class);
    }

    @Override
    public LocalDateTime encontrarUltimaFechaReservaPorIdPersona(String idPersonaReserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idPersonaReserva",idPersonaReserva);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlUltimaFechaReservaPorIdPersona,paramSource, LocalDateTime.class);

    }

    @Override
    public boolean existeReservaConIdPersona(String idPersonaReserva) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idPersonaReserva",idPersonaReserva);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteReservaIdPersona,paramSource, Boolean.class);

    }

    @Override
    public LocalDateTime encontrarFechaCreacionReserva(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id",id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlEncontrarFechaCreacionReserva,paramSource, LocalDateTime.class);
    }

}