package com.crud.motorista.motorista;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.motorista.motorista.DTO.MotoristaEditDTO;
import com.crud.motorista.motorista.DTO.MotoristaReturnDTO;
import com.crud.motorista.motorista.DTO.MotoristaSaveDTO;

public class MotoristaService {
    
    @Autowired
    MotoristaRepository motoristaRepository;

    public List<Motorista> listAll(){
        return motoristaRepository.findAll();
    }

    public Motorista cadastrarMotorista(MotoristaSaveDTO motorista){
        Motorista m = new Motorista(null, null);
        m.setCpf(motorista.getCpf());
        m.setName(motorista.getName());
        m.setPlaca(motorista.getPlaca());
        m.setModelo(motorista.getModelo());
        m.setPrecoViagem(motorista.getPrecoViagem());
        m.setOcupacao("Disponivel");
        m.setStatus("Pendente");
        m.setIdentifier(UUID.randomUUID().toString());
        return motoristaRepository.save(m);
    }

    public MotoristaReturnDTO editarMotorista(String identifier, MotoristaEditDTO m){
        Motorista motorista = motoristaRepository.findByIdentifier(identifier);
        if(m.getModelo()!=null && m.getPlaca()!=null){
            motorista.setModelo(m.getModelo());
            motorista.setPlaca(m.getPlaca());
        }
        if(m.getOcupacao()!=null){
            motorista.setOcupacao(m.getOcupacao());
        }
        if(m.getPrecoViagem()!=null){
            motorista.setPrecoViagem(m.getPrecoViagem());
        }

        MotoristaReturnDTO saida = new MotoristaReturnDTO(motorista.getName(), motorista.getPlaca(), motorista.getModelo());
        return saida;
    }
}
