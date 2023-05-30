package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;

public class Partida {
    public static final int SE_GANO = 1;
    public static final int SE_SIGUE_JUGANDO = 2;
    public static final int SE_PERDIO = 0;
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
        int recompensa = 0;
        recompensa = mapa.defensasAtacar();
        jugador.sumarMonedas(recompensa);
        aplicarDanioEnemigos();
        mapa.actualizarEstadoDefensas();
        mapa.moverEnemigos();
    }
    public Boolean construir(Defensa defensa){
        boolean seConstruyo = false;
        if(jugador.comprarDefensa(defensa)) {
            seConstruyo = mapa.construir(defensa);
        }
        return seConstruyo;
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

    public void insertarEnemigo(Enemigo enemigo) {
        mapa.insertarEnemigo(enemigo);
    }

    public void aplicarDanioEnemigos() {
        jugador.recibirDanio(mapa.danioDeEnemigos());
    }

    public boolean jugadorTieneTantosCreditos(int creditosValor) {
        return creditosValor == jugador.valorCreditos();
    }

    public int estado() {
        if(!jugador.muerto() && mapa.sinEnemigos()) {
            return SE_GANO; // se gano
        }
        else if (!jugador.muerto()) {
            return SE_SIGUE_JUGANDO; // todavia se sigue jugando
        }
        else {
            return SE_PERDIO; // se murio el jugador
        }
    }
}
