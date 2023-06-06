package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Contador;
import edu.fiuba.algo3.modelo.Posicion;

public class Arania extends Enemigo{
    public Arania(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa,Posicion unaPosicion) {
        super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa, unaPosicion);
    }

    @Override
    protected int morir() {
        muerto = true;
        Contador contador = Contador.getInstance();
        contador.incrementarContadorArania();
        return entregarRecompensa();
    }

    @Override
    protected int entregarRecompensa() {
        return ( (int) Math.floor(Math.random()) * (10) + 1);
    }
}
