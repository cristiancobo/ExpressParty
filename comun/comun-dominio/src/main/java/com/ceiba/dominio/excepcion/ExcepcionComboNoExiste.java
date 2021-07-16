package com.ceiba.dominio.excepcion;

public class ExcepcionComboNoExiste extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExcepcionComboNoExiste(String mensaje) {
        super(mensaje);
    }
}
