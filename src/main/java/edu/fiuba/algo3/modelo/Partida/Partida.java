package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;

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

    public EstadoPartida estado() {
        return new EstadoPartida(this.jugador, this.mapa);
    }
}