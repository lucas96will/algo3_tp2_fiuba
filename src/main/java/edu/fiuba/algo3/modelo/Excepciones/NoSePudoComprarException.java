package edu.fiuba.algo3.modelo.Excepciones;

public class NoSePudoComprarException extends RuntimeException{
    public NoSePudoComprarException(String errorMessage) {
        super(errorMessage);
    }
}
