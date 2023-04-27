package com.crud.motorista.motorista.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotoristaEditDTO {
    private String placa;

    private String modelo;

    private Double precoViagem;
    
    private String ocupacao;
}
