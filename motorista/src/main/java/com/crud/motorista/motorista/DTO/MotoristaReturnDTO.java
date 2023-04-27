package com.crud.motorista.motorista.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class MotoristaReturnDTO {

    @Column
    private String name;

    @Column
    private String placa;

    @Column
    private String modelo;
    
}
