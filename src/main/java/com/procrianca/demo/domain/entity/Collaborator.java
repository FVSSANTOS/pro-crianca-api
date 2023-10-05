package com.procrianca.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;


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

    @OneToOne(mappedBy = "collaborator", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;

    @ManyToOne
    @JoinColumn(name = "units_id")
    private Unit units;
}
