package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoSePudoComprarException;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Factory.EstadoPartidaFactory;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;

import java.util.List;

public class Partida {
    private Jugador jugador;
    private Mapa mapa;
    private EstadoPartida estado;

    private ContadorTurnos turnos;

    public Partida() {
    }

    public Partida(Jugador jugador, Mapa mapa) {
        this.jugador = jugador;
        this.mapa = mapa;
        turnos = ContadorTurnos.obtenerContador();
        this.estado = (jugador.obtenerVidaJugador() > 0) ? new EstadoPartidaSigueJugando() : new EstadoPartidaPerdida();
    }

    public void terminarTurno() {
        estado.terminarTurno(mapa);
        turnos.incrementarTurno();
    }

    public void construir(Defensa defensa, Posicion posicion) {
        estado.construir(defensa, posicion, jugador, mapa);
    }

    public void construir(TrampaDeArena trampa, Posicion posicion) {
        estado.construirTrampa(trampa, posicion, jugador, mapa);
    }

    public void insertarEnemigo(Enemigo enemigo) {
        estado.insertarEnemigo(enemigo, mapa);
        actualizarEstado();

    }

    public EstadoPartida estado() {
        return estado;
    }

    public void anadirEnemigos(List<Enemigo> enemigos) {
        estado.insertarEnemigos(enemigos, mapa);
        actualizarEstado();
    }

    public void actualizarEstado() {
        this.estado = EstadoPartidaFactory.obtenerEstadoPartida(jugador, mapa);
    }
}
