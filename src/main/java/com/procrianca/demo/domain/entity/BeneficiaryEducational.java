package com.procrianca.demo.domain.entity;

import com.procrianca.demo.domain.enums.Shift;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "dados_educacionais_beneficiario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeneficiaryEducational {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column
    private String school;

    @Column
    private String phone;

    @Column
    private String academicYear;

    @Column
    @Enumerated(EnumType.STRING)
    private Shift shift;

    @Column
    private String cep;

    @Column
    private String email;

    @Column
    private String referencPoint;

    @Column
    private String previousSchool;

    @Column
    private String previousSchoolPhone;

    @Column
    private String observations;
}