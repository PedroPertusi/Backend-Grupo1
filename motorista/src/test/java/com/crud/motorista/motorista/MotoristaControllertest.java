package com.crud.motorista.motorista;

import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.crud.motorista.motorista.DTO.MotoristaEditDTO;
import com.crud.motorista.motorista.DTO.MotoristaReturnDTO;
import com.crud.motorista.motorista.DTO.MotoristaSaveDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class MotoristaControllertest {

    MockMvc mockMvc;

    @InjectMocks
    MotoristaController motoristaController;

    @Mock
    MotoristaService motoristaService;

    @Mock
    MotoristaRepository motoristaRepository;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(motoristaController)
                .build();
    }

    @Test
    void listAll() throws Exception {
        MotoristaReturnDTO m = new MotoristaReturnDTO("Pedro", "ABC", "ABC", "M1", "", "");
        List<MotoristaReturnDTO> motoristas = new ArrayList<>();
        motoristas.add(m);

        Mockito.when(motoristaService.listAll()).thenReturn(motoristas);

        MvcResult result = mockMvc
            .perform(MockMvcRequestBuilders.get("/motorista"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(motoristas), resp);
    }

    @Test
    void saveMotorista() throws Exception {
        MotoristaSaveDTO mSave = new MotoristaSaveDTO("Pedro", "ABC", "ABC", "M1", 100.00);
        MotoristaReturnDTO mReturn = new MotoristaReturnDTO("Pedro", "ABC", "ABC", "M1", "", "");

        Mockito.when(motoristaService.cadastrarMotorista(Mockito.any())).thenReturn(mReturn);

        ObjectMapper om = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/motorista")
            .contentType("application/json")
            .content(om.writeValueAsString(mSave)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mReturn), resp);
    }
    
    @Test
    void editMotorista() throws Exception {
        MotoristaEditDTO mEdit = new MotoristaEditDTO("ABC1D23", "Nissan GTR", 100.00, "INDISPONIVEL");
        MotoristaReturnDTO mReturn = new MotoristaReturnDTO("Leozão Bota Quente", "ABC1D23", "Nissan GTR", "Leo", "", "");

        Mockito.when(motoristaService.editarMotorista(Mockito.anyString(), Mockito.any())).thenReturn(mReturn);

        ObjectMapper om = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/motorista/{identifier}", "motorista123")
            .contentType("application/json")
            .content(om.writeValueAsString(mEdit)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        
        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mReturn), resp);
    }

    @Test
    void removeMotorista() throws Exception {
        Mockito.when(motoristaService.deleteMotorista(Mockito.anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/motorista/{identifier}", "motorista123"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(resp, "true");
    }


    @Test
    void cancelMotorista() throws Exception {
        MotoristaReturnDTO mReturn = new MotoristaReturnDTO("Leozao Bota Quente", "ABC1D23", "Nissan GTR", "Leo", "INDISPONIVEL", "CANCELADO");
        
        Mockito.when(motoristaService.cancelaMotorista(Mockito.anyString())).thenReturn(mReturn);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/motorista/{identifier}/cancel", "motorista123"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
    
        ObjectMapper om = new ObjectMapper();
        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(mReturn), resp);
    }
}
