package com.crud.motorista.motorista;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.motorista.motorista.DTO.MotoristaEditDTO;
import com.crud.motorista.motorista.DTO.MotoristaReturnDTO;
import com.crud.motorista.motorista.DTO.MotoristaSaveDTO;

@Service
public class MotoristaService {
    
    @Autowired
    MotoristaRepository motoristaRepository;

    public List<MotoristaReturnDTO> listAll(){
        ArrayList<MotoristaReturnDTO> f = new ArrayList<MotoristaReturnDTO>();
        for (Motorista m : motoristaRepository.findAll()) {
            f.add(Motorista.converteReturnDTO(m));
        }
        return f;
    }

    public MotoristaReturnDTO cadastrarMotorista(MotoristaSaveDTO motorista){
        Motorista m = new Motorista(motorista.getName(), motorista.getCpf());
        m.setPlaca(motorista.getPlaca());
        m.setModelo(motorista.getModelo());
        m.setPrecoViagem(motorista.getPrecoViagem());
        m.setOcupacao("INDISPONIVEL");
        m.setStatus("PENDENTE");
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
        motorista.setOcupacao("INDISPONIVEL");
        motorista.setStatus("PENDENTE");
        motoristaRepository.save(motorista);

        return Motorista.converteReturnDTO(motorista);
    }

    public boolean deleteMotorista(String identifier) {
        Motorista m = motoristaRepository.findByIdentifier(identifier);
        if (m == null) return false;
        motoristaRepository.delete(m);
        return true;
    }

    public MotoristaReturnDTO cancelaMotorista(String identifier){
        Motorista m = motoristaRepository.findByIdentifier(identifier);
        if (m == null) return null;

        m.setStatus("CANCELADO");
        m.setOcupacao("INDISPONIVEL");
        motoristaRepository.save(m);
        return Motorista.converteReturnDTO(m);
    }

    public MotoristaReturnDTO validaMotorista(String identifier) {
        Motorista m = motoristaRepository.findByIdentifier(identifier);

        if (m == null) return null;

        if (m.getStatus() == "CANCELADO") return null;

        if (m.getPlaca() != null && (m.getPlaca().matches("^[a-zA-Z]{2}\\d{2}[a-zA-Z]{1}\\d{2}$") || m.getPlaca().matches("^[a-zA-Z]{3}\\d{4}$")) && m.getModelo() != null && m.getPrecoViagem() != null && m.getPrecoViagem() > 0) {
            m.setStatus("LIBERADO");
            m.setOcupacao("DISPONIVEL");
            motoristaRepository.save(m);
            return Motorista.converteReturnDTO(m);
        }

        m.setStatus("PENDENTE");
        motoristaRepository.save(m);
        return Motorista.converteReturnDTO(m);
    }

    public MotoristaReturnDTO motoristaDisponivel() {
        Motorista m = motoristaRepository.findFirstByOcupMotorista("DISPONIVEL");
        if (m == null) return null;
        m.setOcupacao("INDISPONIVEL");
        motoristaRepository.save(m);
        return Motorista.converteReturnDTO(m);
    }

    public MotoristaReturnDTO liberaMotorista(String identifier) {
        Motorista m = motoristaRepository.findByIdentifier(identifier);
        if (m == null) return null;

        if (m.getStatus() == "PENDENTE" || m.getStatus() == "CANCELADO") return null;

        m.setOcupacao("DISPONIVEL");
        motoristaRepository.save(m);
        return Motorista.converteReturnDTO(m);
    }



}
