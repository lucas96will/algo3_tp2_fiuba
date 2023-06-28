package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;

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
    }

    @Override
    public void construir(Defensa defensa, Jugador jugador, Mapa mapa) {
        mapa.construir(defensa);
    }
    
    @Override
    public void construirTrampa(TrampaDeArena trampa, Posicion posicion, Jugador jugador, Mapa mapa) {
        jugador.comprar(trampa);
        mapa.construirTrampa(trampa, posicion);
    }

    @Override
    public void insertarEnemigo(Enemigo enemigo, Mapa mapa) {
        mapa.insertarEnemigo(enemigo);
    }

    @Override
    public void insertarEnemigos(List<Enemigo> enemigos, Mapa mapa) {
        enemigos.forEach(e -> insertarEnemigo(e, mapa));
    }

    @Override
    public EstadoPartida siguienteEstado(Mapa mapa, Jugador jugador, List<List<Enemigo>> enemigosPorTurno) {
        if(!jugador.muerto() && mapa.sinEnemigos() && enemigosPorTurno.isEmpty()) {
            return new EstadoPartidaGanada(); // se gano
        }
        else if (jugador.muerto()) {
            return new EstadoPartidaPerdida(); // muere el jugador
        }

        return new EstadoPartidaSigueJugando();

    }
}
