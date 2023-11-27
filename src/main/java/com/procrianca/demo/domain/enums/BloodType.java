package com.procrianca.demo.domain.enums;

public enum BloodType {
    QUALQUER(0),
    A_POSITIVO(1),
    A_NEGATIVO(2),
    B_POSITIVO(3),
    B_NEGATIVO(4),
    AB_POSITIVO(5),
    AB_NEGATIVO(6),
    O_POSITIVO(7),
    O_NEGATIVO(8);

    private final int bloodType;

    private BloodType(int bloodType) {
        this.bloodType = bloodType;
    }

    public int getBloodType() {
        return bloodType;
    }
}
