package com.ceiba.combo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.combo.puerto.dao.DaoCombo;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaControladorCombo.class)
public class ConsultaControladorComboTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void aValidarListar() throws Exception{
        mockMvc.perform(get("/combos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].nombre", is("combo 1")))
                .andExpect(jsonPath("$[0].precio", is(100000.0)))
                .andExpect(jsonPath("$[1].nombre", is("combo 2")))
                .andExpect(jsonPath("$[1].precio", is(200000.0)))
                .andExpect(jsonPath("$[2].nombre", is("combo 3")))
                .andExpect(jsonPath("$[2].precio", is(300000.0)))
                .andExpect(jsonPath("$[*].nombre", containsInAnyOrder("combo 1", "combo 2", "combo 3")))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)));
    }

}

