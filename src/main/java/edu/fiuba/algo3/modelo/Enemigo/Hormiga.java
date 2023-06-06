package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Contador;
import edu.fiuba.algo3.modelo.Posicion;

public class Hormiga extends Enemigo{

    public Hormiga(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa, Posicion unaPosicion) {
        super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa, unaPosicion);
    }

    @Override
    protected int morir() {
        muerto = true;
        Contador contador = Contador.getInstance();
        contador.incrementarContadorHormigas();
        return entregarRecompensa();

    }

    @Override
    protected int entregarRecompensa() {
        Contador contador = Contador.getInstance();
        return contador.getMuertesHormiga() > 10 ?  2 : this.recompensa;
    }

}
