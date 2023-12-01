package com.procrianca.demo.domain.enums;

public enum Relationship {
    QUALQUER(0),
    MAE(1),
    PAI(2),
    OUTRO(3);

    private final int relationship;

    private Relationship(int relationship) {
        this.relationship = relationship;
    }

    public int getRelationship() {
        return this.relationship;
    }
}
