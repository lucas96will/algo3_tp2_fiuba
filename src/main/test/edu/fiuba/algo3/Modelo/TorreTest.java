package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Cargador.CargadorJson;
import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

/*    @Test
    public void noAtacaSiEstaIncompleta(){
        Logger.getInstance().logEstado("\n--> TESTUNITARIO TorreBlanca test 1: Torre no ataca si está incompleta");
        EstadoDefensa estadoIncompletoMock = mock(EstadoDefensaIncompleto.class);
        Hormiga hormigaMock = mock(Hormiga.class);

        when(hormigaMock.estaEnRango(anyInt(), any(Posicion.class))).thenReturn(true);


        Torre torreBlanca = new Torre(20,2,5, estadoIncompletoMock, new Posicion(1,4), "Torre Blanca");

        List<Enemigo> enemigos = new ArrayList<Enemigo>();
        enemigos.add(hormigaMock);
        torreBlanca.atacar(enemigos);
        verify(hormigaMock, never()).recibirAtaque(any(int.class), any(int.class), any(Posicion.class));
    }

    @Test
    public void AtacaSiEstaCompleta(){
        Logger.getInstance().logEstado("\n--> TESTUNITARIO TorreBlanca test 2: Torre ataca si está incompleta");
        EstadoDefensa estadoCompletoMock = mock(EstadoDefensaCompleto.class);
        Hormiga hormigaMock = mock(Hormiga.class);

        Torre torrePlateada = new Torre(20,2,5, estadoCompletoMock, new Posicion(1,4), "Torre Blanca");
        List<Enemigo> enemigos = new ArrayList<Enemigo>();
        enemigos.add(hormigaMock);
        torrePlateada.atacar(enemigos);
        verify(hormigaMock).recibirAtaque(any(int.class), any(int.class), any(Posicion.class));

    }*/

    @Test
    public void seConstruyeDespuesDeUnTurno() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO TorreBlanca test 3: Se construye después de un turno");
        EstadoDefensaIncompleto incompletoMock= mock(EstadoDefensaIncompleto.class);
        EstadoDefensaCompleto completoMock = mock(EstadoDefensaCompleto.class);
        Torre torreBlanca = new Torre(10, 1, 3, incompletoMock, "Torre Blanca");

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
        jugadorSingleton.actualizarEstado(100, new Recurso(5), "pepe");
        Mapa mapa = new CargadorJson().procesarMapa(rutaJsonMapa);
        Partida partida = new Partida(jugadorSingleton, mapa);

        Defensa defensa = new DefensaFactory().obtenerDefensa("Blanca");

        assertThrows(RecursosInsuficientesException.class, () -> partida.construir(defensa, new Posicion(2,2)));
    }
}