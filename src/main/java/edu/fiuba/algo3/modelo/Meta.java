package edu.fiuba.algo3.modelo;

public class Meta extends Pasarela{
    public Meta(Pasarela pasarela_anterior) {
        super(pasarela_anterior);
        pasarela_anterior.fijarSiguiente(this);
    }
}
