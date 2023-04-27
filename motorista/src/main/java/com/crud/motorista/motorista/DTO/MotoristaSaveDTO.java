package com.crud.motorista.motorista.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotoristaSaveDTO {

    @NonNull
    private String name;

    @NonNull
    private Integer cpf;

    private String placa;

    private String modelo;

    private Double precoViagem;

    private String status;

    private String ocupacao; 
    
}
