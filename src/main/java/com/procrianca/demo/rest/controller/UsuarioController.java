package com.procrianca.demo.rest.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.procrianca.demo.domain.entity.Usuario;
import com.procrianca.demo.service.impl.UsuarioServiceImpl;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class UsuarioController {
    
    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/usuarios")
    @ResponseStatus
    public ResponseEntity<List<Usuario>> getAllProducts() {
        List<Usuario> products = this.usuarioService.listAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar (@RequestBody @Valid Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.salvar(usuario);
    }
}
