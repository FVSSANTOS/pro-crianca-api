package com.procrianca.demo.domain.repository;

import com.procrianca.demo.domain.entity.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaboratorRepository extends JpaRepository<Collaborator,Integer> {
    
}
