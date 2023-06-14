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
import static org.mockito.Mockito.times;

public class TorrePlateadaTest {
    @Test
    public void noAtacaSiEstaIncompleta(){
        Logger.getInstance().logEstado("\n--> TESTUNITARIO TorrePlateada test 1: Torre no ataca si está incompleta");
        EstadoDefensa estadoIncompletoMock = mock(EstadoDefensaIncompleto.class);
        Hormiga hormigaMock = mock(Hormiga.class);

        when(hormigaMock.estaEnRango(anyInt(), any(Posicion.class))).thenReturn(true);
        when(estadoIncompletoMock.puedeAtacar()).thenReturn(false); // estado incompleto siempre retorna 0

        TorrePlateada torrePlateada = new TorrePlateada(20,2,5, estadoIncompletoMock, new Posicion(1,4));

        List<Enemigo> enemigos = new ArrayList<Enemigo>();
        enemigos.add(hormigaMock);

        assertEquals(torrePlateada.atacar(enemigos), 0);
        verify(estadoIncompletoMock, times(1)).puedeAtacar(); // chequeo que la clase torre

    }

    @Test
    public void atacaSiEstaCompleta(){
        Logger.getInstance().logEstado("\n--> TESTUNITARIO TorrePlateada test 2: Torre ataca si está incompleta");
        EstadoDefensa estadoCompletoMock = mock(EstadoDefensaCompleto.class);
        Hormiga hormigaMock = mock(Hormiga.class);

        when(hormigaMock.estaEnRango(anyInt(), any(Posicion.class))).thenReturn(true);
        when(hormigaMock.recibirDanio(anyInt())).thenReturn(1);
        when(estadoCompletoMock.puedeAtacar()).thenReturn(true); // estado incompleto siempre retorna 0

        TorrePlateada torrePlateada = new TorrePlateada(20,2,5, estadoCompletoMock, new Posicion(1,4));

        List<Enemigo> enemigos = new ArrayList<Enemigo>();
        enemigos.add(hormigaMock);

        assertEquals(torrePlateada.atacar(enemigos), 1);
        verify(estadoCompletoMock, times(1)).puedeAtacar(); // chequeo que la clase torre
    }

    @Test
    public void seConstruyeDespuesDeDosTurnos() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO TorrePlateada test 3: Torre se construye después de dos turnos");
        EstadoDefensaIncompleto incompletoMock= mock(EstadoDefensaIncompleto.class);
        EstadoDefensaCompleto completoMock = mock(EstadoDefensaCompleto.class);
        TorrePlateada torrePlateada = new TorrePlateada(20, 2, 5, incompletoMock);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                ((TorrePlateada)args[0]).establecerEstado(incompletoMock);
                return null;
            }
        }).when(incompletoMock).siguienteEstado(torrePlateada);

        torrePlateada.siguienteEstado();

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                ((TorrePlateada)args[0]).establecerEstado(completoMock);
                return null;
            }
        }).when(incompletoMock).siguienteEstado(torrePlateada);

        torrePlateada.siguienteEstado();
        verify(incompletoMock, times(2)).siguienteEstado(torrePlateada); // verifico si solo interactua dos veces con estado incompleto luego de pasar dos veces
        verify(completoMock, times(0)).siguienteEstado(torrePlateada); // verifico que no interactue con mock de completo

    }
}
