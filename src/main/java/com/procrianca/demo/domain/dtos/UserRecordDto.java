package com.procrianca.demo.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserRecordDto(
        @NotBlank @NotNull String login, @NotBlank @NotNull String password, @NotNull LocalDateTime createdAt, @NotNull LocalDateTime updatedAt
) {
}
