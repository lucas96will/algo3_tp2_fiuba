package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Contador;
import edu.fiuba.algo3.modelo.Recurso;

public class DatosPartida {
    private static DatosPartida datosPartida;
    private int vidaJugador;
    private Recurso recursosJugador;
    private Contador contadorMuertes;

    private DatosPartida(){

    }

    static public DatosPartida getInstance(){
        if(datosPartida == null) {
            datosPartida = new DatosPartida();
        }
        return datosPartida;
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
}
