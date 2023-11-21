package com.procrianca.demo.domain.enums;

public enum Shift {
    QUALQUER(0),
    MANHA(1),
    TARDE(2),
    NOITE(3);

    private int shift;

    private Shift(int shift) {
        this.shift = shift;
    }

    public int getShift() {
        return shift;
    }
}
