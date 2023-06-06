package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Partida.EstadoPartida;
import edu.fiuba.algo3.modelo.Partida.Partida;

import java.util.List;

public class JuegoControlador {
    private List<List<Enemigo>> enemigosPorTurno;
    private Mapa mapa;
    private Partida partida;
    private Jugador jugador;

    public void guardarDatos(List<List<Enemigo>> enemigosPorTurno, Mapa mapa) {
        this.enemigosPorTurno = enemigosPorTurno;
        this.mapa = mapa;
    }

    public void cargarJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void terminarTurno() {
        partida.terminarTurno();
        if(enemigosPorTurno.size() == 0) {
            return;
        }
        partida.anadirEnemigos(enemigosPorTurno.get(0));
        enemigosPorTurno.remove(0);
    }

    public void iniciar() {
        this.partida = new Partida();
        partida.crearPartida(jugador, mapa);
        partida.iniciar();
    }

    public EstadoPartida estado() {
        return partida.estado();
    }
}
