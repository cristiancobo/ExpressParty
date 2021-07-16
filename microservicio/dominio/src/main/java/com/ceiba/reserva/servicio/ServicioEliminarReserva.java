package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ServicioEliminarReserva {

    private final RepositorioReserva repositorioReserva;


    public ServicioEliminarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public void ejecutar(Long id) {

        if(estaVigenteParaCancelarReserva(id)){
            this.repositorioReserva.eliminar(id);
        }else{
            System.out.println("EJEJEJE");
        }

    }

    public boolean estaVigenteParaCancelarReserva(Long id){
        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaReservacion = this.repositorioReserva.encontrarFechaCreacionReserva(id);
        LocalDateTime tiempo = LocalDateTime.from( fechaReservacion );
        long minutosTranscurridos = tiempo.until( fechaActual, ChronoUnit.MINUTES );
        if(minutosTranscurridos < 30 ){
            return true;
        }
        return false;
    }

}
