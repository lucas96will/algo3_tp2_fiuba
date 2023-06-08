package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Partida.EstadoPartida;
import edu.fiuba.algo3.modelo.Posicion;

import java.util.List;

public class JuegoFacade {
    private final CargadorJson archivoLoader;
    private final JuegoControlador juegoControlador;

    public JuegoFacade() {
        archivoLoader = new CargadorJson();
        juegoControlador = new JuegoControlador();
    }

    public boolean cargarConJson(String jsonEnemigos,String jsonMapa){
        if(!archivoLoader.archivoEsCorrecto(jsonEnemigos, jsonMapa)){
            return false;
        }
        List<List<Enemigo>> enemigosPorTurno = archivoLoader.procesarEnemigos(jsonEnemigos);
        Mapa mapa = archivoLoader.procesarMapa(jsonMapa);
        juegoControlador.guardarDatos(enemigosPorTurno, mapa);
        return true;
    }


    public void cargarJugador(Jugador jugador) {
        juegoControlador.cargarJugador(jugador);
    }

    public void iniciar() {
        juegoControlador.iniciar();
    }

    public void terminarTurno() {
        juegoControlador.terminarTurno();
    }

    public EstadoPartida estado() {
        return juegoControlador.estado();
    }

    public void construir(Defensa torre, Posicion posicion) {
        juegoControlador.construir(torre, posicion);
    }
}

