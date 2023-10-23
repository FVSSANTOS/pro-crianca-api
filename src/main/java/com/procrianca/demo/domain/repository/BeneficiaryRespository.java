package com.procrianca.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import com.procrianca.demo.domain.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import com.procrianca.demo.domain.entity.Beneficiary;
import org.springframework.data.jpa.repository.Query;


public interface BeneficiaryRespository extends JpaRepository<Beneficiary,Integer>{
    List<Beneficiary> findBeneficiaryByCpf(String cpf);

    List<Beneficiary> findBeneficiaryByName(String name);

    List<Beneficiary> findBeneficiaryBySituation(String situation);

    List<Beneficiary> findBeneficiaryByGender(String gender);

    // @Query("from beneficiario b where b.id_colaborador = :idCollaborator") // JPQL
    List<Beneficiary> findBeneficiaryByIdCollaborator(Integer idCollaborator);
}
