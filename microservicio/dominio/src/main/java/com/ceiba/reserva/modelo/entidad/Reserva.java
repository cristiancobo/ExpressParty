package com.ceiba.reserva.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Reserva {

    private static final String NOMBRE_PERSONA_RESERVA_OBLIGATORIO = "El nombre de la persona quien reserva es obligatorio";
    private static final String ID_PERSONA_RESERVA_OBLIGATORIO = "El número de la persona quien reserva es obligatorio";
    private static final String ID_COMBO_OBLIGATORIO = "El tipo de combo es obligatorio";
    private static final String TELEFONO_PERSONA_OBLIGATORIO = "El telefono es obligatorio";
    private static final String DIRECCION_PERSONA_OBLIGATORIO = "La dirección es obligatoria";

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

    public void establecerPrecioBaseComboReserva(double precioBase){
        setPrecioFinalReserva(precioBase);
    }
}