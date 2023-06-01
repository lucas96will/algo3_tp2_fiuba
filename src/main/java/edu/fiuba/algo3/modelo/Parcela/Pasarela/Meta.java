package edu.fiuba.algo3.modelo.Parcela.Pasarela;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Posicion;

public class Meta extends Pasarela {
    public Meta(Pasarela pasarela_anterior, Posicion unaPosicion) {
        super(pasarela_anterior,unaPosicion);
        pasarela_anterior.fijarSiguiente(this);
    }

    public int danioTotal() {
        int danio = 0;
        for(Enemigo enemigo : enemigosEncima) {
            danio = enemigo.sumarDanio(danio);
        }
        enemigosEncima.clear();
        return danio;
    }

}
