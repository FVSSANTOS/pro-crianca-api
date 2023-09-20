package com.procrianca.demo.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CredenciaisDTO {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
