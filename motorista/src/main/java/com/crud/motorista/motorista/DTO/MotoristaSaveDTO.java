package com.crud.motorista.motorista.DTO;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MotoristaSaveDTO {

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
    private Double precoViagem;

    @Column
    private String status;

    @Column
    private String ocupacao; 
    
}
