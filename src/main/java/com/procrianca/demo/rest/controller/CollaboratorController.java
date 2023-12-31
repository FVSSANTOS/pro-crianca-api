package com.procrianca.demo.rest.controller;

import com.procrianca.demo.domain.entity.Beneficiary;
import com.procrianca.demo.domain.jpafilters.CollaboratorFilter;
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
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Collaborator API", description = "API for collaborator management")
public class CollaboratorController {


    private final CollaboratorService collaboratorService;

    @PostMapping("/collaborators")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse> saveCollaborator(@RequestBody @Valid Collaborator collaborator){
        log.info("Calling endpoint to save a collaborator in controller: " + log.getName());


        var collaboratorSaved = collaboratorService.saveCollaborator(collaborator);
        if (collaboratorSaved == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Colaborador não pôde ser inserido.", HttpStatusCode.BAD_REQUEST.getValue()) {});
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("Colaborador criado com sucesso.", HttpStatusCode.CREATED.getValue()) {});
        }
    }

    @Operation(summary = "Update a collaborator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Collaborator updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiary.class)))
    })
    @PutMapping("/collaborators/{id}")
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
    @DeleteMapping("/collaborators/{id}")
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
        return ResponseEntity.status(HttpStatus.OK).body(collaborators);
    }

    @Operation(summary = "Retrieve all collaborators in filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed Collaborators in filter", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Collaborator.class))),
    })
    @PostMapping("/collaborators/filter")
    public ResponseEntity<List<Collaborator>> getCollaboratorsFilter(@RequestBody CollaboratorFilter collaboratorFilter) {
        log.info("Calling endpoint to list all collaborators in controller using a filter");
        try {
            List<Collaborator> collaboratorList = collaboratorService.listCollaboratorsWithFilter(collaboratorFilter);
            return ResponseEntity.ok(collaboratorList);
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Get collaborator by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Collaborator found"),
            @ApiResponse(responseCode = "404", description = "Collaborator not found")
    })
    @GetMapping("/collaborators/{id}")
    public ResponseEntity<Collaborator> getCollaboratorById(@PathVariable(value = "id") @NotNull @Positive Integer id) {
        log.info("Calling endpoint to find one collaborator in controller: " + log.getName());
        try {
            var collaborator = this.collaboratorService.findById(id);

            return ResponseEntity.ok(collaborator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
