package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;



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
     * Método para verificar si existe o no una reserva
     * @param idPersonaReserva
     * @return
     */
    public boolean existeReservaConIdPersona(String idPersonaReserva);



    /**
     * Método para verificar si existe una reserva o no por su id
     * @param id
     * @return
     */
    public boolean existe(Long id);


}