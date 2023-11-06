package com.procrianca.demo.domain.enums;

public enum Status {
    ATIVO(1),
    DESATIVADO(2);

    private int status;

    private Status(int status){
        this.status = status;
    }

    public int getStatus(){
        return status;
    }
}
