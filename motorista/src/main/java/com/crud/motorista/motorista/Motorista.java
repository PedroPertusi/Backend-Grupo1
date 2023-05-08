package com.crud.motorista.motorista;

import com.crud.motorista.motorista.DTO.MotoristaReturnDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.*;


@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Motorista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String identifier;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private String cpf;

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

    public static MotoristaReturnDTO converteReturnDTO(Motorista m){
        return new MotoristaReturnDTO(m.getName(), m.getPlaca(), m.getModelo(), m.getIdentifier(), m.getOcupacao(), m.getStatus());
    }

}
