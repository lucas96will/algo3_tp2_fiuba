package edu.fiuba.algo3.modelo.Excepciones;

public class EnemigoNoIdentificadoException extends RuntimeException{
    public EnemigoNoIdentificadoException() {
        super("Error, enemigo no identificado");
    }
}
