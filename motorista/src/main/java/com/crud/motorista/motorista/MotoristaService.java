package com.crud.motorista.motorista;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.motorista.motorista.DTO.MotoristaEditDTO;
import com.crud.motorista.motorista.DTO.MotoristaReturnDTO;
import com.crud.motorista.motorista.DTO.MotoristaSaveDTO;

public class MotoristaService {
    
    @Autowired
    MotoristaRepository motoristaRepository;

    public List<MotoristaReturnDTO> listAll(){
        return motoristaRepository.findAll().stream().map(motorista->Motorista.converteReturnDTO(motorista)).collect(Collectors.toList());
    }

    public MotoristaReturnDTO cadastrarMotorista(MotoristaSaveDTO motorista){
        Motorista m = new Motorista(null, null);
        m.setCpf(motorista.getCpf());
        m.setName(motorista.getName());
        m.setPlaca(motorista.getPlaca());
        m.setModelo(motorista.getModelo());
        m.setPrecoViagem(motorista.getPrecoViagem());
        m.setOcupacao("Disponivel");
        m.setStatus("Pendente");
        m.setIdentifier(UUID.randomUUID().toString());
        motoristaRepository.save(m);

        return Motorista.converteReturnDTO(m);
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
        motoristaRepository.save(motorista);

        MotoristaReturnDTO saida = new MotoristaReturnDTO(motorista.getName(), motorista.getPlaca(), motorista.getModelo());
        return saida;
    }

    public MotoristaReturnDTO validaMotorista(String identifier) {
        Motorista m = motoristaRepository.findByIdentifier(identifier);

        if (m == null) return null;

        if (m.getStatus() == "CANCELADO") return null;

        if (m.getPlaca() != null && (m.getPlaca().matches("^[a-zA-Z]{2}\\d{2}[a-zA-Z]{1}\\d{2}$") || m.getPlaca().matches("^[a-zA-Z]{3}\\d{4}$")) && m.getModelo() != null && m.getPrecoViagem() != null && m.getPrecoViagem() > 0) {
            m.setStatus("LIBERADO");
            motoristaRepository.save(m);
            return Motorista.converteReturnDTO(m);
        }

        m.setStatus("PENDENTE");
        motoristaRepository.save(m);
        return Motorista.converteReturnDTO(m);
        

    }

    public MotoristaReturnDTO getMotoristaDisponivel() {
        Motorista m = motoristaRepository.findFirstByOcupMotorista("DISPONIVEL");
        if (m == null) return null;
        m.setOcupacao("INDISPONIVEL");
        motoristaRepository.save(m);
        return Motorista.converteReturnDTO(m);
    }

    

}
