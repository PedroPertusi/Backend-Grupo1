package com.crud.motorista.motorista.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MotoristaReturnDTO {

    private String name;

    private String placa;

    private String modelo;

    private  Double precoViagem;

    private String identifier;

    private String ocupacao;

    private String status;
    
}
