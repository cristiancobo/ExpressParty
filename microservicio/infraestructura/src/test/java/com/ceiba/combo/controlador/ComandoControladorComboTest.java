package com.ceiba.combo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.combo.adaptador.dao.DaoComboMysql;
import com.ceiba.combo.comando.ComandoCombo;
import com.ceiba.combo.modelo.dto.DtoCombo;
import com.ceiba.combo.modelo.entidad.Combo;
import com.ceiba.combo.puerto.dao.DaoCombo;
import com.ceiba.combo.servicio.testdatabuilder.ComandoComboTestDataBuilder;
import com.ceiba.reserva.comando.ComandoReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ComandoReservaTestDataBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorCombo.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING	)
public class ComandoControladorComboTest {
    @Autowired
    DaoCombo daoCombo;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test

    public void aValidarCrear() throws Exception{

       ComandoCombo comandoCombo = new ComandoComboTestDataBuilder().build();
       mockMvc.perform((post("/combos"))
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(comandoCombo)))
               .andExpect(status().isCreated())
               .andDo(
                       resultado ->{
                           MvcResult mvcResult = mockMvc.perform((get("/combos")
                                   .param("id",String.valueOf(comandoCombo.getId())))).andReturn();
                           String contentAsString = mvcResult.getResponse().getContentAsString();
                           contentAsString = contentAsString.replace("[", "");
                           contentAsString = contentAsString.replace("]", "");
                           JSONObject crearReservaResponse = new JSONObject(contentAsString);
                           Assert.assertEquals("combo 1", crearReservaResponse.getString("nombre"));
                           Assert.assertEquals(100000.0, crearReservaResponse.getDouble("precio"),0);

                       }

               );

    }

    @Test
    public void bValidarActualizar() throws Exception{
        Long id = 1L;
        ComandoCombo comandoCombo = new ComandoComboTestDataBuilder().conNombre("combo super").conPrecio(500000.0).build();
        mockMvc.perform(put("/combos/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoCombo)))
                .andExpect(status().isOk())
                .andDo(resultado ->{
                            MvcResult mvcResult = mockMvc.perform((get("/combos")
                                    .param("id",String.valueOf(id)))).andReturn();
                            String contentAsString = mvcResult.getResponse().getContentAsString();
                            contentAsString = contentAsString.replace("[", "");
                            contentAsString = contentAsString.replace("]", "");
                            JSONObject actualizarComboResponse = new JSONObject(contentAsString);
                            Assert.assertEquals("combo super", actualizarComboResponse.getString("nombre"));
                            Assert.assertEquals(500000.0, actualizarComboResponse.getDouble("precio"),0);
                        }
                );
    }

    @Test
    public void cValidarEliminar() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/combos/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
