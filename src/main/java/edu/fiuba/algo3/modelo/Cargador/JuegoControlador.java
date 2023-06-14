package edu.fiuba.algo3.modelo.Cargador;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Partida.EstadoPartida;
import edu.fiuba.algo3.modelo.Partida.Partida;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

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
        if(enemigosPorTurno.size() != 0) {
            partida.anadirEnemigos(enemigosPorTurno.get(0));
            enemigosPorTurno.remove(0);
        }
        partida.terminarTurno();
    }

    public void iniciar() {
        this.partida = new Partida();
        partida.crearPartida(jugador, mapa);
        partida.iniciar();
    }

    public EstadoPartida estado() {
        return partida.estado();
    }

    public void construir(Defensa torre, Posicion posicion) {
        partida.construir(torre, posicion);
    }
}
