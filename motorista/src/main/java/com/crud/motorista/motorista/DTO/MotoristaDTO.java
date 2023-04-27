package com.crud.motorista.motorista.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MotoristaDTO {

    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private String placa;

    @Column
    private String modelo;
    
}
