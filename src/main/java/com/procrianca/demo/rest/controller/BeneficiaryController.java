package com.procrianca.demo.rest.controller;

import com.procrianca.demo.domain.entity.BeneficiaryMedic;
import com.procrianca.demo.domain.entity.Image;
import com.procrianca.demo.domain.jpafilters.BeneficiaryFilter;
import com.procrianca.demo.domain.mapper.ImageMapper;
import com.procrianca.demo.domain.response.AuthResponse;
import com.procrianca.demo.domain.response.HttpStatusCode;
import com.procrianca.demo.service.impl.ImageService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Beneficiaries API", description = "API for beneficiary management")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;
    private final ImageService imageService;
    private final ImageMapper mapper;

    // Formato para receber upload de arquivos: mult-part/formdata
    @PostMapping("/images")
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name
    ) throws IOException {

        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());
        // log.info("Content Type: {}", file.getContentType()); // image/png

        var image = mapper.mapToImage(file, name);
        var imageSaved = this.imageService.save(image);

        // build url to return
        URI imageURI = buildImageURL(imageSaved);

        return ResponseEntity.created(imageURI).build();
    }

    private URI buildImageURL(Image image) {
        String photoPath = "/" + image.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(photoPath)
                .build().toUri();
    }

    @Operation(summary = "Create a new beneficary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beneficiary created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Beneficiary.class)))
    })
    @PostMapping("/beneficiaries")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse> saveBeneficiary(@RequestBody @Valid Beneficiary beneficiary) {
        log.info("Calling endpoint to save a beneficiary in controller: " + log.getName());
        log.info(beneficiary.toString());

        var beneficiarySaved = beneficiaryService.saveBeneficiary(beneficiary);
        if (beneficiarySaved == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Beneficiário não pôde ser inserido.", HttpStatusCode.BAD_REQUEST.getValue()) {
            });
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("Beneficiário criado com sucesso.", HttpStatusCode.CREATED.getValue()) {
            });
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
                                                          @RequestBody @Valid Beneficiary beneficiary) {
        log.info("Calling endpoint to update a beneficiary in controller: " + log.getName());

        var beneficiaryUpdated = this.beneficiaryService.update(id, beneficiary);
        if (beneficiaryUpdated == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Beneficiário não pôde ser atualizado..", HttpStatusCode.BAD_REQUEST.getValue()) {
            });
        }
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse("Beneficiário atualizado com sucesso.", HttpStatusCode.CREATED.getValue()) {
        });
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse("Beneficiário não encontrado. Não foi possivel exlcuir.", HttpStatusCode.NOT_FOUND.getValue()) {
            });
        }
        return ResponseEntity.ok(new AuthResponse("Beneficiário excluído com sucesso", HttpStatusCode.OK.getValue()));
    }

    @Operation(summary = "Retrieve all beneficiaries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed Beneficiaries", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Beneficiary.class))),
    })
    @GetMapping("/beneficiaries")
    public ResponseEntity<List<Beneficiary>> getAllBeneficiaries() {
        log.info("Calling endpoint to list all beneficiaries in controller: " + log.getName());
        List<Beneficiary> beneficiaries = this.beneficiaryService.listAllBeneficiaries();
        return ResponseEntity.status(HttpStatus.OK).body(beneficiaries);
    }

    @Operation(summary = "Retrieve all beneficiaries in filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed Beneficiaries in filter", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Beneficiary.class))),
    })
    @PostMapping("/beneficiaries/filter")
    public ResponseEntity<List<Beneficiary>> getBeneficiariesFilter(@RequestBody BeneficiaryFilter beneficiaryFilter) {
        log.info("Calling endpoint to list all beneficiaries in controller using a filter");
        List<Beneficiary> beneficiaryList = beneficiaryService.listBeneficiariesWithFilter(beneficiaryFilter);
        return ResponseEntity.ok(beneficiaryList);
    }

    @Operation(summary = "Get beneficiary by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiary found"),
            @ApiResponse(responseCode = "404", description = "Beneficiary not found")
    })
    @GetMapping("/beneficiaries/{id}")
    public ResponseEntity<Beneficiary> getBeneficiaryById(@PathVariable(value = "id") @NotNull @Positive Integer id) {
        log.info("Calling endpoint to find one beneficiary in controller: " + log.getName());
        try {
            var beneficiary = this.beneficiaryService.findById(id);

            return ResponseEntity.ok(beneficiary);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


}
