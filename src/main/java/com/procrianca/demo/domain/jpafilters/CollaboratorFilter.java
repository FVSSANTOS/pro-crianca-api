package com.procrianca.demo.domain.jpafilters;

import com.procrianca.demo.domain.enums.Gender;
import com.procrianca.demo.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CollaboratorFilter {
    private String name;
    private String role;
    private Status status;
    private Gender gender;

    public CollaboratorFilter() {
        this.name = "";
        this.role = "";
        this.status = Status.QUALQUER;
        this.gender = Gender.QUALQUER;
    }
}
