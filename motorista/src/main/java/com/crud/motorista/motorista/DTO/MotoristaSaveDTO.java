package com.crud.motorista.motorista.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotoristaSaveDTO {

    private String name;

    private String cpf;

    private String placa;

    private String modelo;

    private Double precoViagem;

    private String status;

    private String ocupacao; 
    
}
