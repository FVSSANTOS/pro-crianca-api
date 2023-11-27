package com.procrianca.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import com.procrianca.demo.domain.entity.Unit;
import com.procrianca.demo.domain.jpafilters.BeneficiaryFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.procrianca.demo.domain.entity.Beneficiary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {
    Optional<Beneficiary> findByCpf(String cpf);

    Unit findUnitById(Integer id);

    @Query("SELECT b FROM  Beneficiary b " +
            "WHERE " +
            "b.name LIKE CONCAT('%',:#{#filter.name},'%') AND " +
            "b.responsibleName LIKE CONCAT('%',:#{#filter.nameResponsiblePerson},'%') AND " +
            "b.cpf LIKE CONCAT('%',:#{#filter.cpf},'%') AND " +
            "(:#{#filter.status.getStatus()} = 0 OR b.status = :#{#filter.status}) AND " +
            "(:#{#filter.gender.getGender()} = 0 OR b.gender = :#{#filter.gender}) AND " +
            "b.rentalValue >= :#{#filter.rentalValueMin} AND " +
            "b.rentalValue <= :#{#filter.rentalValueMax} AND " +
            "b.age >= :#{#filter.ageMin} AND " +
            "b.age <= :#{#filter.ageMax}")
    List<Beneficiary> findAllWithFilter(@Param("filter") BeneficiaryFilter filter);
}