package com.ceiba.combo.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.combo.servicio.testdatabuilder.ComboTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.Test;

public class ModeloComboTest {

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
}
