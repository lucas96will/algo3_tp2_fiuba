package edu.fiuba.algo3.modelo;

public class CondicionPartida {
    public static final int SE_GANO = 1;
    public static final int SE_SIGUE_JUGANDO = 2;
    public static final int SE_PERDIO = 0;
    public int estado;

    private void actualizar(Jugador jugador, Mapa mapa) {
        if(!jugador.muerto() && mapa.sinEnemigos()) {
            this.estado = SE_GANO; // se gano
        }
        else if (!jugador.muerto()) {
            this.estado = SE_SIGUE_JUGANDO; // todavia se sigue jugando
        }
        else {
            this.estado = SE_PERDIO; // se murio el jugador
        }
    }

    public boolean sigueJugando() {
        return estado == SE_SIGUE_JUGANDO;
    }
    public boolean gano() {
        return estado == SE_GANO;
    }
    public boolean perdio() {
        return estado == SE_PERDIO;
    }

    public CondicionPartida estado(Jugador jugador, Mapa mapa) {
        CondicionPartida nuevaCondicion = new CondicionPartida();
        nuevaCondicion.actualizar(jugador, mapa);
        return nuevaCondicion;
    }
}
