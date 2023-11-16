package com.procrianca.demo.domain.enums;

public enum Race {
    QUALQUER(0),
    PRETO(1),
    PARDO(2),
    AMARELO(3),
    BRANCO(4);

    private int race;

    private Race(int race) {
        this.race = race;
    }

    public int getRace() {
        return race;
    }
}
