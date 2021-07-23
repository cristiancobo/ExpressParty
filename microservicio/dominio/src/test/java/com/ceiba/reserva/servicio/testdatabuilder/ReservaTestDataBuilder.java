package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDateTime;

public class ReservaTestDataBuilder {
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

    public ReservaTestDataBuilder() {
        this.id = 1L;
        this.idCombo = 1L;
        this.precioFinalReserva=100000;
        this.fechaCreacionReserva = LocalDateTime.now();
        this.fechaReservacion = LocalDateTime.of(2021,7,30,19,20,10);
        this.fechaExpiracion = LocalDateTime.of(2021,8,1,19,20,10);
        this.nombrePersonaReserva = "pepito";
        this.idPersonaReserva="12345678";
        this.telefonoPersonReserva="320667543";
        this.direccionPersonaReserva="cra 45 # 67a - 56";
    }

    public ReservaTestDataBuilder conIdCombo(Long id){
        this.idCombo = id;
        return this;
    }
    public ReservaTestDataBuilder conNombrePersonaReserva(String nombrePersonaReserva){
        this.nombrePersonaReserva = nombrePersonaReserva;
        return this;
    }
    public ReservaTestDataBuilder conIdPersonaReserva(String idPersonaReserva){
        this.idPersonaReserva = idPersonaReserva;
        return this;
    }

    public ReservaTestDataBuilder conTelefonoPersona(String telefonoPersonReserva){
        this.telefonoPersonReserva = telefonoPersonReserva;
        return this;
    }
    public ReservaTestDataBuilder conDireccionPersona(String direccionPersonaReserva){
        this.direccionPersonaReserva = direccionPersonaReserva;
        return this;
    }
    public ReservaTestDataBuilder conFechaReserva(LocalDateTime fechaReservacion){
        this.fechaReservacion = fechaReservacion;
        return this;
    }
    public ReservaTestDataBuilder conFechaCreacion(LocalDateTime fechaCreacion){
        this.fechaCreacionReserva = fechaCreacion;
        return this;
    }
    public ReservaTestDataBuilder conFechaExpiracion(LocalDateTime fechaExpiracion){
        this.fechaExpiracion = fechaExpiracion;
        return this;
    }

    public Reserva build(){
        return new Reserva(id,idCombo,precioFinalReserva,fechaCreacionReserva,fechaReservacion,fechaExpiracion,nombrePersonaReserva,idPersonaReserva,telefonoPersonReserva,direccionPersonaReserva);
    }
}
