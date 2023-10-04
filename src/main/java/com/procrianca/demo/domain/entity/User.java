package com.procrianca.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.java.com.procrianca.demo.domain.entity.Collaborator;

import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "collaborator_id")
    private Integer id;

    @Column(name = "login")
    @NotEmpty(message = "{Campo login é obrigratório}")
    @NotBlank
    @Length(min = 8, max = 255)
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "{Campo senha é obrigratório}")
    @NotBlank
    @Length(min = 8, max = 255)
    private String password;

    @Column(name = "admin")
    private boolean admin;

    @OneToOne
    @MapsId
    @JoinColumn(name = "collaborator_id")
    private Collaborator user;

    @NotNull
    LocalDateTime createdAt;

    @NotNull
    LocalDateTime updatedAt;

}
