package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;

public class Partida {
    private Jugador jugador;
    private Mapa mapa;

    public Partida(){}
    public void crearPartidaGenerica(Jugador jugador){
        this.jugador = jugador;
        mapa = new Mapa(7);
        mapa.crearMapaGenerico();
    }
    public void comenzar(){}
    public void terminarTurno() {
        mapa.defensasAtacar();
        mapa.actualizarEstadoDefensas();
        mapa.moverEnemigos();
    }
    public void construir(Defensa defensa){
        if(jugador.comprarDefensa(defensa)) {
            mapa.construir(defensa);
        }
    }

    public boolean construccionTerminadaEn(int posicionFila, int posicionColumna) {
        return mapa.construccionTerminadaEn(posicionFila, posicionColumna);
    }

    public boolean terminarPartida(){
        return jugador.muerto();
    }
    public boolean jugadorTieneTodaLaVidaYMaximosCreditos(){
        return jugador.estaIntacto();
    }

    public boolean hayConstruccionEn(int posicionFila, int posicionColumna) {
        return mapa.hayConstruccionEn(posicionFila, posicionColumna);
    }

    public void insertarEnemigo(Enemigo enemigo) {
        mapa.insertarEnemigo(enemigo);
    }
}
