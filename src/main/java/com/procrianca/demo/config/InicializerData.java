package com.procrianca.demo.config;

import com.procrianca.demo.domain.entity.User;
import com.procrianca.demo.domain.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InicializerData implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public InicializerData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1, "administrator@email.com", "12345678", true, now, now);
        String passwordEncode = passwordEncoder.encode(user.getPassword());

        user.setPassword(passwordEncode);
        usuarioRepository.save(user);
    }
}