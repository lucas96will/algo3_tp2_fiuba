package edu.fiuba.algo3.modelo.Partida;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoSePudoComprarException;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private List<List<Enemigo>> enemigosPorTurno;
    private Jugador jugador;
    private Mapa mapa;
    private EstadoPartida estado;
    private ContadorTurnos turnos; //TODO: agregar comportamiento ??

    public Partida(Jugador jugador, Mapa mapa) {
        this.jugador = jugador;
        this.mapa = mapa;
        this.estado = new EstadoPartidaSigueJugando().siguienteEstado(mapa, jugador);
        turnos = ContadorTurnos.obtenerContador();
        enemigosPorTurno = new ArrayList<>();
    }

    public Partida(Jugador jugador, Mapa mapa, List<List<Enemigo>> enemigosPorTurno) {
        this(jugador, mapa);
        this.enemigosPorTurno = enemigosPorTurno;
    }


    public void terminarTurno() {
        try {
            if (enemigosPorTurno.size() != 0) {
                anadirEnemigos(enemigosPorTurno.get(0));
                enemigosPorTurno.remove(0);
            }
            estado.terminarTurno(mapa);
            turnos.incrementarTurno();
            actualizarEstado();
        } catch (RuntimeException e) {
            //TODO: averiguar que hacer con esta excepcion
        }


    }

    public void construir(Defensa defensa, Posicion posicion) {
        try {
            estado.construir(defensa, posicion, jugador, mapa);
        } catch (NoSePudoComprarException a) {
            throw new RuntimeException("No se pudo comprar defensa");
        } catch (DefensaNoSePudoConstruir e) {
            jugador.obtenerReembolso(defensa);
            throw new RuntimeException("No se puede construir");
        } //TODO: No usar el try catch como un if
    }

    public void construir(TrampaDeArena trampa, Posicion posicion) {
        try {
            estado.construirTrampa(trampa, posicion, jugador, mapa);
        } catch (NoSePudoComprarException e) {
            throw new RuntimeException("No se pudo comprar trampa");
        } catch (RuntimeException e) {
            jugador.obtenerReembolso(trampa);
            throw new RuntimeException("No se puede construir");
        }//TODO: No usar el try catch como un if, codigo repetido
    }

    public void insertarEnemigo(Enemigo enemigo) {
        try {
            estado.insertarEnemigo(enemigo, mapa);
            actualizarEstado();
        } catch (RuntimeException e) {
            //TODO: averiguar que hacer con esta excepcion
        }
    }

    public EstadoPartida estado() {
        return estado;
    }

    public void anadirEnemigos(List<Enemigo> enemigos) {
        try {
            estado.insertarEnemigos(enemigos, mapa);
            actualizarEstado();
        } catch (RuntimeException e) {
        //TODO: averiguar que hacer con esta excepcion
        }
    }

    public void actualizarEstado() {
        this.estado = estado.siguienteEstado(mapa, jugador);
    }
}
