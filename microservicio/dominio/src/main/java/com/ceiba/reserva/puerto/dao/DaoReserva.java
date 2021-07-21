package com.ceiba.reserva.puerto.dao;

import com.ceiba.reserva.modelo.dto.DtoReserva;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DaoReserva {

    List<DtoReserva> listar();

    /**
     * Método para encontrar cuentas reservas hay en una fecha especifica
     * @param localDate
     * @return
     */
    public int numeroReservasParaUnaFecha(LocalDate localDate);

    /**
     * Método para encontrar la última fehca de expiración de una reserva dado la identificación de una persona
     * @param idPersonaReserva
     * @return
     */
    public LocalDateTime encontrarUltimaFechaReservaPorIdPersona(String idPersonaReserva);

    /**
     * Método para encontrar la fecha de creación de una reserva
     * @param id
     * @return
     */
    public LocalDateTime encontrarFechaCreacionReserva(Long id);

    public DtoReserva obtenerReservaPorId(Long id);

}