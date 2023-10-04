package com.procrianca.demo.rest.controller;

import com.procrianca.demo.domain.response.AuthResponse;
import com.procrianca.demo.domain.response.HttpStatusCode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.procrianca.demo.service.impl.CollaboratorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.java.com.procrianca.demo.domain.entity.Collaborator;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Collaborator API", description = "API for collaborator management")
public class CollaboratorController {
    
    private final CollaboratorService collaboratorService;

    @PostMapping("/collaborator")
    @ResponseStatus(HttpStatus.CREATED)
    public Collaborator saveCollaborator(@RequestBody @Valid Collaborator collaborator){
        log.info("Calling endpoint to save a collaborator in controller: " + log.getName());

        // Chame o método saveBeneficiary com o objeto passado no corpo da solicitação
        var collaboratorSaved = collaboratorService.saveCollaborator(collaborator);
        return collaborator;
    }
}
