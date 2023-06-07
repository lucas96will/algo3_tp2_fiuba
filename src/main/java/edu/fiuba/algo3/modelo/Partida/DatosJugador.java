package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Contador;
import edu.fiuba.algo3.modelo.Recurso;

public class DatosJugador {
    private static DatosJugador datosJugador;
    private int vidaJugador;
    private Recurso recursosJugador;
    private Contador contadorMuertes;

    private DatosJugador(){
        vidaJugador = 100;
        recursosJugador = new Recurso(0);
        contadorMuertes = new Contador();
    }

    static public DatosJugador getInstance(){
        if(datosJugador == null) {
            datosJugador = new DatosJugador();
        }
        return datosJugador;
    }

    public void actualizarEstado(int vida, Recurso recursos, Contador contadorMuertes) {
        this.vidaJugador = vida;
        this.recursosJugador = recursos;
        this.contadorMuertes = contadorMuertes;
    }

    public void incrementarContadorAranias(){
        contadorMuertes.incrementarContadorAranias();
    }

    public void incrementarContadorHormigas(){
        contadorMuertes.incrementarContadorHormigas();
    }

    public int obtenerMuertesHormigas() {
        return contadorMuertes.obtenerMuertesHormigas();
    }

    public int obtenerVidaJugador() {
        return vidaJugador;
    }

    public void reducirVidaJugador(int danio) {
        this.vidaJugador -= danio;
    }

    public int obtenerMuertesArania() {
        return contadorMuertes.obtenerMuertesAranias();
    }
}