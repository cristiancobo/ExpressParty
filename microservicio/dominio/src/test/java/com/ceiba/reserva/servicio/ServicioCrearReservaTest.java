package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.combo.puerto.dao.DaoCombo;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.dominio.excepcion.ExcepcionComboNoExiste;
import com.ceiba.dominio.excepcion.ExcepcionTopeNumeroReservasFecha;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.junit.Assert;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;
import java.time.LocalDateTime;
import java.util.Calendar;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Reserva.class,ServicioCrearReserva.class})
public class ServicioCrearReservaTest {

    @Test
    public void  validarPrecioBaseComboExistenteParaReserva(){

        Reserva reserva = new ReservaTestDataBuilder().build();
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,21,12,21,12));
        PowerMockito.spy(Calendar.class);
        Calendar calentarioActual = Calendar.getInstance();
        calentarioActual.set(2021, Calendar.JULY, 21 ,12,21,12);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calentarioActual);
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        DaoCombo daoCombo = Mockito.mock(DaoCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva, daoCombo);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertTrue(reserva.getPrecioFinalReserva() == 95000);
    }
    @Test
    public void  validarPrecioBaseComboNoExistenteParaReserva(){
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,19,12,21,12));
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        DaoCombo daoCombo = Mockito.mock(DaoCombo.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(false);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva, daoCombo);
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionComboNoExiste.class,"El combo seleccionado no existe");
    }
    @Test
    public void ValidarCantidadReservasParaFechaMenor4(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,19,12,21,12));
        PowerMockito.spy(Calendar.class);
        Calendar calentarioActual = Calendar.getInstance();
        calentarioActual.set(2021, Calendar.JULY, 19 ,12,21,12);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calentarioActual);
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        DaoCombo daoCombo = Mockito.mock(DaoCombo.class);
        LocalDateTime fecha = LocalDateTime.of(2021,07,21,20,20,20);
        Mockito.when(daoReserva.encontrarUltimaFechaReservaPorIdPersona(Mockito.anyString())).thenReturn(fecha);
        Mockito.when(repositorioReserva.existeReservaConIdPersona(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        Mockito.when(daoReserva.numeroReservasParaUnaFecha(Mockito.any())).thenReturn(3);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva, daoCombo);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertEquals("pepito",reserva.getNombrePersonaReserva() );
        Assert.assertEquals("12345678",reserva.getIdPersonaReserva() );
        Assert.assertTrue(reserva.getPrecioFinalReserva() == 80750);
    }
    @Test
    public void ValidarCantidadReservasParaFechaMayor4(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,19,12,21,12));
        PowerMockito.spy(Calendar.class);
        Calendar calentarioActual = Calendar.getInstance();
        calentarioActual.set(2021, Calendar.JULY, 19 ,12,21,12);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calentarioActual);
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        DaoCombo daoCombo = Mockito.mock(DaoCombo.class);
        LocalDateTime fecha = LocalDateTime.of(2021,07,21,20,20,20);
        Mockito.when(daoReserva.encontrarUltimaFechaReservaPorIdPersona(Mockito.anyString())).thenReturn(fecha);
        Mockito.when(repositorioReserva.existeReservaConIdPersona(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        Mockito.when(daoReserva.numeroReservasParaUnaFecha(Mockito.any())).thenReturn(7);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva, daoCombo);
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionTopeNumeroReservasFecha.class,"No es posible realizar una reserva para esta fecha. Elige una nueva");

    }
    @Test
    public void validarDescuentoPorVariasReservasEnDiaHabilExistoso(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,19,12,21,12));
        PowerMockito.spy(Calendar.class);
        Calendar calentarioActual = Calendar.getInstance();
        calentarioActual.set(2021, Calendar.JULY, 19 ,12,21,12);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calentarioActual);
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        DaoCombo daoCombo = Mockito.mock(DaoCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        LocalDateTime fecha = LocalDateTime.of(2021,07,21,20,20,20);
        Mockito.when(daoReserva.encontrarUltimaFechaReservaPorIdPersona(Mockito.anyString())).thenReturn(fecha);
        Mockito.when(repositorioReserva.existeReservaConIdPersona(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva, daoCombo);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertTrue(reserva.getPrecioFinalReserva() == 80750);
    }
    @Test
    public void validarDiaHabilParaDescuento(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,19,12,21,12));
        PowerMockito.spy(Calendar.class);
        Calendar calentarioActual = Calendar.getInstance();
        calentarioActual.set(2021, Calendar.JULY, 19 ,12,21,12);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calentarioActual);
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        DaoCombo daoCombo = Mockito.mock(DaoCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva, daoCombo);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertTrue(reserva.getPrecioFinalReserva() == 95000);
    }
    @Test
    public void validarDiaFestivoParaSobreCosto(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,20,12,21,12));
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoCombo daoCombo = Mockito.mock(DaoCombo.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);

        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva, daoCombo);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertTrue(reserva.getPrecioFinalReserva() == 107000);


    }

    @Test
    public void validarDiaFinSemanaParaSobreCosto(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,20,12,21,12));
        PowerMockito.spy(Calendar.class);
        Calendar calentarioActual = Calendar.getInstance();
        calentarioActual.set(2021, Calendar.JULY, 20 ,12,21,12);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calentarioActual);
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        DaoCombo daoCombo = Mockito.mock(DaoCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva, daoCombo);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertTrue(reserva.getPrecioFinalReserva() == 107000);
    }



}









