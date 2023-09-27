package com.procrianca.demo.rest.controller;

import com.procrianca.demo.domain.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.procrianca.demo.domain.entity.Beneficiary;
import com.procrianca.demo.service.impl.BeneficaryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Beneficiarys API", description = "API for beneficiary management")
public class BeneficiaryController {
    
    private final BeneficaryService beneficiaryService;

    @Operation(summary = "Create a new beneficary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beneficiary created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiary.class)))
    })
    @PostMapping("/beneficiaries")
    @ResponseStatus(HttpStatus.CREATED)
    public Beneficiary saveBeneficiary(@RequestBody @Valid Beneficiary beneficiary){
        log.info(beneficiary.toString());
        log.info("Calling endpoint to save a beneficiary in controller: " + log.getName());

        // Chame o método saveBeneficiary com o objeto passado no corpo da solicitação
        var benefiarySaved = beneficiaryService.saveBeneficiary(beneficiary);

        return benefiarySaved;
    }


    @GetMapping("/beneficiaries")
    @ResponseStatus
    public ResponseEntity<List<Beneficiary>> getAllBeneficiaries() {
        log.info("Calling endpoint to list all users in controller: " + log.getName());

        List<Beneficiary> beneficiaries = this.beneficiaryService.listAllBeneficiaries();
        return ResponseEntity.status(HttpStatus.OK).body(beneficiaries);
    }

}
