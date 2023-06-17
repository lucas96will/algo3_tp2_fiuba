package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Arania extends Enemigo{
    public Arania(Posicion unaPosicion) {
        super(2, 2, 2, (int) (Math.random()* 10), unaPosicion);
        movimiento = new MovimientoTerrestre(2);
    }

    public Arania(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa) {
            super(unaVida, unDanio, unaVelocidad, unaEnergia, unaRecompensa);
            movimiento = new MovimientoTerrestre(2);
    }

    @Override
    protected void morir() {
        Jugador jugador = Jugador.getInstance();

        jugador.obtenerRecompensa(this);
        jugador.incrementarContadorAranias();
        this.estado = new EstadoEnemigoMuerto();
        Logger.getInstance().logExitoso(this + " murio.");
    }

    @Override
    public String toString() {

        return ("Araña en " +  posicion.toString());
    }
}
