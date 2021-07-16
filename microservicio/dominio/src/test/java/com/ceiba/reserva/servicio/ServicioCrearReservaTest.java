package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.dominio.excepcion.ExcepcionComboNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.Assert;

import java.time.LocalDateTime;


public class ServicioCrearReservaTest {

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
    public void validarTopeDeReservasParaUnMismoDiaReservasNoDisponibles(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.numeroReservasParaUnaFecha(Mockito.any())).thenReturn(4);
        int numeroReservas = new ServicioCrearReserva(repositorioReserva,repositorioCombo).verificarCantidadReservasParaFecha(reserva);
        Assert.assertEquals(4,numeroReservas);
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
        reserva.setFechaExpiracion(LocalDateTime.of(2021,04,03,06,45,12));
        Assert.assertFalse(reserva.getFechaExpiracion().equals(fechaExpiracion));
    }
    @Test
    public void validarFechaCreacionReservaCorrecta(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        LocalDateTime fechaCreacion = new ServicioCrearReserva(repositorioReserva,repositorioCombo).establecerFechaDeCreacionReserva(reserva);
        Assert.assertTrue(fechaCreacion.equals(LocalDateTime.now()));
    }

    @Test
    public void validarFechaCreacionReservaIncorrecta(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        LocalDateTime fechaCreacion = new ServicioCrearReserva(repositorioReserva,repositorioCombo).establecerFechaDeCreacionReserva(reserva);
        reserva.setFechaCreacionReserva(LocalDateTime.of(2021,04,2,12,45,02));
        Assert.assertFalse(fechaCreacion.equals(reserva.getFechaCreacionReserva()));
    }
    //TODO Realizar prueba
    @Test
    public void validarSobreCostoDiaFinSemana(){

    }
    //TODO Realizar prueba
    @Test
    public void validarDescuentoPorDiaHabil(){

    }
    //TODO Realizar prueba
    @Test
    public void  validarDescuentoPorDiaFestivo(){

    }

    @Test
    public void  validarPrecioBaseComboExistenteParaReserva(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo);
        servicioCrearReserva.generarPrecioBaseReservaCombo(reserva);
        Assert.assertEquals(100000.0,reserva.getPrecioFinalReserva(),0);
    }
    @Test
    public void  validarPrecioBaseComboNoExistenteParaReserva(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(false);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo);
        BasePrueba.assertThrows(() -> servicioCrearReserva.generarPrecioBaseReservaCombo(reserva), ExcepcionComboNoExiste.class,"El combo seleccionado no existe");

    }








    }









