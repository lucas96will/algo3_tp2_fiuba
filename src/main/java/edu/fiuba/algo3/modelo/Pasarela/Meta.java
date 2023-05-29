package edu.fiuba.algo3.modelo.Pasarela;

import edu.fiuba.algo3.modelo.Posicion;

public class Meta extends Pasarela {
    public Meta(Pasarela pasarela_anterior, Posicion unaPosicion) {
        super(pasarela_anterior,unaPosicion);
        pasarela_anterior.fijarSiguiente(this);
    }
}
