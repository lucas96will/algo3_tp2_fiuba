package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Defensa.Torre;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Jugador.Contador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DefensaTest {
    private Jugador jugadorSingleton;

    @BeforeEach
    public void setup() {
        jugadorSingleton = Jugador.getInstance();
        jugadorSingleton.actualizarEstado(20, new Recurso(100), "PEPE");
        jugadorSingleton.actualizarContador(new Contador());
    }

    @Test
    public void defensaTardaEnConstruirseLoQueDice() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Defensa tarda en construirse lo que dice.");
        Defensa torreBlanca = new Torre(10, 1, 3, new EstadoDefensaIncompleto(1), new Posicion(1, 1), "Torre Blanca");

        Enemigo hormiga = new Hormiga(new Posicion(2,2));

        List<Enemigo> enemigos = new ArrayList<Enemigo>();
        enemigos.add(hormiga);

        assertFalse(hormiga.muerto());
        torreBlanca.siguienteEstado();
        torreBlanca.atacar(enemigos);
        assertTrue(hormiga.muerto());
    }
}
