package edu.fiuba.algo3.unitarias;

import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Posicion;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

public class TorreBlancaTest {
    @Test
    public void noAtacaSiEstaIncompleta(){

        EstadoDefensa estadoIncompletoMock = mock(EstadoDefensaIncompleto.class);
        Hormiga hormigaMock = mock(Hormiga.class);

        when(hormigaMock.estaEnRango(anyInt(), any(Posicion.class))).thenReturn(true);
        when(estadoIncompletoMock.puedeAtacar()).thenReturn(false); // estado incompleto siempre retorna 0

        TorreBlanca torrePlateada = new TorreBlanca(20,2,5, estadoIncompletoMock, new Posicion(1,4));

        assertEquals(torrePlateada.atacar(hormigaMock), 0);
        verify(estadoIncompletoMock, times(1)).puedeAtacar(); // chequeo que la clase torre
    }

    @Test
    public void AtacaSiEstaCompleta(){
        EstadoDefensa estadoCompletoMock = mock(EstadoDefensaCompleto.class);
        Hormiga hormigaMock = mock(Hormiga.class);

        when(hormigaMock.estaEnRango(anyInt(), any(Posicion.class))).thenReturn(true);
        when(hormigaMock.recibirDanio(anyInt())).thenReturn(1);
        when(estadoCompletoMock.puedeAtacar()).thenReturn(true); // estado incompleto siempre retorna 0

        TorrePlateada torrePlateada = new TorrePlateada(20,2,5, estadoCompletoMock, new Posicion(1,4));

        assertEquals(torrePlateada.atacar(hormigaMock), 1);
        verify(estadoCompletoMock, times(1)).puedeAtacar(); // chequeo que la clase torre

    }

    @Test
    public void seConstruyeDespuesDeUnTurno() {
        EstadoDefensaIncompleto incompletoMock= mock(EstadoDefensaIncompleto.class);
        EstadoDefensaCompleto completoMock = mock(EstadoDefensaCompleto.class);
        TorreBlanca torreBlanca = new TorreBlanca(10, 1, 3, incompletoMock);

        doAnswer(invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            ((TorreBlanca)args[0]).establecerEstado(completoMock);
            return null;
        }).when(incompletoMock).siguienteEstado(torreBlanca);

        torreBlanca.siguienteEstado();
        torreBlanca.siguienteEstado();
        verify(incompletoMock, times(1)).siguienteEstado(torreBlanca); // verifico si solo interactua una vez con estado incompleto luego de pasar de turno dos veces
        verify(completoMock, times(1)).siguienteEstado(torreBlanca); // verifico si solo interactua una vez con estado completo luego de pasar de turno dos veces

    }
}