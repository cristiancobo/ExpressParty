package com.ceiba.reserva.modelo.entidad;

import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Reserva {

    private static final String NOMBRE_PERSONA_RESERVA_OBLIGATORIO = "El nombre de la persona quien reserva es obligatorio";
    private static final String ID_PERSONA_RESERVA_OBLIGATORIO = "El número de la persona quien reserva es obligatorio";
    private static final String ID_COMBO_OBLIGATORIO = "El tipo de combo es obligatorio";
    private static final String TELEFONO_PERSONA_OBLIGATORIO = "El telefono es obligatorio";
    private static final String DIRECCION_PERSONA_OBLIGATORIO = "La dirección es obligatoria";
    private static final String FECHA_DE_RESERVA_NO_PUEDE_SER_MENOR_A_LA_ACTUAL = "La fecha de reservación no puede ser menor a la actual";
    private static final String EL_PRECIO_RESERVA_NO_PUEDE_SER_NEGATIVO = "El precio de la reserva es incorrecto. No puede ser negativo";
    private static final int ID_PERSONA_MAXIMA_LONGITUD = 12;
    private static final int ID_PERSONA_MINIMA_LONGITUD = 8;
    private static final int NOMBRE_PERSONA_MAXIMA_LONGITUD = 15;
    private static final int NOMBRE_PERSONA_MINIMA_LONGITUD = 2;
    private static final int TELEFONO_PERSONA_MAXIMA_LONGITUD = 12;
    private static final int TELEFONO_PERSONA_MINIMA_LONGITUD = 7;
    private static final int DIRECCION_PERSONA_MAXIMA_LONGITUD = 20;
    private static final int DIRECCION_PERSONA_MINIMA_LONGITUD = 5;
    private static final String FECHA_INCORRECTA_DEBE_SER_FUTURA = "La fecha para la reservación no puede ser menor a la actual";
    private static final String ID_PERSONA_NO_CUMPLE_CON_LONGITUD = "El id de la persona no cumple con la longitud";
    private static final String NOMBRE_PERSONA_NO_CUMPLE_CON_LONGITUD = "El nombre de la persona no cumple con la longitud";
    private static final String TELEFONO_PERSONA_NO_CUMPLE_CON_LONGITUD = "El telefono de la persona no cumple con la longitud";
    private static final String DIRECCION_PERSONA_NO_CUMPLE_CON_LONGITUD = "La direccion de la persona no cumple con la longitud";


    private static final String FECHA_RESERVA = "La fecha de reservación es obligatoria";

    private Long id;
    private Long idCombo;
    private double precioFinalReserva;
    private LocalDateTime fechaCreacionReserva;
    private LocalDateTime fechaReservacion;
    private LocalDateTime fechaExpiracion;
    private String nombrePersonaReserva;
    private String idPersonaReserva;
    private String telefonoPersonReserva;
    private String direccionPersonaReserva;

    public Reserva(Long id, Long idCombo, double precioFinalReserva, LocalDateTime fechaCreacionReserva, LocalDateTime fechaReservacion, LocalDateTime fechaExpiracion, String nombrePersonaReserva, String idPersonaReserva, String telefonoPersonReserva, String direccionPersonaReserva) {
        validarObligatorio(idCombo, ID_COMBO_OBLIGATORIO);
        validarObligatorio(nombrePersonaReserva,NOMBRE_PERSONA_RESERVA_OBLIGATORIO);
        validarObligatorio(idPersonaReserva, ID_PERSONA_RESERVA_OBLIGATORIO);
        validarObligatorio(telefonoPersonReserva,TELEFONO_PERSONA_OBLIGATORIO);
        validarObligatorio(fechaReservacion,FECHA_RESERVA);
        validarObligatorio(direccionPersonaReserva,DIRECCION_PERSONA_OBLIGATORIO);
        validarLongitudMaxima(idPersonaReserva,ID_PERSONA_MAXIMA_LONGITUD,ID_PERSONA_NO_CUMPLE_CON_LONGITUD);
        validarLongitudMinima(idPersonaReserva,ID_PERSONA_MINIMA_LONGITUD,ID_PERSONA_NO_CUMPLE_CON_LONGITUD);
        validarLongitudMaxima(nombrePersonaReserva,NOMBRE_PERSONA_MAXIMA_LONGITUD,NOMBRE_PERSONA_NO_CUMPLE_CON_LONGITUD);
        validarLongitudMinima(nombrePersonaReserva,NOMBRE_PERSONA_MINIMA_LONGITUD,NOMBRE_PERSONA_NO_CUMPLE_CON_LONGITUD);
        validarLongitudMaxima(telefonoPersonReserva,TELEFONO_PERSONA_MAXIMA_LONGITUD,TELEFONO_PERSONA_NO_CUMPLE_CON_LONGITUD);
        validarLongitudMinima(telefonoPersonReserva,TELEFONO_PERSONA_MINIMA_LONGITUD,TELEFONO_PERSONA_NO_CUMPLE_CON_LONGITUD);
        validarLongitudMaxima(direccionPersonaReserva,DIRECCION_PERSONA_MAXIMA_LONGITUD,DIRECCION_PERSONA_NO_CUMPLE_CON_LONGITUD);
        validarLongitudMinima(direccionPersonaReserva,DIRECCION_PERSONA_MINIMA_LONGITUD,DIRECCION_PERSONA_NO_CUMPLE_CON_LONGITUD);
        validarMenor(LocalDateTime.now(), fechaReservacion, FECHA_INCORRECTA_DEBE_SER_FUTURA);

        this.id = id;
        this.idCombo = idCombo;
        this.precioFinalReserva = precioFinalReserva;
        this.fechaCreacionReserva = fechaCreacionReserva;
        this.fechaReservacion = fechaReservacion;
        this.fechaExpiracion = fechaExpiracion;
        this.nombrePersonaReserva = nombrePersonaReserva;
        this.idPersonaReserva = idPersonaReserva;
        this.telefonoPersonReserva = telefonoPersonReserva;
        this.direccionPersonaReserva = direccionPersonaReserva;
    }

    public void establecerPrecioComboReserva(double precioBase){
        this.precioFinalReserva = precioBase;
    }
    public void establecerFechaCreacionReserva(LocalDateTime fechaCreacionReserva){
        this.fechaCreacionReserva = fechaCreacionReserva;
    }
    public void establecerFechaExpiracionReserva(LocalDateTime fechaExpiracion){
        this.fechaExpiracion = fechaExpiracion;
    }
}