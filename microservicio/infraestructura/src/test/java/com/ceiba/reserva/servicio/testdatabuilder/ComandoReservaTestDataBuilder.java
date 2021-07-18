package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

import java.time.LocalDateTime;

public class ComandoReservaTestDataBuilder {
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

    public ComandoReservaTestDataBuilder() {
        this.idCombo = 1L;
        this.precioFinalReserva = 100000.0;
        this.fechaCreacionReserva = LocalDateTime.now();
        this.fechaReservacion = LocalDateTime.now();
        this.fechaExpiracion =LocalDateTime.now() ;
        this.nombrePersonaReserva = "pepito";
        this.idPersonaReserva = "12345678";
        this.telefonoPersonReserva = "3240654";
        this.direccionPersonaReserva = "cra. 20";
    }

    public ComandoReserva build(){
        return new ComandoReserva(id,idCombo,precioFinalReserva,fechaCreacionReserva,fechaReservacion,
                fechaReservacion,nombrePersonaReserva,idPersonaReserva,telefonoPersonReserva,direccionPersonaReserva);
    }
}
