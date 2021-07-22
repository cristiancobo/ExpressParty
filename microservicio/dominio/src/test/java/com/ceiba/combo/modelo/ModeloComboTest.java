package com.ceiba.combo.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.combo.servicio.testdatabuilder.ComboTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
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
    @Test
    public void validarPropiedadNombreMaximaLongitudNoCorrecta(){
        ComboTestDataBuilder comboTestDataBuilder = new ComboTestDataBuilder().conNombre("Combo super especial con para grandes clientes comerciales en cali");
        BasePrueba.assertThrows(()-> comboTestDataBuilder.build(), ExcepcionLongitudValor.class,"El nombre no cumple la longitud máxima");

    }
    @Test
    public void validarPropiedadNombreMinimaLongitudNoCorrecta(){
        ComboTestDataBuilder comboTestDataBuilder = new ComboTestDataBuilder().conNombre("");
        BasePrueba.assertThrows(()-> comboTestDataBuilder.build(), ExcepcionLongitudValor.class,"El nombre no cumple la longitud mínima");

    }
    @Test
    public void validarPropiedadPrecioNegativo(){
        ComboTestDataBuilder comboTestDataBuilder = new ComboTestDataBuilder().conPrecio(-500000.0);
        BasePrueba.assertThrows(()-> comboTestDataBuilder.build(), ExcepcionValorInvalido.class,"El precio de un combo no puede ser negativo");

    }
}
