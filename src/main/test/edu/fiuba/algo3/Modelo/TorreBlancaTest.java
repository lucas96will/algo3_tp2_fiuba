package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

public class TorreBlancaTest {
    @Test
    public void noAtacaSiEstaIncompleta(){
        Logger.getInstance().logEstado("\n--> TESTUNITARIO TorreBlanca test 1: Torre no ataca si está incompleta");
        EstadoDefensa estadoIncompletoMock = mock(EstadoDefensaIncompleto.class);
        Hormiga hormigaMock = mock(Hormiga.class);

        when(hormigaMock.estaEnRango(anyInt(), any(Posicion.class))).thenReturn(true);
        when(estadoIncompletoMock.puedeAtacar()).thenReturn(false); // estado incompleto siempre retorna 0

        Torre torreBlanca = new Torre(20,2,5, estadoIncompletoMock, new Posicion(1,4), "Torre Blanca");

        List<Enemigo> enemigos = new ArrayList<Enemigo>();
        enemigos.add(hormigaMock);

        assertEquals(torreBlanca.atacar(enemigos), 0);
        verify(estadoIncompletoMock, times(1)).puedeAtacar(); // chequeo que la clase torre
    }

    @Test
    public void AtacaSiEstaCompleta(){
        Logger.getInstance().logEstado("\n--> TESTUNITARIO TorreBlanca test 2: Torre ataca si está incompleta");
        EstadoDefensa estadoCompletoMock = mock(EstadoDefensaCompleto.class);
        Hormiga hormigaMock = mock(Hormiga.class);

        when(hormigaMock.estaEnRango(anyInt(), any(Posicion.class))).thenReturn(true);
        when(hormigaMock.recibirDanio(anyInt())).thenReturn(1);
        when(estadoCompletoMock.puedeAtacar()).thenReturn(true); // estado incompleto siempre retorna 0

        Torre torrePlateada = new Torre(20,2,5, estadoCompletoMock, new Posicion(1,4), "Torre Blanca");
        List<Enemigo> enemigos = new ArrayList<Enemigo>();
        enemigos.add(hormigaMock);

        assertEquals(torrePlateada.atacar(enemigos), 1);
        verify(estadoCompletoMock, times(1)).puedeAtacar(); // chequeo que la clase torre

    }

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