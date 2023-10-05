package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.entity.Beneficiary;
import com.procrianca.demo.domain.entity.Collaborator;
import com.procrianca.demo.domain.repository.CollaboratorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaboratorService {

    @Autowired
    private CollaboratorRepository repository;

    @Transactional
    public Collaborator saveCollaborator(Collaborator collaborator) {
        if(collaborator == null)
            throw new NullPointerException();

        return repository.save(collaborator);
    }

    public List<Collaborator> listAllCollaborators(){
        return repository.findAll();
    }

    public Collaborator update(Integer id, Collaborator collaboratorUpdate) {
        var collaborator = this.repository.findById(id);

        if (collaborator.isEmpty()) return null;

        var collaboratorModel = collaborator.get();

        BeanUtils.copyProperties(collaboratorUpdate, collaboratorModel);
        var collaboratorSaved = this.repository.save(collaboratorModel);
        return collaboratorSaved;
    }

    public boolean deleteCollaborator(Integer id) {
        var collaborator = this.repository.findById(id);
        if (collaborator.isEmpty()) {
            return false;
        }
        this.repository.delete(collaborator.get());
        return true;
    }
}
