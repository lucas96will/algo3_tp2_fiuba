package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Topo extends Enemigo {

    public Topo(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa, Posicion unaPosicion) {
        super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa, unaPosicion);
    }

    public Topo(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa) {
        super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa);
    }

    @Override
    protected void morir() {

    }

    @Override
    protected int entregarRecompensa() {
        return 0;
    }
}
