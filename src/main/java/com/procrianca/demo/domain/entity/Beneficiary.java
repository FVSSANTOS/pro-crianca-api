package com.procrianca.demo.domain.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

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
    private String situation;

    @Column
    private Integer age;

   @Column  
    private String gender;

    @Column
    @CPF
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "units_id")
    private Unit units;
}
