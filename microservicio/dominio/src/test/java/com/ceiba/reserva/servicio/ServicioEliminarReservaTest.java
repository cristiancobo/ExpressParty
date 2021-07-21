package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;

import com.ceiba.dominio.excepcion.ExcepcionNoExisteReserva;
import com.ceiba.dominio.excepcion.ExcepcionTiempoExcedido;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;
import java.time.LocalDateTime;
import java.util.Calendar;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ServicioEliminarReserva.class)
@PowerMockIgnore("jdk.internal.reflect.*")
public class ServicioEliminarReservaTest {

    @Test
    public void validarEliminarReservaCuandoExiste(){
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        LocalDateTime fecha = LocalDateTime.of(2021,07,21,12,10,12);
        PowerMockito.when(daoReserva.encontrarFechaCreacionReserva(Mockito.anyLong())).thenReturn(fecha);
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,21,12,50,12));
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(true);
        ServicioEliminarReserva servicioEliminarReserva = new ServicioEliminarReserva(repositorioReserva,daoReserva);
        BasePrueba.assertThrows(()-> servicioEliminarReserva.ejecutar(1L), ExcepcionTiempoExcedido.class, "El tiempo para realizar la cancelación excedió");

    }

    @Test
    public void verificarExistenciaReserva(){
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        ServicioEliminarReserva servicioEliminarReserva = new ServicioEliminarReserva(repositorioReserva, daoReserva);
        Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(false);
        BasePrueba.assertThrows(()-> servicioEliminarReserva.ejecutar(1L), ExcepcionNoExisteReserva.class, "La reserva que intenta eliminar no existe");
    }
}
