package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

public class TorreTest {
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
}