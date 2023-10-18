package com.procrianca.demo.rest.controller;

import com.procrianca.demo.domain.entity.User;
import com.procrianca.demo.domain.response.AuthResponse;
import com.procrianca.demo.domain.response.HttpStatusCode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.procrianca.demo.domain.entity.Beneficiary;
import com.procrianca.demo.service.impl.BeneficiaryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Beneficiaries API", description = "API for beneficiary management")
public class BeneficiaryController {
    
    private final BeneficiaryService beneficiaryService;

    @Operation(summary = "Create a new beneficary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beneficiary created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiary.class)))
    })
    @PostMapping("/beneficiaries")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse> saveBeneficiary(@RequestBody @Valid Beneficiary beneficiary){
        log.info("Calling endpoint to save a beneficiary in controller: " + log.getName());

        var beneficiarySaved = beneficiaryService.saveBeneficiary(beneficiary);
        if (beneficiarySaved == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Beneficiário não pôde ser inserido.", HttpStatusCode.BAD_REQUEST.getValue()) {});
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("Beneficiário criado com sucesso.", HttpStatusCode.CREATED.getValue()) {});
        }
    }

    @Operation(summary = "Update a beneficary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiary updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiary.class)))
    })
    @PutMapping("/beneficiaries/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse> updateBeneficiary(@PathVariable(value = "id") @NotNull @Positive Integer id,
                                                          @RequestBody @Valid Beneficiary beneficiary){
        log.info("Calling endpoint to update a beneficiary in controller: " + log.getName());

        var beneficiaryUpdated = this.beneficiaryService.update(id, beneficiary);
        if (beneficiaryUpdated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse("Beneficiário não pôde ser atualizado.", HttpStatusCode.UNAUTHORIZED.getValue()) {});
        }
        return ResponseEntity.ok(new AuthResponse("Beneficiário atualizado com sucesso", HttpStatusCode.OK.getValue()));
    }

    @Operation(summary = "Delete beneficiary by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiary deleted"),
            @ApiResponse(responseCode = "404", description = "Beneficiary not found")
    })
        @DeleteMapping("/beneficiaries/{id}")
    public ResponseEntity<AuthResponse> deleteBeneficiary(@PathVariable(value = "id") @NotNull @Positive Integer id) {
        log.info("Calling endpoint to delete one beneficiary in controller: " + log.getName());
        var beneficiary = this.beneficiaryService.deleteBeneficiary(id);
        if (!beneficiary) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse("Beneficiário não encontrado. Não foi possivel exlcuir.", HttpStatusCode.NOT_FOUND.getValue()) {});
        }
        return ResponseEntity.ok(new AuthResponse("Beneficiário excluído com sucesso", HttpStatusCode.OK.getValue()));
    }

    @Operation(summary = "Retrieve all beneficiaries")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listed Beneficiaries"),
        @ApiResponse(responseCode = "404", description = "Beneficiaries not found")
    })
    @GetMapping("/beneficiaries")
    public ResponseEntity<List<Beneficiary>> getAllBeneficiaries() {
        log.info("Calling endpoint to list all beneficiaries in controller: " + log.getName());
        List<Beneficiary> beneficiaries = this.beneficiaryService.listAllBeneficiaries();
        if(beneficiaries.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((List<Beneficiary>) new AuthResponse("Não existe nenhum beneficiário.", HttpStatusCode.NOT_FOUND.getValue()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(beneficiaries);
    }

}
