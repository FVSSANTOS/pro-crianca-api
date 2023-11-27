package com.procrianca.demo.domain.enums;

public enum MatriculationType {
    QUALQUER(0),
    OUTRO(1);

    private final int matriculationType;

    private MatriculationType(int matriculationType) {
        this.matriculationType = matriculationType;
    }

    public int getMatriculationType() {
        return this.matriculationType;
    }
}
