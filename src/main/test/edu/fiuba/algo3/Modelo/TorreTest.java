package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Cargador.CargadorJson;
import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Factory.DefensaFactory;
import edu.fiuba.algo3.modelo.Jugador.Contador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Partida.Partida;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TorreTest {

    private Jugador jugadorSingleton;
    private ContadorTurnos turnos;
    private final String rutaJsonMapa = "data/jsonTests/mapaTest.json";

    @BeforeEach
    public void setup() {
        jugadorSingleton = Jugador.getInstance();
        jugadorSingleton.actualizarEstado(20, new Recurso(100), "PEPE");
        jugadorSingleton.actualizarContador(new Contador());
        jugadorSingleton.resetearDefensas();
        turnos = ContadorTurnos.obtenerContador();
        turnos.resetear();
    }


    @Test
    public void seConstruyeDespuesDeUnTurno() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO TorreBlanca test 3: Se construye después de un turno");
        EstadoDefensaIncompleto incompletoMock= mock(EstadoDefensaIncompleto.class);
        EstadoDefensaCompleto completoMock = mock(EstadoDefensaCompleto.class);
        Torre torreBlanca = new Torre(10, 1, 3, incompletoMock, new Posicion(1,1),"Torre Blanca");

        doAnswer(invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            ((Torre)args[0]).establecerEstado(completoMock);
            return null;
        }).when(incompletoMock).siguienteEstado(torreBlanca);

        torreBlanca.siguienteEstado();
        torreBlanca.siguienteEstado();
        verify(incompletoMock, times(1)).siguienteEstado(torreBlanca); // verifico si solo interactua una vez con estado incompleto luego de pasar de turno dos veces
        verify(completoMock, times(1)).siguienteEstado(torreBlanca); // verifico si solo interactua una vez con estado completo luego de pasar de turno dos veces

    }

    @Test
    public void jugadorNoPuedeComprarTorreLanzaExcepcionRecursosInsuficientesException() {
        Mapa mapa = new CargadorJson().procesarMapa(rutaJsonMapa);
        Partida partida = new Partida(jugadorSingleton, mapa);
        jugadorSingleton.actualizarEstado(100, new Recurso(5), "pepe");
        Defensa defensa = new DefensaFactory().obtenerDefensa("Blanca", new Posicion(2,2));

        assertThrows(RecursosInsuficientesException.class, () ->         jugadorSingleton.comprar(defensa));
    }
}