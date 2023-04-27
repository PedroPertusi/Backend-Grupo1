package com.crud.motorista.motorista;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.motorista.motorista.DTO.MotoristaSaveDTO;

public class MotoristaService {
    
    @Autowired
    MotoristaRepository motoristaRepository;

    public List<Motorista> listAll(){
        return motoristaRepository.findAll();
    }

    public Motorista cadastrarMotorista(Integer id, MotoristaSaveDTO motorista){
        Motorista m = motoristaRepository.findById(id);
        m.setOcupacao("Disponivel");
        m.setStatus("Pendente");
        return motoristaRepository.save(m);
    }

    public Motorista editarMotorista(Motorista m){
        Motorista motorista = motoristaRepository.findById(m.getId());
    }
}
