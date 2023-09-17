package com.procrianca.demo.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record CredenciaisDTO(
    @NotBlank String login,
    @NotBlank String password 
) {
    
}
