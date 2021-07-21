package com.ceiba.reserva.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;

public class ModeloReservaTest {
    @Test
    public void validarPropiedadIdComboObligatorioSinValor(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conIdCombo(null);
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionValorObligatorio.class, "El tipo de combo es obligatorio");
    }
    @Test
    public void validarPropiedadNombrePersonaObligatorioSinValor(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombrePersonaReserva(null);
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionValorObligatorio.class, "El nombre de la persona quien reserva es obligatorio");
    }
    @Test
    public void validarPropiedadIdPersonaObligatorioSinValor(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombrePersonaReserva(null);
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionValorObligatorio.class, "El nombre de la persona quien reserva es obligatorio");
    }
    @Test
    public void validarPropiedadtelefonoPersonaObligatorioSinValor(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conTelefonoPersona(null);
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionValorObligatorio.class, "El telefono es obligatorio");
    }
    @Test
    public void validarPropiedadDireccionPersonaObligatorioSinValor(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conDireccionPersona(null);
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionValorObligatorio.class, "La dirección es obligatoria");
    }

    @Test
    public void validarPropiedadFechaReservacionObligatorioSinValor(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaReserva(null);
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionValorObligatorio.class, "La fecha de reservación es obligatoria");
    }

}
