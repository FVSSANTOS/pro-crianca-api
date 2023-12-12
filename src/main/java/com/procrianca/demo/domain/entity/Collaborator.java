package com.procrianca.demo.domain.entity;

import com.procrianca.demo.domain.enums.Gender;
import com.procrianca.demo.domain.enums.Shift;
import com.procrianca.demo.domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
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
    private String cpf;

    @Column
    private String rg;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Date dateBirth;

    @Column
    private String phone;

    @Column
    @Email
    private String email;

    @Column
    private String role;

    @Column
    @Enumerated(EnumType.STRING)
    private Shift shift;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private Date admissionDate;

    @Column
    private String cep;

    @Column
    private String state;

    @Column
    private String city;

    @Column
    private String neighborhood;

    @Column
    private String address;

    @Column
    private String apartment;

    @Column
    private Integer houseNumber;

    @Column
    private String observations;

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
