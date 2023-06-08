package edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;

public class EstadoPartida {
    private boolean gano;
    private boolean perdio;
    private boolean sigueJugando;

    public EstadoPartida(Jugador jugador, Mapa mapa) {
        if(!jugador.muerto() && mapa.sinEnemigos()) {
            this.gano = true; // se gano
        }
        else if (!jugador.muerto()) {
            this.sigueJugando = true; // todavia se sigue jugando
        }
        else {
            this.perdio = true; // se murio el jugador
        }
    }

    public boolean sigueJugando(){
        return sigueJugando;
    }

    public boolean perdio(){
        if(perdio) {
            Logger.getInstance().logError("Jugador perdio la partida");
        }
        return perdio;
    }

    public boolean gano(){
        if(gano) {
            Logger.getInstance().logExitoso("Jugador gano la partida");
        }
        return gano;
    }
}