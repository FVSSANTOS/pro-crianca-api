package com.procrianca.demo.domain.repository;

import com.procrianca.demo.domain.entity.Beneficiary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BeneficiaryQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Beneficiary> customQuery(String cpf, String name, String situation, String gender, Integer idCollaborator) {

        StringBuilder query = new StringBuilder("from beneficiarios b where idCollaborator != 0");

        if (cpf != "" && cpf != null)  {
            query.append(" and b.cpf = :cpf");
        }

        if (name != "" && name != null)  {
            query.append(" and b.name = :name");
        }

        if (situation != "" && situation != null)  {
            query.append(" and b.situation = :situation");
        }

        if (gender != "" && gender != null)  {
            query.append(" and b.gender = :gender");
        }

        if (idCollaborator != 0 && idCollaborator != null)  {
            query.append(" and b.idCollaborator = :idCollaborator");
        }

        TypedQuery<Beneficiary> typedQuery = entityManager.createQuery(query.toString(), Beneficiary.class);
        if (cpf != "" && cpf != null)  {
            typedQuery.setParameter("cpf", cpf);
        }

        if (name != "" && name != null)  {
            typedQuery.setParameter("name", name);
        }

        if (situation != "" && situation != null)  {
            typedQuery.setParameter("situation", situation);
        }

        if (gender != "" && gender != null)  {
            typedQuery.setParameter("gender", gender);
        }

        if (idCollaborator != 0 && idCollaborator != null)  {
            typedQuery.setParameter("idCollaborator", idCollaborator);
        }

        return typedQuery.getResultList();
    }
}
