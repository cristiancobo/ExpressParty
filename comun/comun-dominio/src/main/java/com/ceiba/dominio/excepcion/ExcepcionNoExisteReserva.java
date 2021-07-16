package com.ceiba.dominio.excepcion;

public class ExcepcionNoExisteReserva extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionNoExisteReserva(String mensaje) {
        super(mensaje);
    }
}
