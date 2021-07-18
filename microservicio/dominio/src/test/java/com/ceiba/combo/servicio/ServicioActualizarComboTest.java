package com.ceiba.combo.servicio;

import com.ceiba.combo.modelo.entidad.Combo;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.combo.servicio.testdatabuilder.ComboTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

public class ServicioActualizarComboTest {

    @Test
    public void validarActualizarComboCorrectamente(){
        Combo combo = new ComboTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        Mockito.doNothing().when(repositorioCombo).actualizar(Mockito.any());
        ServicioActualizarCombo servicioActualizarCombo = new ServicioActualizarCombo(repositorioCombo);
        servicioActualizarCombo.ejecutar(combo);
        Mockito.verify(repositorioCombo,Mockito.times(1)).actualizar(Mockito.any());
    }
}
