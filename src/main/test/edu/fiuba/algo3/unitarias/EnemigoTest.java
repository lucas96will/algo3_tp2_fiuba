package edu.fiuba.algo3.unitarias;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Partida.Partida;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemigoTest {
    @Test
    public void caso1HormigaSeMueveParaDelanteYHaceDanio() {
        /*Verificarquelasdefensas ataquen dentro del rango esperado (y verificar lo contrario*/

        Partida partida = new Partida();
        Jugador jugador = new Jugador(10, 100, "Josecito");
        partida.crearPartidaGenerica(jugador);

        Enemigo hormiga = Enemigo.crearHormiga();
        partida.insertarEnemigo(hormiga);

        for(int i = 0; i < 8; i++){
            partida.terminarTurno();
        }

        assertFalse(partida.jugadorTieneTodaLaVidaYMaximosCreditos()); //hormiga llego al final :c

    }
}
