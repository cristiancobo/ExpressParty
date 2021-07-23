package com.ceiba.reserva.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.combo.comando.ComandoCombo;
import com.ceiba.combo.controlador.ComandoControladorCombo;
import com.ceiba.combo.servicio.testdatabuilder.ComandoComboTestDataBuilder;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ComandoReservaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(ComandoControladorReserva.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING	)
public class ComandoControladorReservaTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void aValidarCrear() throws Exception{
        ComandoReserva comandoReserva = new ComandoReservaTestDataBuilder().build();
        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReserva)))
                .andExpect(status().isCreated())
                .andExpect(status().isCreated())
                .andDo(resultado ->{
                        MvcResult mvcResult = mockMvc.perform((get("/reservas")
                        .param("id",String.valueOf(comandoReserva.getId())))).andReturn();
                        String contentAsString = mvcResult.getResponse().getContentAsString();
                        contentAsString = contentAsString.replace("[", "");
                        contentAsString = contentAsString.replace("]", "");
                        JSONObject crearReservaResponse = new JSONObject(contentAsString);
                        Assert.assertEquals("carla", crearReservaResponse.getString("nombrePersonaReserva"));
                        Assert.assertEquals("12345678", crearReservaResponse.getString("idPersonaReserva"));

                        }
                );


    }

    @Test
    public void bValidarActualizar() throws Exception{
        Long id = 1L;
        ComandoReserva comandoReserva = new ComandoReservaTestDataBuilder().conNombrePersona("lupito").conIdPersona("76567892").build();
        mockMvc.perform(put("/reservas/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoReserva)))
                .andExpect(status().isOk())
                .andDo(resultado ->{
                MvcResult mvcResult = mockMvc.perform((get("/reservas")

                    .param("id",String.valueOf(id)))).andReturn();
            String contentAsString = mvcResult.getResponse().getContentAsString();
            contentAsString = contentAsString.replace("[", "");
            contentAsString = contentAsString.replace("]", "");
            JSONObject crearReservaResponse = new JSONObject(contentAsString);
            Assert.assertEquals("lupito", crearReservaResponse.getString("nombrePersonaReserva"));
            Assert.assertEquals("76567892", crearReservaResponse.getString("idPersonaReserva"));
        }
                );
    }

    @Test
    public void cValidarEliminar() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/reservas/{id}",id))
                .andExpect(status().isOk());


    }
}
