package edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Factory.EstadoPartidaFactory;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import java.util.List;

public class Partida {
    private Jugador jugador;
    private Mapa mapa;
    private EstadoPartida estadoPartida;

    public Partida(){}
    public void crearPartidaGenerica(Jugador jugador){
        this.jugador = jugador;
        mapa = new Mapa();
        mapa.crearMapaGenerico();
        this.estadoPartida = new EstadoPartidaSigueJugando();
        
    }
    public void crearPartida(Jugador jugador, Mapa mapa){
        this.jugador = jugador;
        this.mapa = mapa;
    }
    public void iniciar(){
        this.mapa.iniciarLargada();
    }
    public void terminarTurno() {
        int recompensa = 0;
        recompensa = mapa.defensasAtacar();
        jugador.sumarMonedas(recompensa);
        mapa.actualizarEstadoDefensas();
        mapa.moverEnemigos();
    }
    public void construir(Defensa defensa, Posicion posicion){
        if (jugador.comprarDefensa(defensa)) {
            try {
                mapa.construir(defensa, posicion);
            } catch (Exception e) {
                jugador.obtenerReembolso(defensa);
                throw new RuntimeException("No se puede construir");
            }
        }
    }

    public void insertarEnemigo(Enemigo enemigo) {
        mapa.insertarEnemigo(enemigo);
    }

    public EstadoPartida estado() {
        return EstadoPartidaFactory.obtenerEstadoPartida(this.jugador, this.mapa);
    }

    public void anadirEnemigos(List<Enemigo> enemigos) {
        enemigos.forEach(e -> mapa.insertarEnemigo(e));
    }
}
