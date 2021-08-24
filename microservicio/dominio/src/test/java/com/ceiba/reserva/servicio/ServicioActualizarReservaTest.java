package com.ceiba.reserva.servicio;
import com.ceiba.BasePrueba;
import com.ceiba.combo.puerto.dao.DaoCombo;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.dominio.excepcion.ExcepcionNoExisteReserva;
import com.ceiba.dominio.excepcion.ExcepcionTiempoExcedido;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDateTime;
@RunWith(PowerMockRunner.class)
@PrepareOnlyThisForTest(ServicioActualizarReserva.class)
public class ServicioActualizarReservaTest {

    @Test
    public void validarEstaVigenteParaActualizarReservaExedeTiempo() {
        Reserva reserva = new ReservaTestDataBuilder().build();
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        LocalDateTime fecha = LocalDateTime.of(2021,07,21,12,10,12);
        PowerMockito.when(daoReserva.encontrarFechaCreacionReserva(Mockito.anyLong())).thenReturn(fecha);
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,21,12,56,12));
        DaoCombo daoCombo = Mockito.mock(DaoCombo.class);
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva,daoReserva, daoCombo, repositorioCombo);
        BasePrueba.assertThrows(()-> servicioActualizarReserva.ejecutar(reserva), ExcepcionTiempoExcedido.class, "El tiempo para realizar la actualizacion excedió");
    }

    @Test
    public void validarExistenciaReservaNoexiste(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        DaoCombo daoCombo = Mockito.mock(DaoCombo.class);
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva, daoReserva, daoCombo, repositorioCombo);
        BasePrueba.assertThrows(()-> servicioActualizarReserva.ejecutar(reserva),ExcepcionNoExisteReserva.class, "La reserva que intenta actualizar no existe");
    }

    @Test
    public void validarActualizarReservaCorrectamente(){
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        DaoCombo daoCombo = Mockito.mock(DaoCombo.class);
        DtoReserva dtoReserva = new DtoReserva(reserva.getId(), reserva.getIdCombo(), reserva.getPrecioFinalReserva(),reserva.getFechaCreacionReserva(),reserva.getFechaReservacion(),reserva.getFechaExpiracion(),reserva.getNombrePersonaReserva(),reserva.getIdPersonaReserva(),reserva.getTelefonoPersonReserva(), reserva.getDireccionPersonaReserva());
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoReserva.encontrarFechaCreacionReserva(Mockito.anyLong())).thenReturn(reserva.getFechaCreacionReserva());
        Mockito.when(repositorioCombo.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoReserva.obtenerReservaPorId(Mockito.anyLong())).thenReturn(dtoReserva);
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,21,12,21,12));
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva, daoReserva, daoCombo, repositorioCombo);
        servicioActualizarReserva.ejecutar(reserva);
        Mockito.verify(repositorioReserva,Mockito.times(1)).actualizar(Mockito.any());
    }

}
