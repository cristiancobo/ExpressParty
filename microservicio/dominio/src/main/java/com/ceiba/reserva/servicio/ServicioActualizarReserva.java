package com.ceiba.reserva.servicio;

import com.ceiba.combo.puerto.dao.DaoCombo;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.dominio.excepcion.ExcepcionComboNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionNoExisteReserva;
import com.ceiba.dominio.excepcion.ExcepcionTiempoExcedido;
import com.ceiba.dominio.util.horarios.GestionHorarios;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class ServicioActualizarReserva {

    private final RepositorioReserva repositorioReserva;
    private final DaoReserva daoReserva;
    private final DaoCombo daoCombo;
    private final RepositorioCombo repositorioCombo;
    private  static final String RESERVA_NO_EXISTE = "La reserva que intenta actualizar no existe";
    private static final String TIEMPO_EXCEDIDO = "El tiempo para realizar la actualizacion excedió";
    private static final int TIEMPO_MINUTOS_MAXIMO_CANCELACION_RESERVA = 30;
    private static final String COMBO_NO_EXISTE = "El combo seleccionado no existe";
    private static final int NUMERO_MAXIMO_DIAS_RESERVACION = 2;
    private static final double DESCUENTO_POR_VARIAS_RESERVAS = 0.15;
    private static final double SOBRE_COSTO_FIN_SEMANA = 0.07;
    private static final double SOBRE_COSTO_FESTIVO = 0.07;
    private static final double DESCUENTO_POR_DIA_HABIL = 0.05;

    public ServicioActualizarReserva(RepositorioReserva repositorioReserva, DaoReserva daoReserva, DaoCombo daoCombo, RepositorioCombo repositorioCombo) {
        this.repositorioReserva = repositorioReserva;
        this.daoReserva = daoReserva;
        this.daoCombo = daoCombo;
        this.repositorioCombo = repositorioCombo;
    }

    public void ejecutar(Reserva reserva){

            verficarExistenciaReserva(reserva);
            estaVigenteParaActualizarReserva(reserva.getId());
            establecerFechaDeCreacionReserva( reserva);
            generarPrecioBaseReservaCombo(reserva);
            establecerFechaDeExpiracionReserva(reserva);
            validarDiaHabilParaDescuento(reserva);
            this.repositorioReserva.actualizar(reserva);
    }
    private LocalDateTime establecerFechaDeCreacionReserva(Reserva reserva){
        reserva.establecerFechaCreacionReserva(daoReserva.encontrarFechaCreacionReserva(reserva.getId()));
        return reserva.getFechaCreacionReserva();
    }

    private void validarDiaHabilParaDescuento(Reserva reserva){
        Long idComboActual = daoReserva.obtenerReservaPorId(reserva.getId()).getIdCombo();
        if(idComboActual != reserva.getIdCombo()){
            double precioBase = this.daoCombo.obtenerPrecioCombo(reserva.getIdCombo());
            reserva.establecerPrecioComboReserva(precioBase);

        LocalDateTime fechaActual = LocalDateTime.now();
        GestionHorarios gestionHorarios = new GestionHorarios();
        boolean esDiaFestivo = gestionHorarios.esFestivo(fechaActual.getMonthValue()-1,
                fechaActual.getDayOfMonth());
        boolean esDiaHabil = (fechaActual.getDayOfWeek() != DayOfWeek.SATURDAY && fechaActual.getDayOfWeek() != DayOfWeek.SUNDAY)  && !esDiaFestivo;
        if (esDiaHabil){
            reserva.establecerPrecioComboReserva(reserva.getPrecioFinalReserva()-(reserva.getPrecioFinalReserva()*DESCUENTO_POR_DIA_HABIL));
        }else if(esDiaFestivo){
            reserva.establecerPrecioComboReserva(reserva.getPrecioFinalReserva()+(reserva.getPrecioFinalReserva()*SOBRE_COSTO_FESTIVO));
        }else {
            reserva.establecerPrecioComboReserva(reserva.getPrecioFinalReserva()+(reserva.getPrecioFinalReserva()*SOBRE_COSTO_FIN_SEMANA));
        }
        }
    }

    private void generarPrecioBaseReservaCombo(Reserva reserva){
        Long idComboActual = daoReserva.obtenerReservaPorId(reserva.getId()).getIdCombo();
        if(this.repositorioCombo.existe(reserva.getIdCombo())){
            if(idComboActual != reserva.getIdCombo()){
                double precioBase = this.daoCombo.obtenerPrecioCombo(reserva.getIdCombo());
                reserva.establecerPrecioComboReserva(precioBase);
            }else{
                reserva.establecerPrecioComboReserva(daoReserva.obtenerReservaPorId(reserva.getId()).getPrecioFinalReserva());
            }


        }else{
            throw new ExcepcionComboNoExiste(COMBO_NO_EXISTE);
        }

    }

    private LocalDateTime establecerFechaDeExpiracionReserva(Reserva reserva){
        GestionHorarios gestionHorarios = new GestionHorarios();
        Date fechaReservacionConvertida = java.util.Date
                .from(reserva.getFechaReservacion().atZone(ZoneId.systemDefault())
                        .toInstant());
        reserva.establecerFechaExpiracionReserva(gestionHorarios.calcularSiguienteDiaHabil(fechaReservacionConvertida,NUMERO_MAXIMO_DIAS_RESERVACION));
        return reserva.getFechaExpiracion();
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
        long minutosTranscurridos = tiempo.until( fechaActual, ChronoUnit.MINUTES );
        if(minutosTranscurridos >= TIEMPO_MINUTOS_MAXIMO_CANCELACION_RESERVA){
            throw new ExcepcionTiempoExcedido(TIEMPO_EXCEDIDO);
        }

    }

}