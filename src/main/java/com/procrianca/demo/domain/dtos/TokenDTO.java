package com.procrianca.demo.domain.dtos;

import com.procrianca.demo.domain.response.AuthResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class  TokenDTO extends AuthResponse {

    private String token;
    private Object user;

    public TokenDTO(String token, String message, int httpStatusCode) {
        super(message, httpStatusCode);
        this.token = token;
    }

    public TokenDTO(String token, String message, int httpStatusCode, Object user) {
        super(message, httpStatusCode);
        this.token = token;
        this.setUser(user);
    }

}
