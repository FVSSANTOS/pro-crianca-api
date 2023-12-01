package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.entity.BeneficiaryEducational;
import com.procrianca.demo.domain.repository.BeneficiaryEducationalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryEducationalService {

    @Autowired
    private BeneficiaryEducationalRepository beneficiaryEducationalRepository;

    public BeneficiaryEducational saveBeneficiaryEducational(BeneficiaryEducational beneficiaryEducational) {
        return beneficiaryEducationalRepository.save(beneficiaryEducational);
    }
}
