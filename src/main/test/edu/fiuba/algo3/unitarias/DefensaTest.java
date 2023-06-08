package edu.fiuba.algo3.unitarias;

import edu.fiuba.algo3.modelo.Contador;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Defensa.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Partida.DatosJugador;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recurso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DefensaTest {
    private DatosJugador datosPartidaSingleton;

    @BeforeEach
    public void setup() {
        datosPartidaSingleton = DatosJugador.getInstance();
        datosPartidaSingleton.actualizarEstado(20, new Recurso(100), new Contador());
    }

    @Test
    public void defensaTardaEnConstruirseLoQueDice() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Defensa tarda en construirse lo que dice.");
        Defensa torreBlanca = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(1), new Posicion(1, 1));

        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(2,2));

        List<Enemigo> enemigos = new ArrayList<Enemigo>();
        enemigos.add(hormiga);

        assertEquals(0, torreBlanca.atacar(enemigos));
        torreBlanca.siguienteEstado();
        assertEquals(1, torreBlanca.atacar(enemigos));
    }

}
