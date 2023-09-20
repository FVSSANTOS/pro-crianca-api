package com.procrianca.demo.exception;

public class passwordInvalidException extends RuntimeException{
    public passwordInvalidException(){
        super("Senha inválida");
    }
}
