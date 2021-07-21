package com.ceiba.combo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.combo.adaptador.dao.DaoComboMysql;
import com.ceiba.combo.comando.ComandoCombo;
import com.ceiba.combo.modelo.dto.DtoCombo;
import com.ceiba.combo.modelo.entidad.Combo;
import com.ceiba.combo.puerto.dao.DaoCombo;
import com.ceiba.combo.servicio.testdatabuilder.ComandoComboTestDataBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorCombo.class)

public class ComandoControladorComboTest {
    @Autowired
    DaoCombo daoCombo;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;


    @Test
    public void crear(){

    }
    @Test
    public void actualizar() {


    }

    @Test
    public void eliminar()  {

    }
}
