package edu.fiuba.algo3.modelo.Cargador;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;
import edu.fiuba.algo3.modelo.Partida.EstadoPartida;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Partida.Partida;

import java.util.List;

public class Juego {
    private final Cargador archivoLoader;
    private List<List<Enemigo>> enemigosPorTurno;
    private Mapa mapa;
    private Partida partida;
    private Jugador jugador;

    private static Juego juego = new Juego();

    public Juego() {
        archivoLoader = new CargadorJson();
    }

    static public Juego getInstance() {
        if(juego==null){
            juego = new Juego();
        }
        return juego;
    }

    public void cargarEnemigosYMapa(String jsonEnemigos, String jsonMapa){
        try {
            archivoLoader.archivoEsCorrecto(jsonEnemigos, jsonMapa);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        this.enemigosPorTurno = archivoLoader.procesarEnemigos(jsonEnemigos);
        this.mapa = archivoLoader.procesarMapa(jsonMapa);
    }


    public void cargarJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void iniciar() {
        partida = new Partida(jugador, mapa, enemigosPorTurno);
    }

    public void terminarTurno() {
        partida.terminarTurno();
    }

    public EstadoPartida estado() {
        return partida.estado();
    }

    public void construir(Defensa torre) {
        partida.construir(torre);
    }

    public void construir(TrampaDeArena unaTrampa, Posicion unaPosicion){
        partida.construir(unaTrampa, unaPosicion);
    }

    public List<Enemigo> obtenerEnemigos () {return mapa.obtenerEnemigos();}
    public List<Parcela> obtenerParcelas () {return mapa.obtenerParcelas();}
}

