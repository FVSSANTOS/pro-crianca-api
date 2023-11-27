package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.entity.BeneficiaryResponsible;
import com.procrianca.demo.domain.repository.BeneficiaryResponsibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryResponsibleService {

    @Autowired
    private BeneficiaryResponsibleRepository beneficiaryResponsibleRepository;

    public BeneficiaryResponsible saveBeneficiaryResponsible(BeneficiaryResponsible beneficiaryResponsible) {
        return beneficiaryResponsibleRepository.save(beneficiaryResponsible);
    }
}
