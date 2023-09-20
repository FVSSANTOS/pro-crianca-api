package com.procrianca.demo.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procrianca.demo.domain.entity.Beneficiary;


public interface BeneficiaryRespository extends JpaRepository<Beneficiary,Integer>{
    Optional<Beneficiary> findByCpf(String cpf);
}
