package edu.fiuba.algo3.modelo.Cargador;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Partida.EstadoPartida;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Partida.Partida;
import org.json.simple.parser.ParseException;

import java.util.List;

public class Juego {
    private final Cargador archivoLoader;
    private List<List<Enemigo>> enemigosPorTurno;
    private Mapa mapa;
    private Partida partida;
    private Jugador jugador;

    public Juego() {
        archivoLoader = new CargadorJson();
    }

    public boolean cargarConJson(String jsonEnemigos,String jsonMapa){
        try {
            archivoLoader.archivoEsCorrecto(jsonEnemigos, jsonMapa);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        List<List<Enemigo>> enemigosPorTurno = archivoLoader.procesarEnemigos(jsonEnemigos);
        Mapa mapa = archivoLoader.procesarMapa(jsonMapa);
        this.enemigosPorTurno = enemigosPorTurno;
        this.mapa = mapa;
        return true;
    }


    public void cargarJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void iniciar() {
        this.partida = new Partida();
        partida.crearPartida(jugador, mapa);
        partida.iniciar();
    }

    public void terminarTurno() {
        if(enemigosPorTurno.size() != 0) {
            partida.anadirEnemigos(enemigosPorTurno.get(0));
            enemigosPorTurno.remove(0);
        }
        partida.terminarTurno();
    }

    public EstadoPartida estado() {
        return partida.estado();
    }

    public void construir(Defensa torre, Posicion posicion) {
        partida.construir(torre, posicion);
    }
}

