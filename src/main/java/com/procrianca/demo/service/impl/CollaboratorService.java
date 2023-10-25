package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.entity.Beneficiary;
import com.procrianca.demo.domain.entity.Collaborator;
import com.procrianca.demo.domain.entity.Unit;
import com.procrianca.demo.domain.entity.User;
import com.procrianca.demo.domain.repository.CollaboratorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
        if (collaborator == null || collaborator.getUnit() == null) {
            return null;
        }

        Collaborator collaboratorExists = this.repository.findCollaboratorByCpf(collaborator.getCpf());
        if (collaboratorExists != null) {
            return null;
        }

        for (Beneficiary beneficiary : collaborator.getBeneficiarios()) {
            beneficiary.setCollaborator(collaborator);
        }

        Integer unitId = collaborator.getUnit().getId();
        Unit unit = unitService.findUnitById(unitId);

        Integer userId = collaborator.getUser().getId();
        Optional<User> userOptional = userService.findUserById(userId);

        if (unit != null && userOptional.isPresent()) {
            collaborator.setUnit(unit);
            collaborator.setUser(userOptional.get());
            return repository.save(collaborator);
        }

        return null;
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
}
