package com.procrianca.demo.domain.repository;

import com.procrianca.demo.domain.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit,Integer> {
}
