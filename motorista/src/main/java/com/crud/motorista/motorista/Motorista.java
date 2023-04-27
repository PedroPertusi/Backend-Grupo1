package com.crud.motorista.motorista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.*;


@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Motorista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private Integer cpf;

    @Column
    @NonNull
    private String placa;

    @Column
    @NonNull
    private String modelo;

    @Column
    @NonNull
    private Double precoViagem;

    @Column
    private String status;

    @Column
    private String ocupacao;

}
