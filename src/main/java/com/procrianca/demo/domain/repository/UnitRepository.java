package com.procrianca.demo.domain.repository;

import com.procrianca.demo.domain.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Integer> {

    Unit findUnitById(Integer id);
}
