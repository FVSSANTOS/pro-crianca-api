package com.procrianca.demo.rest.controller;

import com.procrianca.demo.domain.dtos.UserRecordDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.procrianca.demo.domain.entity.Usuario;
import com.procrianca.demo.service.impl.UsuarioServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
@Tag(name = "Users API", description = "API for user management")
public class UsuarioController {
    
    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/usuarios")
    @ResponseStatus
    public ResponseEntity<List<Usuario>> getAllProducts() {
        List<Usuario> products = this.usuarioService.listAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)))
    })
    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity salvar (@RequestBody Usuario user){

        log.info("Calling endpoint to save a user in controller: " + log.getName());

        String passwordEncode = passwordEncoder.encode(user.getSenha());

        var userNew = new Usuario();
        BeanUtils.copyProperties(user, userNew);

        userNew.setSenha(passwordEncode);
        BeanUtils.copyProperties(userNew, user);

        var userSaved = this.usuarioService.salvar(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }
}
