package com.crud.motorista.motorista;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MotoristaRepository extends JpaRepository<Motorista,Integer>{
    Motorista findbyOcupMotorista(String ocupacao); 
}
