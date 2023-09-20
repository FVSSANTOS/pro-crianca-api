package com.procrianca.demo.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String message;
    private int staus;

    public AuthResponse(String message, int staus) {
        this.message = message;
        this.staus = staus;
    }


}
