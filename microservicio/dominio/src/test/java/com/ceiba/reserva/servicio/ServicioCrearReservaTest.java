package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.dominio.excepcion.ExcepcionComboNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionTopeNumeroReservasFecha;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Assert;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;
import java.time.LocalDateTime;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ServicioCrearReserva.class)
@PowerMockIgnore("jdk.internal.reflect.*")
public class ServicioCrearReservaTest {

    //TODO: Mover pruebas a su respectivo paquete

    @Mock
    LocalDateTime localDateTime;

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
    public void validarCrearReservaConTodasLasPropiedadesConValor(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.crear(Mockito.any())).thenReturn(1L);
        ServicioCrearReserva  servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo);
        Assert.assertEquals((long)servicioCrearReserva.ejecutar(reserva), 1L);
    }
    @Test
    public void validarDescuentoExistosoPorVariasReservasVigentes(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.existeReservaConIdPersona(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioReserva.encontrarUltimaFechaReservaPorIdPersona(Mockito.anyString())).thenReturn(reserva.getFechaReservacion());
        double precioFinalReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo).verficarDescuentoPorVariasReservas(reserva);
        Assert.assertEquals(85000, precioFinalReserva, 0);
    }
    @Test
    public void validarDescuentoNoExistosoPorVariasReservasVigentes(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.existeReservaConIdPersona(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioReserva.encontrarUltimaFechaReservaPorIdPersona(Mockito.anyString())).thenReturn(reserva.getFechaReservacion());
        double precioFinalReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo).verficarDescuentoPorVariasReservas(reserva);
        Assert.assertEquals(0, precioFinalReserva, 0);
    }
    @Test
    public void validarTopeDeReservasParaUnMismoDiaReservasNoDisponiblesMayorIgual4(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.numeroReservasParaUnaFecha(Mockito.any())).thenReturn(5);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo);
        BasePrueba.assertThrows(()-> servicioCrearReserva.verificarCantidadReservasParaFecha(reserva), ExcepcionTopeNumeroReservasFecha.class, "No es posible realizar una reserva para esta fecha. Elige una nueva");
    }


    @Test
    public void validarTopeDeReservasParaUnMismoDiaReservasSiDisponibles(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.numeroReservasParaUnaFecha(Mockito.any())).thenReturn(3);
        int numeroReservas = new ServicioCrearReserva(repositorioReserva,repositorioCombo).verificarCantidadReservasParaFecha(reserva);
        Assert.assertEquals(3,numeroReservas);
    }

    @Test public void validarFechaExpiracionReservaCorrecta(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        LocalDateTime fechaExpiracion = new ServicioCrearReserva(repositorioReserva,repositorioCombo).establecerFechaDeExpiracionReserva(reserva);
        Assert.assertTrue(reserva.getFechaExpiracion().equals(fechaExpiracion));
    }
    @Test
    public void validarFechaExpiracionReservaIncorrecta(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        LocalDateTime fechaExpiracion = new ServicioCrearReserva(repositorioReserva,repositorioCombo).establecerFechaDeExpiracionReserva(reserva);
        reserva.establecerFechaExpiracionReserva(LocalDateTime.of(2021,04,03,06,45,12));
        Assert.assertFalse(reserva.getFechaExpiracion().equals(fechaExpiracion));
    }
    //TODO: Realizar prueba
    @Test
    public void validarFechaCreacionReservaCorrecta(){

    }

    @Test
    public void validarFechaCreacionReservaIncorrecta(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        LocalDateTime fechaCreacion = new ServicioCrearReserva(repositorioReserva,repositorioCombo).establecerFechaDeCreacionReserva(reserva);
        reserva.establecerFechaCreacionReserva(LocalDateTime.of(2021,04,2,12,45,02));
        Assert.assertFalse(fechaCreacion.equals(reserva.getFechaCreacionReserva()));
    }

    //TODO Realizar prueba
    @Test
    public void validarDescuentoPorDiaHabil(){

    }

    @Test
    public void  validarPrecioBaseComboExistenteParaReserva(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,18,12,21,12));
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertEquals(100000.0,reserva.getPrecioFinalReserva(),0);
    }
    @Test
    public void  validarPrecioBaseComboNoExistenteParaReserva(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(false);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo);
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionComboNoExiste.class,"El combo seleccionado no existe");

    }








    }









