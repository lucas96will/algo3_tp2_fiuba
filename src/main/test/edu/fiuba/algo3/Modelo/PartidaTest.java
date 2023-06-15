package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Jugador.Contador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Partida.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartidaTest {
    @BeforeEach
    public void setUp() {
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, new Recurso(100), "Joaquin");
        jugador.actualizarContador(new Contador());
    }

    @Test
    public void test01PartidaIniciaConEstadoGanado() {
        Partida partida = new Partida();
        partida.crearPartidaGenerica(Jugador.getInstance());
        EstadoPartida estadoEsperado = new EstadoPartidaGanada();
        EstadoPartida estadoObtenido = partida.estado();
        assertEquals(estadoEsperado, estadoObtenido);
    }

    @Test
    public void test02PartidaNuevaSeLeInsertaUnEnemigoYEstadoCambiaAEstadoPartidaSigueJugando() {
        Partida partida = new Partida();
        partida.crearPartidaGenerica(Jugador.getInstance());
        EstadoPartida estadoEsperado = new EstadoPartidaSigueJugando();

        partida.insertarEnemigo(new Hormiga(1,1,1,1,1));
        EstadoPartida estadoObtenido = partida.estado();

        assertEquals(estadoEsperado, estadoObtenido);

    }

    @Test
    public void test03PartidaIniciaConJugadorCon0DeVidaTieneEstadoPartidaPerdidaCorrectamente() {
        Partida partida = new Partida();
        Jugador.getInstance().actualizarEstado(0, new Recurso(100), "Joaquin");
        partida.crearPartidaGenerica(Jugador.getInstance());

        EstadoPartida estadoEsperado = new EstadoPartidaPerdida();
        EstadoPartida estadoObtenido = partida.estado();

        assertEquals(estadoEsperado, estadoObtenido);
    }

    @Test
    public void test04JugadorPierdeTodaSuVidaPartidaTieneEstadoPartidaPerdidaCorrectamente(){
        Partida partida = new Partida();
        Jugador.getInstance().actualizarEstado(10, new Recurso(100), "Joaquin");
        partida.crearPartidaGenerica(Jugador.getInstance());

        Jugador.getInstance().actualizarEstado(0, new Recurso(100), "Joaquin");


        EstadoPartida estadoEsperado = new EstadoPartidaPerdida();
        EstadoPartida estadoObtenido = partida.estado();

        assertEquals(estadoEsperado, estadoObtenido);
    }

}
