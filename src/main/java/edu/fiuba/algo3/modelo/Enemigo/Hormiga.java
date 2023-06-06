package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Partida.DatosJugador;
import edu.fiuba.algo3.modelo.Posicion;

public class Hormiga extends Enemigo{

    public Hormiga(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa, Posicion unaPosicion) {
        super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa, unaPosicion);
    }

    public Hormiga(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa) {
                super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa);
    }

    @Override
    protected int morir() {
        muerto = true;
        DatosJugador datosJugador = DatosJugador.getInstance();
        datosJugador.incrementarContadorHormigas();
        return entregarRecompensa();

    }

    @Override
    protected int entregarRecompensa() {
        DatosJugador datosJugador = DatosJugador.getInstance();
        return datosJugador.obtenerMuertesHormigas() > 10 ?  2 : this.recompensa;
    }

}
