package com.procrianca.demo.domain.repository;

import com.procrianca.demo.domain.entity.Collaborator;
import com.procrianca.demo.domain.jpafilters.CollaboratorFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator,Integer> {
    Collaborator findCollaboratorById(Integer id);
    Collaborator findCollaboratorByCpf(String cpf);

    @Query("SELECT c FROM  Collaborator c " +
            "WHERE " +
            "c.name LIKE CONCAt('%',:#{#filter.name},'%') AND " +
            "c.role LIKE CONCAt('%',:#{#filter.role},'%') AND " +
            "c.cpf LIKE CONCAt('%',:#{#filter.cpf},'%') AND" +
            "(:#{#filter.status.getStatus()} = 0 OR c.status = :#{#filter.status}) AND " +
            "(:#{#filter.gender.getGender()} = 0 OR c.gender = :#{#filter.gender})")
    List<Collaborator> findAllWithFilter(@Param("filter") CollaboratorFilter filter);
}
