package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Arania extends Enemigo{
    public Arania(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa, Posicion unaPosicion) {
        super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa, unaPosicion);
    }

    public Arania(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa) {
            super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa);
    }

    @Override
    protected void morir() {
        Jugador jugador = Jugador.getInstance();

        jugador.obtenerRecompensa(this);
        jugador.incrementarContadorAranias();
        this.estado = new Muerto();
        Logger.getInstance().logExitoso(this + " murio.");
    }

    @Override
    public String toString() {

        return ("Araña en " +  posicion.toString());
    }
}
