package com.ceiba.reserva.servicio;

import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.dominio.excepcion.ExcepcionComboNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionTopeNumeroReservasFecha;
import com.ceiba.dominio.util.horarios.GestionHorarios;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ServicioCrearReserva {

    private final RepositorioReserva repositorioReserva;
    private final RepositorioCombo repositorioCombo;
    private final DaoReserva daoReserva;
    private static final String TOPE_NUMERO_RESERVAS = "No es posible realizar una reserva para esta fecha. Elige una nueva";
    private static final String COMBO_NO_EXISTE = "El combo seleccionado no existe";
    private static final int NUMERO_MAXIMO_DIAS_RESERVACION = 2;
    private static final double SOBRE_COSTO_FIN_SEMANA = 0.07;
    private static final double SOBRE_COSTO_FESTIVO = 0.07;
    private static final double DESCUENTO_POR_VARIAS_RESERVAS = 0.15;
    private static final double DESCUENTO_POR_DIA_HABIL = 0.05;
    private static final int NUMERO_MAXIMO_RESERVAS_PARA_UNA_FECHA = 4;

    public ServicioCrearReserva(RepositorioReserva repositorioReserva, RepositorioCombo repositorioCombo, DaoReserva daoReserva) {
        this.repositorioReserva = repositorioReserva;
        this.repositorioCombo = repositorioCombo;

        this.daoReserva = daoReserva;
    }
    public Long ejecutar(Reserva reserva){
        generarPrecioBaseReservaCombo(reserva);
        establecerFechaDeCreacionReserva(reserva);
        establecerFechaDeExpiracionReserva(reserva);
        validarDiaHabilParaDescuento(reserva);
        verficarDescuentoPorVariasReservas(reserva);
        verificarCantidadReservasParaFecha(reserva);
        return this.repositorioReserva.crear(reserva);
    }
    private void generarPrecioBaseReservaCombo(Reserva reserva){
         if(this.repositorioCombo.existe(reserva.getIdCombo())){
             double precioBase = this.repositorioCombo.obtenerPrecioCombo(reserva.getIdCombo());
             reserva.establecerPrecioComboReserva(precioBase);
         }else{
            throw new ExcepcionComboNoExiste(COMBO_NO_EXISTE);
         }

    }
    public void validarDiaHabilParaDescuento(Reserva reserva){
        LocalDateTime fechaActual = LocalDateTime.now();
        GestionHorarios gestionHorarios = new GestionHorarios();
        boolean esDiaFestivo = gestionHorarios.esFestivo(Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        boolean esDiaHabil = (fechaActual.getDayOfWeek() != DayOfWeek.SATURDAY && fechaActual.getDayOfWeek() != DayOfWeek.SUNDAY)  && !esDiaFestivo;
        if (esDiaHabil){
            reserva.establecerPrecioComboReserva(reserva.getPrecioFinalReserva()-(reserva.getPrecioFinalReserva()*DESCUENTO_POR_DIA_HABIL));
        }else if(esDiaFestivo){
            reserva.establecerPrecioComboReserva(reserva.getPrecioFinalReserva()+(reserva.getPrecioFinalReserva()*SOBRE_COSTO_FESTIVO));
        }else {
            reserva.establecerPrecioComboReserva(reserva.getPrecioFinalReserva()+(reserva.getPrecioFinalReserva()*SOBRE_COSTO_FIN_SEMANA));
        }

    }
    public LocalDateTime establecerFechaDeCreacionReserva(Reserva reserva){
        reserva.establecerFechaCreacionReserva(LocalDateTime.now());
        return reserva.getFechaCreacionReserva();
    }
    public LocalDateTime establecerFechaDeExpiracionReserva(Reserva reserva){
        GestionHorarios gestionHorarios = new GestionHorarios();
        Date fechaReservacionConvertida = java.util.Date
                .from(reserva.getFechaReservacion().atZone(ZoneId.systemDefault())
                        .toInstant());
        reserva.establecerFechaExpiracionReserva(gestionHorarios.calcularSiguienteDiaHabil(fechaReservacionConvertida,NUMERO_MAXIMO_DIAS_RESERVACION));
        return reserva.getFechaExpiracion();
    }

    public int verificarCantidadReservasParaFecha(Reserva reserva){

        if( this.daoReserva.numeroReservasParaUnaFecha(reserva.getFechaReservacion().toLocalDate()) >= NUMERO_MAXIMO_RESERVAS_PARA_UNA_FECHA){
           throw  new ExcepcionTopeNumeroReservasFecha(TOPE_NUMERO_RESERVAS);
        }
        return this.daoReserva.numeroReservasParaUnaFecha(reserva.getFechaReservacion().toLocalDate());
    }

    public void verficarDescuentoPorVariasReservas(Reserva reserva){
        if(this.repositorioReserva.existeReservaConIdPersona(reserva.getIdPersonaReserva()))
        {
            LocalDateTime ultimaFechaReservacion = this.daoReserva.encontrarUltimaFechaReservaPorIdPersona(reserva.getIdPersonaReserva());
            LocalDateTime fechaActual = LocalDateTime.now();
            if(fechaActual.isBefore(ultimaFechaReservacion)){
                reserva.establecerPrecioComboReserva(reserva.getPrecioFinalReserva()-(reserva.getPrecioFinalReserva()*DESCUENTO_POR_VARIAS_RESERVAS));
                reserva.getPrecioFinalReserva();
            }


        }

    }
}
