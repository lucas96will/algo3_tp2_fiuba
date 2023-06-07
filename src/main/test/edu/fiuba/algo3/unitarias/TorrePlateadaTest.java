package edu.fiuba.algo3.unitarias;

import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Posicion;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TorrePlateadaTest {
    @Test
    public void noAtacaSiEstaIncompleta(){

        EstadoDefensa estadoIncompletoMock = mock(EstadoDefensaIncompleto.class);
        Hormiga hormigaMock = mock(Hormiga.class);

        when(hormigaMock.estaEnRango(anyInt(), any(Posicion.class))).thenReturn(true);
        when(estadoIncompletoMock.puedeAtacar()).thenReturn(false); // estado incompleto siempre retorna 0

        TorrePlateada torrePlateada = new TorrePlateada(20,2,5, estadoIncompletoMock, new Posicion(1,4));


        assertEquals(torrePlateada.atacar(hormigaMock), 0);
        verify(estadoIncompletoMock, times(1)).puedeAtacar(); // chequeo que la clase torre

    }

    @Test
    public void atacaSiEstaCompleta(){

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
    public void seConstruyeDespuesDeDosTurnos() {
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
