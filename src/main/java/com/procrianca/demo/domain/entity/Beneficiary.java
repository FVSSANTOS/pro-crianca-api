package com.procrianca.demo.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.hibernate.annotations.Columns;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.boot.actuate.autoconfigure.health.HealthProperties.Status;

import com.procrianca.demo.domain.enums.Gender;
import com.procrianca.demo.domain.enums.Race;
import com.procrianca.demo.domain.enums.Shift;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "beneficiarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Beneficiary {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String responsibleName;
   
    @Column
    private Status status;

    @Column
    private Integer age;

    @Column  
    private Gender gender;

    @Column
    private Race race;

    @Column 
    private Shift shift;

    @Column
    @CPF
    private String cpf;

    @Column
    private Date exit;

    @Column
    private Date prohibited;

    @Column
    private Date dateBirth;

    @Column
    private String religion;

    @Column
    private String rg;

    @Column
    private String reservist;

    @Column
    private String voterRegistration;

    @Column
    private String vem;

    @Column
    private Integer pass;

    @Column
    private Integer quantity;
    
    @Column
    private String project;

    @Column
    private String courses;

    @Column
    private String other;

    @Column(name = "native")
    private String nati;

    @Column
    private String registration;

    @Column
    private String professionalActivity;

    @Column
    private String forwarded;

    @Column
    private String city;

    @Column
    private String insertionMotif;

    @Column
    private String address;

    @Column
    private String neighborhood;

    @Column
    private String reference;

    @Column
    private String cep;

    @Column
    private String phone;

    @Column
    private String dwelling;

    @Column
    private Double rentalValue;

    @Column
    @Email
    private String email;

    @Column
    private String type;

    @Column
    private Integer numberRooms;

    @Column
    private boolean sanitized;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "collaborator_id")
    private Collaborator collaborator;
}
