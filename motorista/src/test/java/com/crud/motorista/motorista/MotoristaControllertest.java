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
import com.crud.motorista.motorista.DTO.MotoristaReturnDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class MotoristaControllertest {

    MockMvc mockMvc;

    @InjectMocks
    MotoristaController motoristaController;

    @Mock
    MotoristaService motoristaService;

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
    
}
