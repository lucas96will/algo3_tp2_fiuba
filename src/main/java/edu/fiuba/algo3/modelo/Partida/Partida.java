package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private List<List<Enemigo>> enemigosPorTurno;
    private Jugador jugador;
    private Mapa mapa;
    private EstadoPartida estado;
    private ContadorTurnos turnos; //TODO: agregar comportamiento ??

    public Partida(Jugador jugador, Mapa mapa) {
        this.jugador = jugador;
        this.mapa = mapa;
        this.estado = new EstadoPartidaSigueJugando().siguienteEstado(mapa, jugador);
        turnos = ContadorTurnos.obtenerContador();
        enemigosPorTurno = new ArrayList<>();
    }

    public Partida(Jugador jugador, Mapa mapa, List<List<Enemigo>> enemigosPorTurno) {
        this(jugador, mapa);
        this.enemigosPorTurno = enemigosPorTurno;
    }


    public void terminarTurno() {
        if (enemigosPorTurno.size() != 0) {
            anadirEnemigos(enemigosPorTurno.get(0));
            enemigosPorTurno.remove(0);
        }
        estado.terminarTurno(mapa);
        turnos.incrementarTurno();
        actualizarEstado();

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
        this.estado = estado.siguienteEstado(mapa, jugador);
    }
}
