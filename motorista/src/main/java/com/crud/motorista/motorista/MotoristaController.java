package com.crud.motorista.motorista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.motorista.motorista.DTO.MotoristaReturnDTO;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {
    @Autowired
    private MotoristaService motoristaService;

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
