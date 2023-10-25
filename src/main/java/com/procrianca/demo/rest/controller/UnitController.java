package com.procrianca.demo.rest.controller;

import com.procrianca.demo.domain.entity.Beneficiary;
import com.procrianca.demo.domain.entity.Unit;
import com.procrianca.demo.domain.response.AuthResponse;
import com.procrianca.demo.domain.response.HttpStatusCode;
import com.procrianca.demo.service.impl.UnitService;
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
@Tag(name = "Unit API", description = "API for unit management")
public class UnitController {


    private final UnitService unitService;

    @PostMapping("/units")
    @ResponseStatus(HttpStatus.CREATED)
    public Unit saveUnit(@RequestBody @Valid Unit unit){
        log.info("Calling endpoint to save a unit in controller: " + log.getName());


        var unitSaved = unitService.saveUnit(unit);
        return unitSaved;
    }

    @Operation(summary = "Update a unit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unit updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiary.class)))
    })
    @PutMapping("/units/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse> updateUnit(@PathVariable(value = "id") @NotNull @Positive Integer id,
                                                           @RequestBody @Valid Unit unit){
        log.info("Calling endpoint to update a unit in controller: " + log.getName());

        var unitUpdated = this.unitService.update(id, unit);
        if (unitUpdated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse("Unidade não encontrada.", HttpStatusCode.UNAUTHORIZED.getValue()) {});
        }
        return ResponseEntity.ok(new AuthResponse("Unidade atualizada com sucesso", HttpStatusCode.OK.getValue()));
    }

    @Operation(summary = "Delete unit by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unit deleted"),
            @ApiResponse(responseCode = "404", description = "Unit not found")
    })
    @DeleteMapping("/units/{id}")
    public ResponseEntity<AuthResponse> deleteUnit(@PathVariable(value = "id") @NotNull @Positive Integer id) {
        log.info("Calling endpoint to delete one unit in controller: " + log.getName());
        var unit = this.unitService.deleteUnit(id);
        if (!unit) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse("Unidade não encontrada. Não foi possivel exlcuir.", HttpStatusCode.NOT_FOUND.getValue()) {});
        }
        return ResponseEntity.ok(new AuthResponse("Unidade excluída com sucesso", HttpStatusCode.OK.getValue()));
    }

    @Operation(summary = "Retrieve all units")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed Units"),
            @ApiResponse(responseCode = "404", description = "Units not found")
    })
    @GetMapping("/units")
    public ResponseEntity<List<Unit>> getAllUnits() {
        log.info("Calling endpoint to list all units in controller: " + log.getName());
        List<Unit> units = this.unitService.listAllUnits();
        if(units.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((List<Unit>) new AuthResponse("Não existe nenhuma unidade .", HttpStatusCode.NOT_FOUND.getValue()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(units);
    }
}
