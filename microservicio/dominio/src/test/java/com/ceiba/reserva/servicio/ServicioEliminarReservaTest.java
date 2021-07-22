package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;

import com.ceiba.dominio.excepcion.ExcepcionNoExisteReserva;
import com.ceiba.dominio.excepcion.ExcepcionTiempoExcedido;
import com.ceiba.reserva.puerto.dao.DaoReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
 import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.time.LocalDateTime;


@RunWith(PowerMockRunner.class)
@PrepareOnlyThisForTest(ServicioEliminarReserva.class)
@PowerMockRunnerDelegate()
public class ServicioEliminarReservaTest {

    @Test
    public void validarEliminarReservaCuandoExistePeroExcedeTiempo(){
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        LocalDateTime fecha = LocalDateTime.of(2021,07,21,12,10,12);
        PowerMockito.when(daoReserva.encontrarFechaCreacionReserva(Mockito.anyLong())).thenReturn(fecha);
        PowerMockito.spy(LocalDateTime.class);
        PowerMockito.when(LocalDateTime.now()).thenReturn(LocalDateTime.of(2021,07,21,12,50,12));
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

    @Test
    public void ejecutar() {
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        DaoReserva daoReserva = Mockito.mock(DaoReserva.class);
        Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(daoReserva.encontrarFechaCreacionReserva(Mockito.anyLong())).thenReturn(LocalDateTime.now());
        Mockito.doNothing().when(repositorioReserva).eliminar(Mockito.anyLong());
        ServicioEliminarReserva servicioEliminarReserva = new ServicioEliminarReserva(repositorioReserva, daoReserva);
        servicioEliminarReserva.ejecutar(1L);
        Mockito.verify(repositorioReserva,Mockito.times(1)).eliminar(Mockito.anyLong());
    }
}
