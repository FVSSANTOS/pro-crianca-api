package com.procrianca.demo.service.impl;

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

    @Transactional
    public Beneficiary saveBeneficiary(Beneficiary beneficiary){
        if(beneficiary == null)
            throw new NullPointerException();
        
        return repository.save(beneficiary);
    }

    public List<Beneficiary> listAllBeneficiaries(){
        return repository.findAll();
    }

    public Beneficiary update(Integer id, Beneficiary beneficiaryUpdate) {
        var beneficiary = this.repository.findById(id);

        if (beneficiary.isEmpty()) return null;

        var beneficiaryModel = beneficiary.get();

        BeanUtils.copyProperties(beneficiaryUpdate, beneficiaryModel);
        var beneficiarySaved = this.repository.save(beneficiaryModel);
        return beneficiarySaved;
    }

    public boolean deleteBeneficiary(Integer id) {
        var beneficiary = this.repository.findById(id);
        if (beneficiary.isEmpty()) {
            return false;
        }
        this.repository.delete(beneficiary.get());
        return true;
    }
}
