package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.entity.BeneficiaryMedic;
import com.procrianca.demo.domain.repository.BeneficiaryMedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryMedicService {

    @Autowired
    private BeneficiaryMedicRepository beneficiaryMedicRepository;

    public BeneficiaryMedic saveBeneficiaryMedical(BeneficiaryMedic beneficiaryMedic){
        return beneficiaryMedicRepository.save(beneficiaryMedic);
    }
}
