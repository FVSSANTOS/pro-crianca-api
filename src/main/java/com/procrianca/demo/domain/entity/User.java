package com.procrianca.demo.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int admin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "colaboradores_id", referencedColumnName = "id")
    private Collaborator collaborator;

    @NotNull
    LocalDateTime createdAt;

    @NotNull
    LocalDateTime updatedAt;

    public User(String mail, String number, int b, Object o, LocalDateTime now, LocalDateTime now1) {
        this.login = mail;
        this.password = number;
        this.admin = b;
        this.collaborator = (Collaborator) o;
        this.createdAt = now;
        this.updatedAt = now1;
    }
}
