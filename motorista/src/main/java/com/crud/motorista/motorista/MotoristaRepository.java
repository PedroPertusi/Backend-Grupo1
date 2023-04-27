package com.crud.motorista.motorista;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MotoristaRepository extends JpaRepository<Motorista,Integer>{
    Motorista findByOcupMotorista(String ocupacao); 
    Motorista findByIdentifier(String identifier);
}
