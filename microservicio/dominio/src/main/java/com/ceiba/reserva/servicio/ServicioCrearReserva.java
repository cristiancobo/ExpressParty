package com.ceiba.reserva.servicio;

import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.dominio.util.horarios.GestionHorarios;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ServicioCrearReserva {

    private final RepositorioReserva repositorioReserva;
    private final RepositorioCombo repositorioCombo;
    private static final String TOPE_NUMERO_RESERVAS = "No es posible realizar una reserva para esta fecha. Elige una nueva";
    private static final int NUMERO_MAXIMO_DIAS_RESERVACION = 2;
    private static final double SOBRE_COSTO_FIN_SEMANA = 0.07;
    private static final double SOBRE_COSTO_FESTIVO = 0.07;
    private static final double DESCUENTO_POR_VARIAS_RESERVAS = 0.15;
    private static final double DESCUENTO_POR_DIA_HABIL = 0.05;







    public ServicioCrearReserva(RepositorioReserva repositorioReserva, RepositorioCombo repositorioCombo) {
        this.repositorioReserva = repositorioReserva;
        this.repositorioCombo = repositorioCombo;
    }

    public Long ejecutar(Reserva reserva){

        generarPrecioBaseReservaCombo(reserva);
        validarDiaFestivoParaSobreCosto(reserva);
        validarDiaFinSemanaParaSobreCosto(reserva);
        establecerFechaDeCreacionReserva(reserva);
        establecerFechaDeExpiracionReserva(reserva);
        validarDiaHabil(reserva);
        verficarDescuentoPorVariasReservas(reserva);
        if(verificarCantidadReservasParaFecha(reserva) >= 4){
          //TODO:  throw  new ExceptionNumeroReservaFecha(TOPE_NUMERO_RESERVAS);
        }
        return this.repositorioReserva.crear(reserva);
    }

    public void generarPrecioBaseReservaCombo(Reserva reserva){
        //TODO: Validacion existencia reserva
        double precioBase = this.repositorioCombo.obtenerPrecioCombo(reserva.getIdCombo());
        reserva.establecerPrecioBaseComboReserva(precioBase);
    }

    public boolean validarDiaFestivoParaSobreCosto(Reserva reserva){

        GestionHorarios gestionHorarios = new GestionHorarios();
        boolean esFestivo = gestionHorarios.esFestivo(Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        if(esFestivo){
            reserva.setPrecioFinalReserva(reserva.getPrecioFinalReserva() - reserva.getPrecioFinalReserva()*SOBRE_COSTO_FESTIVO);
            return true;
        }
        return false;
    }
    public void validarDiaHabil(Reserva reserva){
        boolean esDiaHabil = !validarDiaFestivoParaSobreCosto(reserva)  && !validarDiaFinSemanaParaSobreCosto(reserva);
        if (esDiaHabil){
            reserva.setPrecioFinalReserva(reserva.getPrecioFinalReserva()-(reserva.getPrecioFinalReserva()*DESCUENTO_POR_DIA_HABIL));
        }
    }

    public boolean validarDiaFinSemanaParaSobreCosto(Reserva reserva){
        LocalDate fechaActual = LocalDate.now();
        boolean esFinSemana = fechaActual.getDayOfWeek() == DayOfWeek.SATURDAY || fechaActual.getDayOfWeek() == DayOfWeek.SUNDAY;
        if(esFinSemana){
            reserva.setPrecioFinalReserva(reserva.getPrecioFinalReserva() + reserva.getPrecioFinalReserva() * SOBRE_COSTO_FIN_SEMANA);
            return true;
        }
        return false;
    }

    public void establecerFechaDeCreacionReserva(Reserva reserva){
        reserva.setFechaCreacionReserva(LocalDateTime.now());
    }
    public void establecerFechaDeExpiracionReserva(Reserva reserva){
        GestionHorarios gestionHorarios = new GestionHorarios();
        Date fechaReservacionConvertida = java.util.Date
                .from(reserva.getFechaReservacion().atZone(ZoneId.systemDefault())
                        .toInstant());
        reserva.setFechaExpiracion(gestionHorarios.calcularSiguienteDiaHabil(fechaReservacionConvertida,NUMERO_MAXIMO_DIAS_RESERVACION));
    }

    public int verificarCantidadReservasParaFecha(Reserva reserva){
        return this.repositorioReserva.numeroReservasParaUnaFecha(reserva.getFechaReservacion().toLocalDate());
    }

    public void verficarDescuentoPorVariasReservas(Reserva reserva){
        if(this.repositorioReserva.existeReservaConIdPersona(reserva.getIdPersonaReserva()))
        {

            LocalDateTime ultimaFechaReservacion = this.repositorioReserva.encontrarUltimaFechaReservaPorIdPersona(reserva.getIdPersonaReserva());
            LocalDateTime fechaActual = LocalDateTime.now();
            if(fechaActual.isBefore(ultimaFechaReservacion)){
                reserva.setPrecioFinalReserva(reserva.getPrecioFinalReserva()-(reserva.getPrecioFinalReserva()*DESCUENTO_POR_VARIAS_RESERVAS));
            }


        }
    }



}