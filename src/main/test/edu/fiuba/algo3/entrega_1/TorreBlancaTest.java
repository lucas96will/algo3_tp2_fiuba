package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.CondicionPartida;
import edu.fiuba.algo3.modelo.Defensa.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Partida;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TorreBlancaTest {
    @Test
    public void noAtacaSiEstaIncompleta(){
        Partida partida = new Partida();
        Jugador jugador = Jugador.crearJugadorBase("Joaquin");
        partida.crearPartidaGenerica(jugador);
        TorreBlanca torreBlanca1 = new TorreBlanca(1, 4);
        partida.insertarEnemigo(Enemigo.crearHormiga(1));

        partida.terminarTurno();
        partida.construir(torreBlanca1);
        partida.terminarTurno();
        CondicionPartida condicionPartida = partida.estado();

        assertTrue(condicionPartida.sigueJugando());

    }

    @Test
    public void AtacaSiEstaCompleta(){
        Partida partida = new Partida();
        Jugador jugador = Jugador.crearJugadorBase("Joaquin");
        partida.crearPartidaGenerica(jugador);
        TorreBlanca torreBlanca1 = new TorreBlanca(1, 4);
        partida.insertarEnemigo(Enemigo.crearHormiga(1));

        partida.terminarTurno();
        partida.construir(torreBlanca1);
        partida.terminarTurno();
        partida.terminarTurno();
        CondicionPartida condicionPartida = partida.estado();

        assertTrue(condicionPartida.gano());

    }
}