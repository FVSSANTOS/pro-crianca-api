package main.java.com.procrianca.demo.domain.entity;

import com.procrianca.demo.domain.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private Sting cpf;

    @OneToOne(mappedBy = "collaborator", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;
}
