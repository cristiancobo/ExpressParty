package com.ceiba.combo.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.combo.modelo.entidad.Combo;
import com.ceiba.combo.puerto.repositorio.RepositorioCombo;
import com.ceiba.combo.servicio.testdatabuilder.ComboTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ServicioCrearComboTest {

    @Test
    public void validarPropiedadNombreObligarioSinValor(){
        ComboTestDataBuilder comboTestDataBuilder = new ComboTestDataBuilder().conNombre(null);
        BasePrueba.assertThrows(()-> comboTestDataBuilder.build(), ExcepcionValorObligatorio.class, "El nombre del combo es obligatorio");
    }
    @Test
    public void validarPropiedadPrecioObligatorioSinValor(){
        ComboTestDataBuilder comboTestDataBuilder = new ComboTestDataBuilder().conPrecio(null);
        BasePrueba.assertThrows(()-> comboTestDataBuilder.build(), ExcepcionValorObligatorio.class,"El precio del combo es obligatorio");
    }

    @Test
    public void validarTodasLasPropiedadesComboConValor(){
        Combo combo = new ComboTestDataBuilder().build();
        RepositorioCombo repositorioCombo = Mockito.mock(RepositorioCombo.class);
        Mockito.when(repositorioCombo.crear(Mockito.any())).thenReturn(1L);
        ServicioCrearCombo servicioCrearCombo = new ServicioCrearCombo(repositorioCombo);
        Assert.assertEquals(1L,(long) servicioCrearCombo.ejecutar(combo));
    }
}
