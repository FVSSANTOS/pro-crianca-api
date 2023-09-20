package com.procrianca.demo.rest.controller;


import com.procrianca.demo.domain.dtos.CredenciaisDTO;
import com.procrianca.demo.domain.dtos.TokenDTO;
import com.procrianca.demo.domain.response.AuthResponse;
import com.procrianca.demo.domain.response.HttpStatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.procrianca.demo.domain.entity.User;
import com.procrianca.demo.domain.security.jwt.JwtService;
import com.procrianca.demo.service.impl.UsuarioServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Users API", description = "API for user management")
public class UsuarioController {
    
    private final UsuarioServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))
    })
    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid User user){

        log.info("Calling endpoint to save a user in controller: " + log.getName());

        String passwordEncode = passwordEncoder.encode(user.getPassword());

        user.setPassword(passwordEncode);
        
        var userSaved = this.userService.saveUser(user);
        return userSaved;
    }


    @GetMapping("/usuarios")
    @ResponseStatus
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Calling endpoint to list all users in controller: " + log.getName());

        List<User> users = this.userService.listAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody CredenciaisDTO credenciais) {
        log.info("Calling endpoint to authenticate a users in controller: " + log.getName());

        try {
            User user = User.builder()
                    .login(credenciais.getLogin())
                    .password(credenciais.getPassword())
                    .build();

            UserDetails authenticated = userService.authenticate(user);

            if (incorrectPassword(user.getPassword(), authenticated.getPassword())) {
                String token = jwtService.gerarToken(user);
                System.out.println(token);
                return ResponseEntity.ok(new TokenDTO(token, "Logado com sucesso.", HttpStatusCode.OK.getValue()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Login ou senha incorreta.", HttpStatusCode.UNAUTHORIZED.getValue()) {});
            }
        } catch (UsernameNotFoundException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse("Usuário não encontrado.", HttpStatusCode.UNAUTHORIZED.getValue()) {});
        }
    }

    private boolean incorrectPassword(String senhaDigitada, String senhaArmazenada) {
        return passwordEncoder.matches(senhaDigitada, senhaArmazenada);
    }

    
}
