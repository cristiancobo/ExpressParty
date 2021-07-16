package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Interfaz que representa el repositorio de una reserva
 */
public interface RepositorioReserva {
    /**
     * Método para crear una reserva
     * @param reserva
     * @return
     */
    public Long crear(Reserva reserva);

    /**
     * Método para actualizar un reserva
     * @param reserva
     */
    public void actualizar(Reserva reserva);

    /**
     * Método para eliminar un reserva
     * @param id
     */
    public void eliminar(Long id);

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
     * Método para verificar si existe o no una reserva
     * @param idPersonaReserva
     * @return
     */
    public boolean existeReservaConIdPersona(String idPersonaReserva);

    /**
     * Método para encontrar la fecha de creación de una reserva
     * @param id
     * @return
     */
    public LocalDateTime encontrarFechaCreacionReserva(Long id);

    /**
     * Método para verificar si existe una reserva o no por su id
     * @param id
     * @return
     */
    public boolean existe(Long id);


}