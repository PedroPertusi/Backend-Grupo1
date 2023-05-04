package com.crud.motorista.motorista;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crud.motorista.motorista.DTO.MotoristaEditDTO;
import com.crud.motorista.motorista.DTO.MotoristaReturnDTO;
import com.crud.motorista.motorista.DTO.MotoristaSaveDTO;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MotoristaServiceTest {
    
    @InjectMocks
    MotoristaService motoristaService;

    @Mock 
    MotoristaRepository motoristaRepository;

    @Test
    void listAllTest(){
        Motorista m1 = new Motorista("Pedro Pertusi", "12345");
        m1.setIdentifier("2204");
        m1.setName("Pedro Pertusi");
        m1.setPlaca("123deOliveira4");
        m1.setModelo("Ford Eco Sport");

        Motorista m2 = new Motorista("Leonardo Scarlato", "12345");
        m2.setIdentifier("6969");
        m2.setName("Leonardo Scarlato AKA Leozão Bota Quente");
        m2.setPlaca("ABC1D23");
        m2.setModelo("Nissan GTR");

        List<Motorista> motoristas = new ArrayList<Motorista>();
        motoristas.add(m1);
        motoristas.add(m2);

        Mockito.when(motoristaRepository.findAll()).thenReturn(motoristas);

        List<MotoristaReturnDTO> resp = motoristaService.listAll();

        Assertions.assertEquals(2, resp.size());
    }

    @Test
    void cadastrarMotoristaTest() {
        MotoristaSaveDTO m1 = new MotoristaSaveDTO(null, null, null, null, null);
        m1.setName("Pedro Pertusi");
        m1.setCpf("12345");
        m1.setPlaca("123deOliveira4");
        m1.setModelo("Ford Eco Sport");
        m1.setPrecoViagem(19.90);


        MotoristaReturnDTO resp = motoristaService.cadastrarMotorista(m1);


        Assertions.assertEquals(m1.getName(), resp.getName());
        Assertions.assertEquals(m1.getPlaca(), resp.getPlaca());
        Assertions.assertEquals(m1.getModelo(), resp.getModelo());

    }
    

    @Test
    void editarMotoristaTest(){
        Motorista m1 = new Motorista("Pedro Pertusi", "12345");
        m1.setPlaca("123deOliveira4");
        m1.setModelo("Ford Eco Sport");
        m1.setPrecoViagem(19.90);
        m1.setOcupacao("INDISPONIVEL");
        m1.setStatus("PENDENTE");


        Mockito.when(motoristaRepository.findByIdentifier(Mockito.any())).thenReturn(m1);

        MotoristaEditDTO editDTO = new MotoristaEditDTO(null, null, null, null);
        editDTO.setOcupacao("DISPONIVEL");
        editDTO.setModelo("Fusca");
        editDTO.setPlaca("231oliveiraDe4");
        editDTO.setPrecoViagem(18.90);

        MotoristaReturnDTO resp = motoristaService.editarMotorista(null, editDTO);

        Assertions.assertEquals(m1.getName(), resp.getName());
        Assertions.assertEquals(m1.getPlaca(), resp.getPlaca());
        Assertions.assertEquals(m1.getModelo(), resp.getModelo());
        Assertions.assertEquals(m1.getOcupacao(), resp.getOcupacao());
        Assertions.assertEquals(m1.getStatus(), resp.getStatus());


    }


}
