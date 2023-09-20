package com.procrianca.demo.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


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
    @PostMapping("/beneficiary")
    @ResponseStatus(HttpStatus.CREATED)
    public Beneficiary saveBeneficiary(@RequestBody @Valid Beneficiary beneficiary){
        
        log.info("Calling endpoint to save a beneficiary in controller: " + log.getName());

        var benefiarySaved = beneficiaryService.saveBeneficiary(beneficiary);

        return benefiarySaved;
    }
}
