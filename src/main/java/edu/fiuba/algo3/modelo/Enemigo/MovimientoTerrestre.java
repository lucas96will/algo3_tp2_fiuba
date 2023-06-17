package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public class MovimientoTerrestre implements Movimiento {

    private final int velocidad;

    public MovimientoTerrestre(int unaVelocidad){
        velocidad = unaVelocidad;
    }

    @Override
    public void moverse(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual, Posicion posAnterior) {
        boolean seMovio;
        int k;
        Parcela unaParcela;

        for (int i = 0; i < velocidad; i++) {
            k = 0;
            seMovio = false;
            while (k < parcelas.size() && !seMovio) {
                unaParcela = parcelas.get(k);
                seMovio = unaParcela.moveElEnemigo(enemigo, posActual);
                k++;
            }
        }
    }
}

