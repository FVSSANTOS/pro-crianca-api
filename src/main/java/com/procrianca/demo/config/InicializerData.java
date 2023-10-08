package com.procrianca.demo.config;

import com.procrianca.demo.domain.entity.Collaborator;
import com.procrianca.demo.domain.entity.User;
import com.procrianca.demo.domain.repository.CollaboratorRepository;
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
    private final PasswordEncoder passwordEncoder;

    public InicializerData(CollaboratorRepository collaboratorRepository,UserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.collaboratorRepository = collaboratorRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User( 1, "administrator@email.com", "12345678", true, 
        null, now, now);
       
        String passwordEncode = passwordEncoder.encode(user.getPassword());

        user.setPassword(passwordEncode);
        usuarioRepository.save(user);
         Collaborator collaborator = new Collaborator(1, "jao", "70803247427", user, null);
        collaboratorRepository.save(collaborator);
    }
}