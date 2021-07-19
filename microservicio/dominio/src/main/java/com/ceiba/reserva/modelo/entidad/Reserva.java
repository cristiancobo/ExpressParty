package com.ceiba.reserva.modelo.entidad;

import lombok.Getter;
import lombok.Setter;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import java.time.LocalDateTime;

@Getter
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
        validarObligatorio(idCombo, ID_COMBO_OBLIGATORIO);
        validarObligatorio(nombrePersonaReserva,NOMBRE_PERSONA_RESERVA_OBLIGATORIO);
        validarObligatorio(idPersonaReserva, ID_PERSONA_RESERVA_OBLIGATORIO);
        validarObligatorio(telefonoPersonReserva,TELEFONO_PERSONA_OBLIGATORIO);
        validarObligatorio(fechaReservacion,FECHA_RESERVA);
        validarObligatorio(direccionPersonaReserva,DIRECCION_PERSONA_OBLIGATORIO);

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