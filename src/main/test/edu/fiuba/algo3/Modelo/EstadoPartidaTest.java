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
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Partida.EstadoPartida;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaGanada;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaPerdida;
import edu.fiuba.algo3.modelo.Partida.EstadoPartidaSigueJugando;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
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

    public Mapa obtenerMapaGenerico() {
        Mapa mapa = new Mapa(8);

        mapa.agregarParcelaEnPosicion(new Pasarela(new Largada()), new Posicion(1,1));

        for(int i = 2; i < 7; i++){
            mapa.agregarParcelaEnPosicion(new Pasarela(new Casilla()), new Posicion(1,i));
        }
        mapa.agregarParcelaEnPosicion(new Pasarela(new Meta()), new Posicion(1,7));

        for(int j = 2; j < 8; j++) {
            for(int k = 1; k < 8; k++) {
                mapa.agregarParcelaEnPosicion(new Tierra(), new Posicion(j, k));
            }
        }
        for(int h = 1; h < 8; h++) {
            mapa.agregarParcelaEnPosicion(new Rocoso(), new Posicion(7, h));
        }


        return mapa;
    }

    @Test
    public void test01EstadoPartidaGanadaConstruirNoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaGanada();
        Jugador jugador = Jugador.getInstance();
        Mapa mapa = obtenerMapaGenerico();
        Defensa defensa = new Torre(1,1,1, new EstadoDefensaIncompleto(2), "Torre");

        assertDoesNotThrow( ()-> estado.construir(defensa, new Posicion(2,2), jugador, mapa) );
    }

    @Test
    public void test02EstadoPartidaGanadaInsertarEnemigoNoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaGanada();
        Mapa mapa = obtenerMapaGenerico();
        Enemigo enemigo = new Hormiga(1,1,1,1,1);

        assertDoesNotThrow(() -> estado.insertarEnemigo(enemigo, mapa));
    }

    @Test
    public void test03EstadoPartidaGanadaTerminarTurnoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaGanada();
        Mapa mapa = obtenerMapaGenerico();

        assertThrows(RuntimeException.class, () -> estado.terminarTurno(mapa));
    }

    @Test
    public void test04EstadoPartidaSigueJugandoConstruirNoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaSigueJugando();
        Jugador jugador = Jugador.getInstance();
        Mapa mapa = obtenerMapaGenerico();
        Defensa defensa = new Torre(1,1,1, new EstadoDefensaIncompleto(2), "Torre");

        assertDoesNotThrow( ()-> estado.construir(defensa, new Posicion(2,2), jugador, mapa) );
    }

    @Test
    public void test05EstadoPartidaSigueJugandoInsertarEnemigoNoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaSigueJugando();
        Mapa mapa = obtenerMapaGenerico();
        Enemigo enemigo = new Hormiga(1,1,1,1,1);

        assertDoesNotThrow(() -> estado.insertarEnemigo(enemigo, mapa));
    }

    @Test
    public void test06EstadoPartidaSigueJugandoTerminarTurnoNoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaSigueJugando();
        Mapa mapa = obtenerMapaGenerico();

        assertDoesNotThrow(() -> estado.terminarTurno(mapa));
    }

    @Test
    public void test07EstadoPartidaPerdidaConstruirLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaPerdida();
        Jugador jugador = Jugador.getInstance();
        Mapa mapa = obtenerMapaGenerico();
        Defensa defensa = new Torre(1,1,1, new EstadoDefensaIncompleto(2), "Torre");

        assertThrows(RuntimeException.class, ()-> estado.construir(defensa, new Posicion(2,2), jugador, mapa) );
    }

    @Test
    public void test08EstadoPartidaSigueJugandoInsertarEnemigoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaPerdida();
        Mapa mapa = obtenerMapaGenerico();
        Enemigo enemigo = new Hormiga(1,1,1,1,1);

        assertThrows(RuntimeException.class, () -> estado.insertarEnemigo(enemigo, mapa));
    }

    @Test
    public void test09EstadoPartidaSigueJugandoTerminarTurnoLanzaExcepcion() {
        EstadoPartida estado = new EstadoPartidaPerdida();
        Mapa mapa = obtenerMapaGenerico();

        assertThrows(RuntimeException.class, ()-> estado.terminarTurno(mapa));
    }
}
