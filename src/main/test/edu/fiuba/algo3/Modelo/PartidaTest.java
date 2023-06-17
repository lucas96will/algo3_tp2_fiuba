package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Jugador.Contador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Partida.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartidaTest {

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

    @BeforeEach
    public void setUp() {
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, new Recurso(100), "Joaquin");
        jugador.actualizarContador(new Contador());
    }

    @Test
    public void test01PartidaIniciaConEstadoGanado() {
        Partida partida = new Partida();
        Mapa mapa = obtenerMapaGenerico();
        partida.crearPartida(Jugador.getInstance(), mapa);
        EstadoPartida estadoEsperado = new EstadoPartidaGanada();
        EstadoPartida estadoObtenido = partida.estado();
        assertEquals(estadoEsperado, estadoObtenido);
    }

    @Test
    public void test02PartidaNuevaSeLeInsertaUnEnemigoYEstadoCambiaAEstadoPartidaSigueJugando() {
        Partida partida = new Partida();
        Mapa mapa = obtenerMapaGenerico();
        partida.crearPartida(Jugador.getInstance(), mapa);
        EstadoPartida estadoEsperado = new EstadoPartidaSigueJugando();

        partida.insertarEnemigo(new Hormiga(1,1,1,1,1));
        EstadoPartida estadoObtenido = partida.estado();

        assertEquals(estadoEsperado, estadoObtenido);

    }

    @Test
    public void test03PartidaIniciaConJugadorCon0DeVidaTieneEstadoPartidaPerdidaCorrectamente() {
        Partida partida = new Partida();
        Jugador.getInstance().actualizarEstado(0, new Recurso(100), "Joaquin");
        Mapa mapa = obtenerMapaGenerico();
        partida.crearPartida(Jugador.getInstance(), mapa);

        EstadoPartida estadoEsperado = new EstadoPartidaPerdida();
        EstadoPartida estadoObtenido = partida.estado();

        assertEquals(estadoEsperado, estadoObtenido);
    }

    @Test
    public void test04JugadorPierdeTodaSuVidaPartidaTieneEstadoPartidaPerdidaCorrectamente(){
        Partida partida = new Partida();
        Jugador.getInstance().actualizarEstado(10, new Recurso(100), "Joaquin");
        Mapa mapa = obtenerMapaGenerico();
        partida.crearPartida(Jugador.getInstance(), mapa);

        Jugador.getInstance().actualizarEstado(0, new Recurso(100), "Joaquin");
        partida.actualizarEstado();


        EstadoPartida estadoEsperado = new EstadoPartidaPerdida();
        EstadoPartida estadoObtenido = partida.estado();

        assertEquals(estadoEsperado, estadoObtenido);
    }

}
