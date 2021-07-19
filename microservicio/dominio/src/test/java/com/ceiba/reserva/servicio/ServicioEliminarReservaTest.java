package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionNoExisteReserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import org.junit.Test;

import org.mockito.Mockito;

import java.time.LocalDateTime;


public class ServicioEliminarReservaTest {

    @Test
    public void validarEliminarReservaCuandoExiste(){
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.encontrarFechaCreacionReserva(Mockito.anyLong())).thenReturn(LocalDateTime.now());
        Mockito.doNothing().when(repositorioReserva).eliminar(Mockito.anyLong());
        ServicioEliminarReserva servicioEliminarReserva = new ServicioEliminarReserva(repositorioReserva);
        servicioEliminarReserva.ejecutar(1L);
        Mockito.verify(repositorioReserva,Mockito.times(1)).eliminar(Mockito.anyLong());
    }

    @Test
    public void verificarExistenciaReserva(){

        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(false);
        BasePrueba.assertThrows(()-> servicioActualizarReserva.verficiarExistenciaReserva(1L), ExcepcionNoExisteReserva.class, "La reserva que intenta eliminar no existe");
    }
}
