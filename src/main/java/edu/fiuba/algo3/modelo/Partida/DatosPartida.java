package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Contador;
import edu.fiuba.algo3.modelo.Recurso;

public class DatosPartida {
    private static DatosPartida datosPartida;
    private int vidaJugador;
    private Recurso recursosJugador;
    private Contador contadorMuertes;

    private DatosPartida(){
        this.datosPartida = new DatosPartida();
    }

    static public DatosPartida getInstance(){
        if(DatosPartida.datosPartida == null) {
            return new DatosPartida();
        }
        return datosPartida;
    }

    public void actualizarEstado(int vida, Recurso recursos, Contador contadorMuertes) {
        this.vidaJugador = vida;
        this.recursosJugador = recursos;
        this.contadorMuertes = contadorMuertes;
    }

}
