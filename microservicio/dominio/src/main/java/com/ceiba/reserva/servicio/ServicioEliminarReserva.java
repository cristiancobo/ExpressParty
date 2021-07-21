package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionNoExisteReserva;
import com.ceiba.dominio.excepcion.ExcepcionTiempoExcedido;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class ServicioEliminarReserva {

    private final RepositorioReserva repositorioReserva;
    private final DaoReserva daoReserva;
    private static final String RESERVA_NO_EXISTE = "La reserva que intenta eliminar no existe";
    private static  final String TIEMPO_EXCEDIDO = "El tiempo para realizar la cancelación excedió";
    private static final int TIEMPO_MINUTOS_MAXIMO_CANCELACION_RESERVA = 30;


    public ServicioEliminarReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
    }

    public void ejecutar(Long id) {
           verficarExistenciaReserva(id);
           estaVigenteParaCancelarReserva(id);
            this.repositorioReserva.eliminar(id);
    }

    private void estaVigenteParaCancelarReserva(Long id){

        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaReservacion = this.daoReserva.encontrarFechaCreacionReserva(id);
        LocalDateTime tiempo = LocalDateTime.from( fechaReservacion );
        long minutosTranscurridos = tiempo.until( fechaActual, ChronoUnit.MINUTES );
        if(minutosTranscurridos >= TIEMPO_MINUTOS_MAXIMO_CANCELACION_RESERVA ){
            throw new ExcepcionTiempoExcedido(TIEMPO_EXCEDIDO);
        }
    }

    private void verficarExistenciaReserva(Long id){
        if(!this.repositorioReserva.existe(id)){
            throw new ExcepcionNoExisteReserva(RESERVA_NO_EXISTE);
        }
    }

}
