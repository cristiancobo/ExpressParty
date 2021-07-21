package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionNoExisteReserva;
import com.ceiba.dominio.excepcion.ExcepcionTiempoExcedido;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ServicioActualizarReserva {

    private final RepositorioReserva repositorioReserva;
    private  static final String RESERVA_NO_EXISTE = "La reserva que intenta eliminar no existe";
    private static final String TIEMPO_EXCEDIDO = "El tiempo para realizar la cancelación excedió";
    private static final int TIEMPO_MINUTOS_MAXIMO_CANCELACION_RESERVA = 30;


    public ServicioActualizarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public void ejecutar(Reserva reserva){

            verficiarExistenciaReserva(reserva.getId());
           // estaVigenteParaActualizarReserva(reserva.getId());
            this.repositorioReserva.actualizar(reserva);


    }

    public void verficiarExistenciaReserva(Long id){
        if(!this.repositorioReserva.existe(id)){
            throw new ExcepcionNoExisteReserva(RESERVA_NO_EXISTE);
        }
    }
    /**
    public void estaVigenteParaActualizarReserva(Long id){
        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaReservacion = this.repositorioReserva.encontrarFechaCreacionReserva(id);
        LocalDateTime tiempo = LocalDateTime.from( fechaReservacion );
        long minutosTranscurridos = tiempo.until( fechaActual, ChronoUnit.MINUTES );
        if(minutosTranscurridos >= TIEMPO_MINUTOS_MAXIMO_CANCELACION_RESERVA){
            throw new ExcepcionTiempoExcedido(TIEMPO_EXCEDIDO);
        }

    }
     **/
}