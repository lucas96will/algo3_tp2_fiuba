package edu.fiuba.algo3.modelo;

public class Casilla extends Pasarela {
    public Casilla(Pasarela pasarela_anterior){
        super(pasarela_anterior);
        pasarela_anterior.fijarSiguiente(this);
    }
}
