package com.procrianca.demo.rest.controller;

import com.procrianca.demo.domain.entity.Beneficiary;
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
import com.procrianca.demo.domain.entity.Collaborator;
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


        var collaboratorSaved = collaboratorService.saveCollaborator(collaborator);
        return collaboratorSaved;
    }

    @Operation(summary = "Update a collaborator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Collaborator updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiary.class)))
    })
    @PutMapping("/collaborator/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse> updateCollaborator(@PathVariable(value = "id") @NotNull @Positive Integer id,
                                                          @RequestBody @Valid Collaborator collaborator){
        log.info("Calling endpoint to update a collaborator in controller: " + log.getName());

        var collaboratorUpdated = this.collaboratorService.update(id, collaborator);
        if (collaboratorUpdated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse("Colaborador não encontrado.", HttpStatusCode.UNAUTHORIZED.getValue()) {});
        }
        return ResponseEntity.ok(new AuthResponse("Colaborador atualizado com sucesso", HttpStatusCode.OK.getValue()));
    }

    @Operation(summary = "Delete collaborator by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Collaborator deleted"),
            @ApiResponse(responseCode = "404", description = "Collaborator not found")
    })
    @DeleteMapping("/collaborator/{id}")
    public ResponseEntity<AuthResponse> deleteCollaborator(@PathVariable(value = "id") @NotNull @Positive Integer id) {
        log.info("Calling endpoint to delete one collaborator in controller: " + log.getName());
        var collaborator = this.collaboratorService.deleteCollaborator(id);
        if (!collaborator) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse("Colaborador não encontrado. Não foi possivel exlcuir.", HttpStatusCode.NOT_FOUND.getValue()) {});
        }
        return ResponseEntity.ok(new AuthResponse("Colaborador excluído com sucesso", HttpStatusCode.OK.getValue()));
    }

    @Operation(summary = "Retrieve all collaborators")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed Collaborators"),
            @ApiResponse(responseCode = "404", description = "Collaborators not found")
    })
    @GetMapping("/collaborators")
    public ResponseEntity<List<Collaborator>> getAllCollaborators() {
        log.info("Calling endpoint to list all collaboratorss in controller: " + log.getName());
        List<Collaborator> collaborators = this.collaboratorService.listAllCollaborators();
        if(collaborators.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((List<Collaborator>) new AuthResponse("Não existe nenhum colaborador.", HttpStatusCode.NOT_FOUND.getValue()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(collaborators);
    }
}
