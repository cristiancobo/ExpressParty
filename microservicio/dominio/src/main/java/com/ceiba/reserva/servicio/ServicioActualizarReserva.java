package com.ceiba.reserva.servicio;

import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ServicioActualizarReserva {

    private final RepositorioReserva repositorioReserva;


    public ServicioActualizarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public void ejecutar(Reserva reserva){
        if(estaVigenteParaActualizarReserva(reserva.getId())){
            this.repositorioReserva.actualizar(reserva);
        }else{
            System.out.println("EJEJEJE");
        }

    }

    public boolean estaVigenteParaActualizarReserva(Long id){
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