package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Defensa.Torre;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Jugador.Contador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Partida.EstadoPartida;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaGanada;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaPerdida;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaSigueJugando;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EstadoPartidaTest {
    @BeforeEach
    public void setUp() {
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, new Recurso(100), "Joaquin");
        jugador.actualizarContador(new Contador());
    }

    @Test
    public void test01EstadoPartidaGanadaConstruirNoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaGanada();
        Jugador jugador = Jugador.getInstance();
        Mapa mapa = Mapa.generico();
        Defensa defensa = new Torre(1,1,1, new EstadoDefensaIncompleto(2), "Torre");

        assertDoesNotThrow( ()-> estado.construir(defensa, new Posicion(2,2), jugador, mapa) );
    }

    @Test
    public void test02EstadoPartidaGanadaInsertarEnemigoNoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaGanada();
        Mapa mapa = Mapa.generico();
        Enemigo enemigo = new Hormiga(1,1,1,1,1);

        assertDoesNotThrow(() -> estado.insertarEnemigo(enemigo, mapa));
    }

    @Test
    public void test03EstadoPartidaGanadaTerminarTurnoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaGanada();
        Mapa mapa = Mapa.generico();

        assertThrows(RuntimeException.class, () -> estado.terminarTurno(mapa));
    }

    @Test
    public void test04EstadoPartidaSigueJugandoConstruirNoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaSigueJugando();
        Jugador jugador = Jugador.getInstance();
        Mapa mapa = Mapa.generico();
        Defensa defensa = new Torre(1,1,1, new EstadoDefensaIncompleto(2), "Torre");

        assertDoesNotThrow( ()-> estado.construir(defensa, new Posicion(2,2), jugador, mapa) );
    }

    @Test
    public void test05EstadoPartidaSigueJugandoInsertarEnemigoNoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaSigueJugando();
        Mapa mapa = Mapa.generico();
        Enemigo enemigo = new Hormiga(1,1,1,1,1);

        assertDoesNotThrow(() -> estado.insertarEnemigo(enemigo, mapa));
    }

    @Test
    public void test06EstadoPartidaSigueJugandoTerminarTurnoNoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaSigueJugando();
        Mapa mapa = Mapa.generico();

        assertDoesNotThrow(() -> estado.terminarTurno(mapa));
    }

    @Test
    public void test07EstadoPartidaPerdidaConstruirLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaPerdida();
        Jugador jugador = Jugador.getInstance();
        Mapa mapa = Mapa.generico();
        Defensa defensa = new Torre(1,1,1, new EstadoDefensaIncompleto(2), "Torre");

        assertThrows(RuntimeException.class, ()-> estado.construir(defensa, new Posicion(2,2), jugador, mapa) );
    }

    @Test
    public void test08EstadoPartidaSigueJugandoInsertarEnemigoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaPerdida();
        Mapa mapa = Mapa.generico();
        Enemigo enemigo = new Hormiga(1,1,1,1,1);

        assertThrows(RuntimeException.class, () -> estado.insertarEnemigo(enemigo, mapa));
    }

    @Test
    public void test09EstadoPartidaSigueJugandoTerminarTurnoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaPerdida();
        Mapa mapa = Mapa.generico();

        assertThrows(RuntimeException.class, ()-> estado.terminarTurno(mapa));
    }
}
