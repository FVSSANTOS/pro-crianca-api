package com.procrianca.demo.domain.response;

import lombok.Getter;

@Getter
public enum HttpStatusCode {
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404);

    private final int value;

    HttpStatusCode(int value) {
        this.value = value;
    }

    public int getCode() {
        return value;
    }

    public String getName() {
        return name(); // Retorna o nome da constante enum (por exemplo, "OK", "CREATED").
    }

    public String getDescription() {
        return name().replaceAll("_", " "); // Retorna a descrição do código (por exemplo, "Not Found").
    }

    public static HttpStatusCode getByCode(int code) {
        for (HttpStatusCode status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status HTTP inválido: " + code);
    }
}
