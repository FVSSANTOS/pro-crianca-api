package com.procrianca.demo.domain.enums;


public enum Gender {
    QUALQUER(0),
    FEMININO(1),
    MASCULINO(2),
    NAO_ESPECIFICADO(3);

    private final int gender;

    private Gender(int gender) {
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }
}
