package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;

public class Partida {
    private Jugador jugador;
    private Mapa mapa;

    public Partida(){}
    public void crearPartidaGenerica(Jugador jugador){
        this.jugador = jugador;
        mapa = new Mapa();
        mapa.crearMapaGenerico();
        
    }
    public void crearPartida(Jugador jugador, Mapa mapa){
        this.jugador = jugador;
        this.mapa = mapa;
    }
    public void comenzar(){}
    public void terminarTurno() {
        int recompensa = 0;
        recompensa = mapa.defensasAtacar();
        jugador.sumarMonedas(recompensa);
        mapa.actualizarEstadoDefensas();
        mapa.moverEnemigos();
    }
    public void construir(Defensa defensa, Posicion posicion){
        if (jugador.comprarDefensa(defensa)) {
            mapa.construir(defensa, posicion);
        }
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



    public boolean jugadorTieneTantosCreditos(int creditosValor) {
        return creditosValor == jugador.valorCreditos();
    }

    public EstadoPartida estado() {
        return new EstadoPartida(this.jugador, this.mapa);
    }
    
}
