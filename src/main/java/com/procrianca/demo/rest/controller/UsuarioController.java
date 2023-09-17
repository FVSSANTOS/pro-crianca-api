package com.procrianca.demo.rest.controller;

import com.procrianca.demo.domain.dtos.CredenciaisDTO;
import com.procrianca.demo.domain.dtos.TokenDTO;
import com.procrianca.demo.domain.dtos.UserRecordDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.procrianca.demo.domain.entity.Usuario;
import com.procrianca.demo.domain.security.jwt.JwtService;
import com.procrianca.demo.exception.SenhaInvalidaException;
import com.procrianca.demo.service.impl.UsuarioServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Users API", description = "API for user management")
public class UsuarioController {
    
    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class)))
    })
    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar (@RequestBody @Valid Usuario user){

        log.info("Calling endpoint to save a user in controller: " + log.getName());

        String passwordEncode = passwordEncoder.encode(user.getPassword());

        user.setPassword(passwordEncode);
        
        var userSaved = this.usuarioService.salvar(user);
        return userSaved;
    }


    @GetMapping("/usuarios")
    @ResponseStatus
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> products = this.usuarioService.listAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenDTO> autenticar(@RequestBody CredenciaisDTO credenciais){
        try {         
           Usuario usuario = Usuario.builder()
                    .login(credenciais.login())
                    .password(credenciais.password())
                    .build();
           UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);                          

           String token = jwtService.gerarToken(usuario);
            
           return ResponseEntity.ok(new TokenDTO(usuario.getLogin(), token));
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } 
    }

    
}
