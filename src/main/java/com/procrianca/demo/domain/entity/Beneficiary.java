package com.procrianca.demo.domain.entity;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String situation;

    @Column
    private Integer age;

   @Column  
    private String gender;

    @Column
    private String cpf;
}
