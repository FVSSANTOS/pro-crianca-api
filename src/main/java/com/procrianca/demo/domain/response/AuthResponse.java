package com.procrianca.demo.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String message;
    private int stauts;

    public AuthResponse(String message, int stauts) {
        this.message = message;
        this.stauts = stauts;
    }


}
