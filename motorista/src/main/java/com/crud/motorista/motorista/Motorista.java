package com.crud.motorista.motorista;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Motorista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String identifier;

    @Column
    private String name;

    @Column
    private Integer cpf;

    @Column
    private String placa;

    @Column
    private String modelo;

    @Column
    private Double precoViagem;

    @Column
    private String status;

    @Column
    private String ocupacao;

}
