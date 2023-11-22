package com.procrianca.demo.domain.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import com.procrianca.demo.domain.enums.BloodType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "Dados médicos dos beneficiarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeneficiaryMedic {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column
    private Double height;

    @Column
    private Double weight;

    @Column
    private String allergy;

    @Column
    private Boolean situationHome;

    @Column
    private BloodType bloodType;

    @Column
    private String illness;

    @Column
    private String oralHealth;

    @Column
    private String disability;

    @Column
    private String medications;

    @Column
    private Boolean usedDrugs;

    @Column
    private String whichDrugs;

    @Column
    private String psychosocial;

    @Column
    private String medicalNotes;
}
