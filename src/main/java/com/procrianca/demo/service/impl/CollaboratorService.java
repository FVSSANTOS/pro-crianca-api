package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.entity.Beneficiary;
import com.procrianca.demo.domain.entity.Collaborator;
import com.procrianca.demo.domain.entity.Unit;
import com.procrianca.demo.domain.jpafilters.CollaboratorFilter;
import com.procrianca.demo.domain.repository.CollaboratorRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Slf4j
@Service
public class CollaboratorService {

    @Autowired
    private CollaboratorRepository repository;

    @Autowired
    private UnitService unitService;

    @Autowired
    private UserServiceImpl userService;

    @Transactional
    public Collaborator saveCollaborator(Collaborator collaborator) {
        var collaboratorIsNotValid = collaborator == null
                || collaborator.getUnit() == null;

        if (collaboratorIsNotValid) {
            return null;
        }

        Collaborator collaboratorExists = this.repository.findCollaboratorByCpf(collaborator.getCpf());

        Integer unitId = collaborator.getUnit().getId();

        Unit unit = unitService.findUnitById(unitId);


        if (unit == null || collaboratorExists != null) {
            return null;
        }

        collaborator.setUnit(unit);

        for (Beneficiary beneficiary : collaborator.getBeneficiarios()) {
            beneficiary.setCollaborator(collaborator);
        }

        return repository.save(collaborator);
    }


    public List<Collaborator> listAllCollaborators(){
        return repository.findAll();
    }

    public Collaborator update(Integer id, Collaborator collaboratorUpdate) {
        var collaborator = this.repository.findById(id);

        var beneficiaryIsNotValid =
                collaborator.isEmpty() || collaborator == null;

        if (beneficiaryIsNotValid) return null;

        var collaboratorModel = collaborator.get();

        BeanUtils.copyProperties(collaboratorUpdate, collaboratorModel);
        var collaboratorSaved = this.repository.save(collaboratorModel);
        return collaboratorSaved;
    }

    public boolean deleteCollaborator(Integer id) {
        var collaborator = this.repository.findById(id);
        var collaboratorIsNotValid =
                collaborator.isEmpty() || collaborator == null;

        if (collaboratorIsNotValid) {
            return false;
        } else {
            this.repository.delete(collaborator.get());
            return true;
        }
    }

    public Collaborator findCollaboratorById(Integer id) {
        return repository.findCollaboratorById(id);
    }

    public List<Collaborator> listCollaboratorsWithFilter(CollaboratorFilter collaboratorFilter) {
        log.info(collaboratorFilter.toString());
        return repository.findAllWithFilter(collaboratorFilter);
    }

    public Collaborator findById(Integer id) {
        var optionalCollaborator = repository.findById(id);

        if(optionalCollaborator.isEmpty())
            throw new NoSuchElementException("ERROR: Collaborator not found");

        return optionalCollaborator.get();
    }
}
