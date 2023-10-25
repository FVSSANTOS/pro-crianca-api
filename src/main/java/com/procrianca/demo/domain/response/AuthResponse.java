package com.procrianca.demo.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class AuthResponse {

    private String message;
    private int status;

    public AuthResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public AuthResponse(String message, int status, Object object) {
        this.message = message;
        this.status = status;
    }


}
