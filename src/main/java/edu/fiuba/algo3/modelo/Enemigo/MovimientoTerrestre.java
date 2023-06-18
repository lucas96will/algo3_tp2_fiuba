package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public class MovimientoTerrestre implements Movimiento {

    public MovimientoTerrestre(){

    }

    @Override
    public void moverse(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {
        boolean seMovio = false;
        int k = 0;
        Parcela unaParcela;
        while (k < parcelas.size() && !seMovio) {
            unaParcela = parcelas.get(k);
            seMovio = unaParcela.moveElEnemigo(enemigo, posActual);
            k++;
        }
    }
}

