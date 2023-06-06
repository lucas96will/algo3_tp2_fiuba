package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Partida.DatosPartida;
import edu.fiuba.algo3.modelo.Posicion;

public class Hormiga extends Enemigo{

    public Hormiga(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa, Posicion unaPosicion) {
        super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa, unaPosicion);
    }

    @Override
    protected int morir() {
        muerto = true;
        DatosPartida datosPartida = DatosPartida.getInstance();
        datosPartida.incrementarContadorHormigas();
        return entregarRecompensa();

    }

    @Override
    protected int entregarRecompensa() {
        DatosPartida datosPartida = DatosPartida.getInstance();
        return datosPartida.obtenerMuertesHormigas() > 10 ?  2 : this.recompensa;
    }

}
