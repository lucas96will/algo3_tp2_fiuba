package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;

import java.util.List;
import java.util.Objects;

public class EstadoPartidaSigueJugando implements EstadoPartida{
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public void terminarTurno(Mapa mapa) {
        mapa.defensasAtacar();
        mapa.actualizarEstadoDefensas();
        mapa.moverEnemigos();
        ContadorTurnos.obtenerContador().incrementarTurno();
    }

    @Override
    public void construir(Defensa defensa, Posicion posicion, Jugador jugador, Mapa mapa) {
        jugador.comprarDefensa(defensa);
        mapa.construir(defensa, posicion);
    }

    @Override
    public void insertarEnemigo(Enemigo enemigo, Mapa mapa) {
        mapa.insertarEnemigo(enemigo);
    }

    @Override
    public void insertarEnemigos(List<Enemigo> enemigos, Mapa mapa) {
        enemigos.forEach(e -> insertarEnemigo(e, mapa));
    }

}
