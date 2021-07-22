package com.ceiba.reserva.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;

@RunWith(PowerMockRunner.class)
@PrepareOnlyThisForTest(Reserva.class)
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
    @Test
    public void validarPropiedadIdPersonaConMaximaLongitudIncorrecta(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conIdPersonaReserva("12345678910111213");
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionLongitudValor.class, "El id de la persona no cumple con la longitud");
    }
    @Test
    public void validarPropiedadIdPersonaConMinimaLongitudIncorrecta(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conIdPersonaReserva("12");
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionLongitudValor.class, "El id de la persona no cumple con la longitud");
    }
    @Test
    public void validarPropiedadNombrePersonaConMaximaLongitudIncorrecta(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombrePersonaReserva("Antonio araujo de cordoba velez del quindio valle");
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionLongitudValor.class, "El nombre de la persona no cumple con la longitud");
    }
    @Test
    public void validarPropiedadNombrePersonaConMinimaLongitudIncorrecta(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombrePersonaReserva("");
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionLongitudValor.class, "El nombre de la persona no cumple con la longitud");
    }
    @Test
    public void validarPropiedadDireccionPersonaConMaximaLongitudIncorrecta(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conDireccionPersona("Cra. 20 # 56 - 56 a la vuelta cerca a una panaderia cerca al barrio lopez");
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionLongitudValor.class, "La direccion de la persona no cumple con la longitud");
    }
    @Test
    public void validarPropiedadDireccionPersonaConMinimaLongitudIncorrecta(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conDireccionPersona("Cra.");
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionLongitudValor.class, "La direccion de la persona no cumple con la longitud");
    }
    @Test
    public void validarPropiedadTelefonoPersonaConMaximaLongitudIncorrecta(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conTelefonoPersona("2335656788654677655");
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionLongitudValor.class, "El telefono de la persona no cumple con la longitud");
    }
    @Test
    public void validarPropiedadTelefonoPersonaConMinimaLongitudIncorrecta(){
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conTelefonoPersona("23");
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionLongitudValor.class, "El telefono de la persona no cumple con la longitud");
    }
    @Test
    public void validarPropiedadFechaReservacionNoFutura(){
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,21,12,21,12));
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaReserva(LocalDateTime.of(2021,07,19,12,21,12));
        BasePrueba.assertThrows(()-> reservaTestDataBuilder.build(), ExcepcionValorInvalido.class, "La fecha para la reservación no puede ser menor a la actual");
    }
}
