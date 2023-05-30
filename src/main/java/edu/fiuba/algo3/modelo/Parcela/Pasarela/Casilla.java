package edu.fiuba.algo3.modelo.Parcela.Pasarela;

import edu.fiuba.algo3.modelo.Posicion;

public class Casilla extends Pasarela {
    public Casilla(Pasarela pasarela_anterior, Posicion unaPosicion){
        super(pasarela_anterior, unaPosicion);
        pasarela_anterior.fijarSiguiente(this);
    }
}
