package com.crud.motorista.motorista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.motorista.motorista.DTO.MotoristaDTO;

public class MotoristaService {
    
    @Autowired
    MotoristaRepository motoristaRepository;

    public List<Motorista> listAll(){
        return motoristaRepository.findAll();
    }

    public Motorista cadastrarMotorista(MotoristaDTO motorista){
        Motorista m = motoristaRepository.findById(motorista.getId());
        m.setOcupacao("Disponivel");
        m.setStatus("Pendente");
        return motoristaRepository.save(m);
    }

    public Motorista editarMotorista(Motorista m){

    }
}
