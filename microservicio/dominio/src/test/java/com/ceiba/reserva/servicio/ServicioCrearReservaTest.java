package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.dominio.excepcion.ExcepcionComboNoExiste;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.junit.Assert;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;
import java.time.LocalDateTime;
import java.util.Calendar;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ServicioCrearReserva.class)
@PowerMockIgnore("jdk.internal.reflect.*")
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
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);

        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertEquals(95000.0,reserva.getPrecioFinalReserva(),0);
    }
    @Test
    public void  validarPrecioBaseComboNoExistenteParaReserva(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);

        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(false);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva);
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
        LocalDateTime fecha = LocalDateTime.of(2021,07,21,20,20,20);
        Mockito.when(daoReserva.encontrarUltimaFechaReservaPorIdPersona(Mockito.anyString())).thenReturn(fecha);
        Mockito.when(repositorioReserva.existeReservaConIdPersona(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        Mockito.when(daoReserva.numeroReservasParaUnaFecha(Mockito.any())).thenReturn(3);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertEquals("pepito",reserva.getNombrePersonaReserva() );
        Assert.assertEquals("12345678",reserva.getIdPersonaReserva() );
        Assert.assertEquals(80750,reserva.getPrecioFinalReserva(),0);
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
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        LocalDateTime fecha = LocalDateTime.of(2021,07,21,20,20,20);
        Mockito.when(daoReserva.encontrarUltimaFechaReservaPorIdPersona(Mockito.anyString())).thenReturn(fecha);
        Mockito.when(repositorioReserva.existeReservaConIdPersona(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertEquals(80750,reserva.getPrecioFinalReserva(),0);
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
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);

        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertEquals(95000,reserva.getPrecioFinalReserva(),0);
    }
    @Test
    public void validarDiaFestivoParaSobreCosto(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,20,12,21,12));
        PowerMockito.spy(Calendar.class);
        Calendar calentarioActual = Calendar.getInstance();
        calentarioActual.set(2021, Calendar.JULY, 20 ,12,21,12);
        PowerMockito.when(Calendar.getInstance()).thenReturn(calentarioActual);
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);

        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva);
        servicioCrearReserva.ejecutar(reserva);
        Assert.assertEquals(107000,reserva.getPrecioFinalReserva(),0);
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
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioCombo.obtenerPrecioCombo(Mockito.anyLong())).thenReturn(100000.0);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva,repositorioCombo, daoReserva);
        servicioCrearReserva.ejecutar(reserva);
    }


}









