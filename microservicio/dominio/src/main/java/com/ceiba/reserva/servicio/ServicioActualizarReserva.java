package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionNoExisteReserva;
import com.ceiba.dominio.excepcion.ExcepcionTiempoExcedido;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ServicioActualizarReserva {

    private final RepositorioReserva repositorioReserva;
    private final DaoReserva daoReserva;
    private  static final String RESERVA_NO_EXISTE = "La reserva que intenta actualizar no existe";
    private static final String TIEMPO_EXCEDIDO = "El tiempo para realizar la cancelación excedió";
    private static final int TIEMPO_MINUTOS_MAXIMO_CANCELACION_RESERVA = 30;


    public ServicioActualizarReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
    }

    public void ejecutar(Reserva reserva){

            verficarExistenciaReserva(reserva);
            estaVigenteParaActualizarReserva(reserva.getId());
            this.repositorioReserva.actualizar(reserva);


    }

    private void verficarExistenciaReserva(Reserva reserva){
        Long id  = reserva.getId();
        boolean existe = this.repositorioReserva.existe(id);
        if(!existe){
            throw new ExcepcionNoExisteReserva(RESERVA_NO_EXISTE);
        }
    }


    private void estaVigenteParaActualizarReserva(Long id){
        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaReservacion = this.daoReserva.encontrarFechaCreacionReserva(id);
        LocalDateTime tiempo = LocalDateTime.from( fechaReservacion );
        System.out.println(tiempo);
        long minutosTranscurridos = tiempo.until( fechaActual, ChronoUnit.MINUTES );
        if(minutosTranscurridos >= TIEMPO_MINUTOS_MAXIMO_CANCELACION_RESERVA){
            throw new ExcepcionTiempoExcedido(TIEMPO_EXCEDIDO);
        }

    }

}