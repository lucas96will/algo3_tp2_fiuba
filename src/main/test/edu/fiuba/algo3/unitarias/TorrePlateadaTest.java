package edu.fiuba.algo3.unitarias;

import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;
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

        TorrePlateada torrePlateada = new TorrePlateada(new Posicion(1,4), estadoIncompletoMock);

        torrePlateada.establecerPasarelasEnRango(pasarelaMock);
        assertEquals(torrePlateada.atacar(), 0);
        verify(estadoIncompletoMock, times(1)).atacar(pasarelas, 1); // chequeo que la clase torre
        // delegue correctamente el atacar al estado
    }

    @Test
    public void atacaSiEstaCompleta(){

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

        TorrePlateada torrePlateada = new TorrePlateada(new Posicion(1,4), estadoCompletoMock);

        torrePlateada.establecerPasarelasEnRango(pasarelaMock);
        assertEquals(torrePlateada.atacar(), 1);
        verify(estadoCompletoMock, times(1)).atacar(pasarelas, 1); // chequeo que la clase torre
        // delegue correctamente el atacar al estado
    }

    @Test
    public void seConstruyeDespuesDeDosTurnos() {
        EstadoDefensaIncompleto incompletoMock= mock(EstadoDefensaIncompleto.class);
        EstadoDefensaCompleto completoMock = mock(EstadoDefensaCompleto.class);
        TorrePlateada torrePlateada = new TorrePlateada(new Posicion(1,5), incompletoMock);
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
