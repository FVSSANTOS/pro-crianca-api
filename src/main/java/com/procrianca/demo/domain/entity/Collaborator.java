package com.procrianca.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;


@Entity
@Table(name = "colaboradores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Collaborator {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    @CPF
    private String cpf;

    @OneToOne(mappedBy = "collaborator")
    private User user;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @OneToMany(mappedBy = "collaborator")
    private List<Beneficiary> beneficiarios;

    public Collaborator(String collaborator, String number, User user, Unit unit, List<Beneficiary> beneficiarios) {
        this.name = collaborator;
        this.cpf = number;
        this.user = user;
        this.unit = unit;
        this.beneficiarios = beneficiarios;
    }
}
