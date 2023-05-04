package com.crud.motorista.motorista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.motorista.motorista.DTO.MotoristaEditDTO;
import com.crud.motorista.motorista.DTO.MotoristaReturnDTO;
import com.crud.motorista.motorista.DTO.MotoristaSaveDTO;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {
    @Autowired
    private MotoristaService motoristaService;

    // Rota que lista todos motoristas
    @GetMapping
    public List<MotoristaReturnDTO> listAll() {
        return motoristaService.listAll();
    }

    // Rota que salva motorista
    @PostMapping
    public MotoristaReturnDTO salvarMotorista (@RequestBody MotoristaSaveDTO m) {
        return motoristaService.cadastrarMotorista(m);
    }

    // Rota que edita motorista
    @PutMapping
    public MotoristaReturnDTO editarMotorista (@RequestBody MotoristaEditDTO m, @PathVariable String identifier) {
        return motoristaService.editarMotorista(identifier, m);
    }

    // Rota que deleta o motorista (apaga da DB)
    @DeleteMapping("/{identifier}")
    public boolean deleteMotorista(@PathVariable String identifier) {
        return motoristaService.deleteMotorista(identifier);
    }

    // Rota que cancela o motorista (status CANCELADO e ocupacao INDISPONIVEL)
    @PutMapping("/{identifier}/cancel")
    public MotoristaReturnDTO cancelaMotorista(@PathVariable String identifier) {
        return motoristaService.cancelaMotorista(identifier);
    }
    
    // Rota que valida o motorista (status LIBERADO para fazer corridas)
    @GetMapping("/{identifier}")
    public MotoristaReturnDTO validaMotorista(@PathVariable String identifier) {
        return motoristaService.validaMotorista(identifier);
    }

    // Rota que retorna um motorista disponível aleatório
    @GetMapping("/available")
    public MotoristaReturnDTO motoristaDisponivel() {
        return motoristaService.motoristaDisponivel();
    }

    // Rota que altera o motorista de indisponível p/ disponível 
    @PostMapping("/{identifier}/ocupacao")
    public MotoristaReturnDTO liberaMotorista (@PathVariable String identifier) {
        return motoristaService.liberaMotorista(identifier);
    }
}
