package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.CondicionPartida;
import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.Posicion;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TorreBlancaTest {
    @Test
    public void noAtacaSiEstaIncompleta(){

        EstadoDefensa estadoIncompletoMock = mock(EstadoDefensaIncompleto.class);
        Posicion posicion = new Posicion(0, 2);
        Meta pasarelaMock = mock(Meta.class);
        Largada largadaMock = mock(Largada.class);
        List<Pasarela> pasarelas = new ArrayList<>();
        pasarelas.add(pasarelaMock);

        pasarelaMock.posicion = posicion;
        pasarelaMock.anterior = largadaMock;
        when(largadaMock.noLlegoAlaLargada()).thenReturn(false);
        when(estadoIncompletoMock.atacar(pasarelas, 1)).thenReturn(0); // estado incompleto siempre retorna 0

        TorreBlanca torreBlanca = new TorreBlanca(new Posicion(1,4), estadoIncompletoMock);

        torreBlanca.establecerPasarelasEnRango(pasarelaMock);
        assertEquals(torreBlanca.atacar(), 0);
        verify(estadoIncompletoMock, times(1)).atacar(pasarelas, 1); // chequeo que la clase torre
                                                                                                // delegue correctamente el atacar al estado
    }

    @Test
    public void AtacaSiEstaCompleta(){
        EstadoDefensa estadoCompletoMock = mock(EstadoDefensaCompleto.class);
        Posicion posicion = new Posicion(0, 2);
        Meta pasarelaMock = mock(Meta.class);
        Largada largadaMock = mock(Largada.class);
        List<Pasarela> pasarelas = new ArrayList<>();
        pasarelas.add(pasarelaMock);

        pasarelaMock.posicion = posicion;
        pasarelaMock.anterior = largadaMock;
        when(largadaMock.noLlegoAlaLargada()).thenReturn(false);
        when(estadoCompletoMock.atacar(pasarelas, 1)).thenReturn(1);

        TorreBlanca torreBlanca = new TorreBlanca(new Posicion(1,4), estadoCompletoMock);

        torreBlanca.establecerPasarelasEnRango(pasarelaMock);
        assertEquals(torreBlanca.atacar(), 1);
        verify(estadoCompletoMock, times(1)).atacar(pasarelas, 1);

    }

    @Test
    public void seConstruyeDespuesDeUnTurno() {
        EstadoDefensaIncompleto incompletoMock= mock(EstadoDefensaIncompleto.class);
        EstadoDefensaCompleto completoMock = mock(EstadoDefensaCompleto.class);
        TorreBlanca torreBlanca = new TorreBlanca(new Posicion(1,5), incompletoMock);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                ((TorreBlanca)args[0]).establecerEstado(completoMock);
                return null;
            }
        }).when(incompletoMock).siguienteEstado(torreBlanca);

        torreBlanca.siguienteEstado();
        torreBlanca.siguienteEstado();
        verify(incompletoMock, times(1)).siguienteEstado(torreBlanca); // verifico si solo interactua una vez con estado incompleto luego de pasar de turno dos veces
        verify(completoMock, times(1)).siguienteEstado(torreBlanca); // verifico si solo interactua una vez con estado completo luego de pasar de turno dos veces

    }
}