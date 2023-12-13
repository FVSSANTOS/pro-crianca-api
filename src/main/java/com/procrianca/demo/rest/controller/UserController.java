package com.procrianca.demo.rest.controller;


import com.procrianca.demo.domain.dtos.AuthoritiesDTO;
import com.procrianca.demo.domain.dtos.CredentialsDTO;
import com.procrianca.demo.domain.dtos.TokenDTO;
import com.procrianca.demo.domain.dtos.UserRecordDto;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.procrianca.demo.domain.entity.User;
import com.procrianca.demo.domain.security.jwt.JwtService;
import com.procrianca.demo.service.impl.UserServiceImpl;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Users API", description = "API for user management")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))
    })
    @PostMapping("/users")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse> saveUser(@RequestBody @Valid User user) {

        log.info("Calling endpoint to save a user in controller: " + log.getName());

        String passwordEncode = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncode);

        var userSaved = this.userService.saveUser(user);
        if (userSaved == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Usuário já cadastrado na base de dados.", HttpStatusCode.BAD_REQUEST.getValue()) {
            });
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("Usuário criado com sucesso.", HttpStatusCode.CREATED.getValue()) {
        });
    }

    @Operation(summary = "Retrieve all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed Users"),
            @ApiResponse(responseCode = "404", description = "Users not found")
    })
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Calling endpoint to list all users in controller: " + log.getName());
        List<User> users = this.userService.listAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((List<User>) new AuthResponse("Não existe nenhum usuário.", HttpStatusCode.NOT_FOUND.getValue()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @Operation(summary = "Authenticate user")
    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody CredentialsDTO credenciais) {
        log.info("Calling endpoint to authenticate a users in controller: " + log.getName());

        User user = User.builder()
                .login(credenciais.getLogin())
                .password(credenciais.getPassword())
                .build();

        UserDetails authenticated = userService.authenticate(user);

        if (authenticated != null) {
            if (correctPassword(user.getPassword(), authenticated.getPassword())) {
                String token = jwtService.generateToken(user);

                AuthoritiesDTO authoritiesDTO = AuthoritiesDTO.fromUserDetails(authenticated);

                return ResponseEntity.ok(new TokenDTO(token, "Logado com sucesso.", HttpStatusCode.OK.getValue(), authoritiesDTO));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Login ou senha incorreta.", HttpStatusCode.UNAUTHORIZED.getValue()) {
        });

    }

    @Operation(summary = "Create user and collaborator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create user and collaborator"),
            @ApiResponse(responseCode = "400", description = "Cannot create user and collaborator")
    })
    @PostMapping("/userscollaborator")
    public ResponseEntity<User> createUserCollaborator(@RequestBody @Valid User user) {
        log.info("Calling endpoint to add user in controller: " + log.getName());

        try {
            User userInserted = userService.insertUserColaborator(user);
            return ResponseEntity.ok(userInserted);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    private boolean correctPassword(String senhaDigitada, String senhaArmazenada) {
        return passwordEncoder.matches(senhaDigitada, senhaArmazenada);
    }


}
