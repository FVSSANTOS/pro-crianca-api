package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.entity.BeneficiaryMedic;
import com.procrianca.demo.domain.entity.Collaborator;
import com.procrianca.demo.domain.entity.Unit;
import com.procrianca.demo.domain.jpafilters.BeneficiaryFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procrianca.demo.domain.entity.Beneficiary;
import com.procrianca.demo.domain.repository.BeneficiaryRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Slf4j
@Service
public class BeneficiaryService {

    @Autowired
    private BeneficiaryRepository repository;

    @Autowired
    private UnitService unitService;

    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    private BeneficiaryMedicService beneficiaryMedicService;


    @Transactional
    public Beneficiary saveBeneficiary(Beneficiary beneficiary) {
        var beneficiaryIsNotValid =
                beneficiary == null
                        || beneficiary.getUnit() == null
                        || beneficiary.getBeneficiaryMedic() == null;

        if (beneficiaryIsNotValid) {
            return null;
        }


        BeneficiaryMedic beneficiaryMedic = beneficiaryMedicService.saveBeneficiaryMedical(beneficiary.getBeneficiaryMedic());


        Integer unitId = beneficiary.getUnit().getId();
        Unit unit = unitService.findUnitById(unitId);

        beneficiary.setUnit(unit);
        beneficiary.setBeneficiaryMedic(beneficiaryMedic);

        if (beneficiary.getCollaborator() != null) {

            Integer collaboratorId = beneficiary.getCollaborator().getId();
            Collaborator collaborator = collaboratorService.findCollaboratorById(collaboratorId);
            beneficiary.setCollaborator(collaborator);
        }


        return repository.save(beneficiary);

    }

    public List<Beneficiary> listAllBeneficiaries() {
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

    public List<Beneficiary> listBeneficiariesWithFilter(BeneficiaryFilter beneficiaryFilter) {
        log.info(beneficiaryFilter.toString());

        return repository.findAllWithFilter(beneficiaryFilter);

    }
}
