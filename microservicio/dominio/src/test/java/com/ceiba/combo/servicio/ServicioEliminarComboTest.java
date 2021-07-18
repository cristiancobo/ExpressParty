package com.ceiba.combo.servicio;

import com.ceiba.combo.modelo.entidad.Combo;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.combo.servicio.testdatabuilder.ComboTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

public class ServicioEliminarComboTest {

    @Test
    public void validarEliminarComboCorrectamente(){
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        Mockito.doNothing().when(repositorioCombo).eliminar(Mockito.anyLong());
        ServicioEliminarCombo servicioEliminarCombo = new ServicioEliminarCombo(repositorioCombo);
        servicioEliminarCombo.ejecutar(1L);
        Mockito.verify(repositorioCombo,Mockito.times(1)).eliminar(Mockito.anyLong());
    }
}
