package com.ceiba.dominio.excepcion;

public class ExcepcionTopeNumeroReservasFecha extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionTopeNumeroReservasFecha(String mensaje) {
        super(mensaje);
    }
}
