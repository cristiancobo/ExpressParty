package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionNoExisteReserva;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class ServicioActualizarReservaTest {
    @Test
    public void validarActualizarReservaCorrectamente(){
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Reserva reserva = new ReservaTestDataBuilder().build();
        Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioReserva.encontrarFechaCreacionReserva(Mockito.anyLong())).thenReturn(LocalDateTime.now());
        Mockito.doNothing().when(repositorioReserva).actualizar(reserva);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        servicioActualizarReserva.ejecutar(reserva);
        Mockito.verify(repositorioReserva,Mockito.times(1)).actualizar(Mockito.any());
    }
    @Test
    public void verificarExistenciaReserva(){

        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        Mockito.when(repositorioReserva.existe(Mockito.anyLong())).thenReturn(false);
        BasePrueba.assertThrows(()-> servicioActualizarReserva.verficiarExistenciaReserva(1L), ExcepcionNoExisteReserva.class, "La reserva que intenta eliminar no existe");
    }
}
