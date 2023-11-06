package com.procrianca.demo.domain.enums;


public enum Gender {
    FEMININO('F'),
    MASCULINO('M'),
    NAO_ESPECIFICADO('N');

    private final char gender;

    private Gender(char gender){
        this.gender = gender;
    }

    public char getGender(){
        return gender;
    }
}
