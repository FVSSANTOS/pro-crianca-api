package com.procrianca.demo.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDto(
        @NotBlank @NotNull String login,
        @NotBlank @NotNull String password,
        @NotBlank @NotNull Integer id
) {

    public UserRecordDto(@NotBlank @NotNull String login, @NotBlank @NotNull String password, @NotBlank @NotNull Integer id) {
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public String getLoginPasswordCombo() {
        return login + ":" + password;
    }

}
