package edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoComprarException;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Factory.EstadoPartidaFactory;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import java.util.List;

public class Partida {
    private Jugador jugador;
    private Mapa mapa;
    private EstadoPartida estado;

    private ContadorTurnos turnos;

    public Partida() {
    }

    public void crearPartida(Jugador jugador, Mapa mapa) {
        this.jugador = jugador;
        this.mapa = mapa;
        this.estado = EstadoPartidaFactory.obtenerEstadoPartida(jugador, mapa);
        turnos = ContadorTurnos.obtenerContador();
    }

    public void terminarTurno() {
        try {
            estado.terminarTurno(mapa);
            turnos.incrementarTurno();
            actualizarEstado();
        } catch (RuntimeException e) {

        }

    }

    public void construir(Defensa defensa, Posicion posicion) {
        try {
            estado.construir(defensa, posicion, jugador, mapa);
        } catch (DefensaNoSePudoComprarException a) {
            throw new RuntimeException("No se pudo comprar defensa");
        } catch (DefensaNoSePudoConstruir e) {
            jugador.obtenerReembolso(defensa);
            throw new RuntimeException("No se puede construir");
        }
    }

    public void insertarEnemigo(Enemigo enemigo) {
        try{
            estado.insertarEnemigo(enemigo, mapa);
            actualizarEstado();
        } catch (RuntimeException e){

        }
    }

    public EstadoPartida estado() {
        return estado;
    }

    public void anadirEnemigos(List<Enemigo> enemigos) {
        try {
            estado.insertarEnemigos(enemigos, mapa);
            actualizarEstado();
        } catch (RuntimeException e){

        }
    }

    public void actualizarEstado(){
        this.estado = EstadoPartidaFactory.obtenerEstadoPartida(jugador, mapa);
    }
}
