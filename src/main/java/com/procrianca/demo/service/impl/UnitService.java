package com.procrianca.demo.service.impl;

import com.procrianca.demo.domain.entity.Unit;
import com.procrianca.demo.domain.repository.UnitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    @Autowired
    private UnitRepository repository;

    @Transactional
    public Unit saveUnit(Unit unit){
        if(unit == null)
            throw new NullPointerException();

        return repository.save(unit);
    }

    public List<Unit> listAllUnits(){
        return repository.findAll();
    }

    public Unit update(Integer id, Unit unitUpdate) {
        var unit = this.repository.findById(id);

        if (unit.isEmpty()) return null;

        var beneficiaryModel = unit.get();

        BeanUtils.copyProperties(unitUpdate, beneficiaryModel);
        var unitSaved = this.repository.save(beneficiaryModel);
        return unitSaved;
    }

    public boolean deleteUnit(Integer id) {
        var unit = this.repository.findById(id);
        if (unit.isEmpty()) {
            return false;
        }
        this.repository.delete(unit.get());
        return true;
    }

    public Unit findUnitById(Integer id) {
        return repository.findUnitById(id);
    }
}
