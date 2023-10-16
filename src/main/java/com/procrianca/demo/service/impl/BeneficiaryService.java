package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.entity.Collaborator;
import com.procrianca.demo.domain.entity.Unit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procrianca.demo.domain.entity.Beneficiary;
import com.procrianca.demo.domain.repository.BeneficiaryRespository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class BeneficiaryService {

    @Autowired
    private BeneficiaryRespository repository;

    @Autowired
    private UnitService unitService;

    @Autowired
    private CollaboratorService collaboratorService;


    @Transactional
    public Beneficiary saveBeneficiary(Beneficiary beneficiary){
        var beneficiaryIsNotValid =
                beneficiary.getUnit() == null || beneficiary == null;

        if (beneficiary.getUnit() != null && beneficiary.getCollaborator() != null) {
            Integer unitId = beneficiary.getUnit().getId();
            Unit unit = unitService.findUnitById(unitId);

            Integer collaboratorId = beneficiary.getCollaborator().getId();
            Collaborator collaborator = collaboratorService.findCollaboratorById(collaboratorId);

            beneficiary.setUnit(unit);
            beneficiary.setCollaborator(collaborator);
        }


        if (beneficiaryIsNotValid) {
            return null;
        } else {
            return repository.save(beneficiary);
        }
    }

    public List<Beneficiary> listAllBeneficiaries(){
        return repository.findAll();
    }

    public Beneficiary update(Integer id, Beneficiary beneficiaryUpdate) {
        var beneficiary = this.repository.findById(id);

        var beneficiaryIsNotValid =
                beneficiary.isEmpty() || beneficiary == null;

        if (beneficiaryIsNotValid) return null;

        var beneficiaryModel = beneficiary.get();

        BeanUtils.copyProperties(beneficiaryUpdate, beneficiaryModel);
        var beneficiarySaved = this.repository.save(beneficiaryModel);
        return beneficiarySaved;
    }

    public boolean deleteBeneficiary(Integer id) {
        var beneficiary = this.repository.findById(id);

        var beneficiaryIsNotValid =
                beneficiary.isEmpty() || beneficiary == null;

        if (beneficiaryIsNotValid) {
            return false;
        } else {
            this.repository.delete(beneficiary.get());
            return true;
        }
    }
}
