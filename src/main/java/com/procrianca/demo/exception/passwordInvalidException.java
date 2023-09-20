package com.procrianca.demo.exception;

public class SenhaInvalidaException  extends RuntimeException{
    public SenhaInvalidaException(){
        super("Senha inválida");
    }
}
