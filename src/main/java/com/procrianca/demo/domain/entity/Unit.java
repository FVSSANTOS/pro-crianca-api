package com.procrianca.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Entity
@Table(name = "Unidades")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String street;

    @Column
    private String number;

    @JsonIgnore
    @OneToMany(mappedBy = "units", fetch = FetchType.LAZY)
    private Set<Collaborator> collaborators;

    @JsonIgnore
    @OneToMany(mappedBy = "units", fetch = FetchType.LAZY)
    private Set<Beneficiary> beneficiaries;
}
