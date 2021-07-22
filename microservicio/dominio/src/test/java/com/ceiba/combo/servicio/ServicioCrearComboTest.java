package com.ceiba.combo.servicio;

import com.ceiba.combo.modelo.entidad.Combo;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.combo.servicio.testdatabuilder.ComboTestDataBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
public class ServicioCrearComboTest {

    @Test
    public void validarTodasLasPropiedadesComboConValor(){
        Combo combo = new ComboTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        Mockito.when(repositorioCombo.crear(Mockito.any())).thenReturn(1L);
        ServicioCrearCombo servicioCrearCombo = new ServicioCrearCombo(repositorioCombo);
        Assert.assertEquals(1L,(long) servicioCrearCombo.ejecutar(combo));
    }
}
