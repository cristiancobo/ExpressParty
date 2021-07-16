package com.ceiba.dominio.excepcion;

public class ExcepcionTiempoExcedido extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionTiempoExcedido(String mensaje) {
        super(mensaje);
    }
}
