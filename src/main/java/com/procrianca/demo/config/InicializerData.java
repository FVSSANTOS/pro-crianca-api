package com.procrianca.demo.config;

import com.procrianca.demo.domain.entity.Collaborator;
import com.procrianca.demo.domain.entity.Unit;
import com.procrianca.demo.domain.entity.User;
import com.procrianca.demo.domain.repository.CollaboratorRepository;
import com.procrianca.demo.domain.repository.UnitRepository;
import com.procrianca.demo.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//classe implementada para cadastrar usuário inicial(serve para a fase de testes)
@Component
public class InicializerData implements CommandLineRunner {

    private final UserRepository usuarioRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final UnitRepository unitRepository;
    private final PasswordEncoder passwordEncoder;

    public InicializerData(UserRepository usuarioRepository,
                           CollaboratorRepository collaboratorRepository,
                           UnitRepository unitRepository,
                           PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.collaboratorRepository = collaboratorRepository;
        this.unitRepository = unitRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        LocalDateTime now = LocalDateTime.now();

        // Cria e salva a unidade (Unit)
        Unit unit = new Unit("Unidade 1", "Rua 1", "Bairro 1", null, null);
        unitRepository.save(unit);

        // Cria e salva o usuário (User)
        User user = new User("administrator@email.com", "12345678", true, null, now, now);
        user = usuarioRepository.save(user);

        // Codifica a senha e atualiza o usuário
        String passwordEncode = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncode);
        user = usuarioRepository.save(user);

        // Cria e salva o colaborador (Collaborator) associado ao usuário e unidade
        Collaborator collaborator = new Collaborator("Collaborator", "71200642406", user, unit);
        collaborator = collaboratorRepository.save(collaborator);

        // Associa o usuário ao colaborador
        user.setCollaborator(collaborator);
    }
}