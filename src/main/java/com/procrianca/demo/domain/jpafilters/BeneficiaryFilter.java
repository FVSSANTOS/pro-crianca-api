package com.procrianca.demo.domain.jpafilters;

import com.procrianca.demo.domain.enums.Gender;
import com.procrianca.demo.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class BeneficiaryFilter {
    private String name;
    private String nameResponsiblePerson;
    private String cpf;
    private Status status;
    private Gender gender;
    private double rentalValueMin;
    private double rentalValueMax;
    private Integer ageMin;
    private Integer ageMax;

    public BeneficiaryFilter() {
        this.name = "";
        this.nameResponsiblePerson = "";
        this.cpf = "";
        this.status = Status.QUALQUER;
        this.gender = Gender.QUALQUER;
        this.rentalValueMin = 0;
        this.rentalValueMax = Double.MAX_VALUE;
        this.ageMin = 0;
        this.ageMax = 200;
    }
}
