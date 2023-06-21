package edu.fiuba.algo3.modelo.Excepciones;

public class ParcelaNoPuedeContenerTrampa extends RuntimeException {
    public ParcelaNoPuedeContenerTrampa(String message) {
        super(message);
    }
}
