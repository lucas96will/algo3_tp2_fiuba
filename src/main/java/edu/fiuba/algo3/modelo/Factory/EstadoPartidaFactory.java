package edu.fiuba.algo3.modelo.Factory;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Partida.EstadoPartida;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaGanada;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaPerdida;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaSigueJugando;

public class EstadoPartidaFactory {

     static public EstadoPartida obtenerEstadoPartida(Jugador jugador, Mapa mapa) {
        if(!jugador.muerto() && mapa.sinEnemigos()) {
            return new EstadoPartidaGanada(); // se gano
        }
        else if (!jugador.muerto()) {
            return new EstadoPartidaSigueJugando(); // todavia se sigue jugando
        }
        else {
            return new EstadoPartidaPerdida(); // se murio el jugador
        }
    }
}
