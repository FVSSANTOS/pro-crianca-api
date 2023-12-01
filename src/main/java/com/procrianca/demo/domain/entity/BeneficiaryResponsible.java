package com.procrianca.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.procrianca.demo.domain.enums.Relationship;

import java.util.Date;

@Entity
@Table(name = "dados_responsaveis_beneficiario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeneficiaryResponsible {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private Date dateBirth;

    @Column
    private String cpf;

    @Column
    private String rg;

    @Column
    private String occupation;

    @Column
    private String institution;

    @Column
    private String maritalStatus;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private Relationship relationship;
}
