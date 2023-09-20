package com.procrianca.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.procrianca.demo.domain.entity.Beneficiary;
import com.procrianca.demo.domain.repository.BeneficiaryRespository;

import jakarta.transaction.Transactional;

@Service
public class BeneficaryService {

    @Autowired
    private BeneficiaryRespository respository;

    @Transactional
    public Beneficiary saveBeneficiary(Beneficiary beneficiary){
        if(beneficiary == null)
            throw new NullPointerException();
        
        return respository.save(beneficiary);
    }
}
