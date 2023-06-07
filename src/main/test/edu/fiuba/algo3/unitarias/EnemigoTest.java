package edu.fiuba.algo3.unitarias;

import edu.fiuba.algo3.modelo.Contador;
import edu.fiuba.algo3.modelo.Enemigo.Arania;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Partida.DatosJugador;
import edu.fiuba.algo3.modelo.Partida.Partida;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recurso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



public class EnemigoTest {

    private DatosJugador datosPartidaSingleton;

    @BeforeEach
    public void setup() {
        datosPartidaSingleton = DatosJugador.getInstance();
        datosPartidaSingleton.actualizarEstado(20, new Recurso(100), new Contador());
    }

    @Test
    public void test1HormigaSeMueveParaDelanteYHaceDanio() {
        /*Verificarquelasdefensas ataquen dentro del rango esperado (y verificar lo contrario*/

        Partida partida = new Partida();
        Jugador jugador = new Jugador(new Recurso(10), 100, "Josecito");
        partida.crearPartidaGenerica(jugador);

        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));
        partida.insertarEnemigo(hormiga);

        for(int i = 0; i < 8; i++){
            partida.terminarTurno();
        }

        assertFalse(partida.jugadorTieneTodaLaVidaYMaximosCreditos()); //hormiga llego al final :c

    }

    @Test
    public void test2HormigaSeMueveCorrectamenteALaSiguientePosicion(){

        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));

        Parcela parcelaMock = mock(Casilla.class);
        when(parcelaMock.estaEnRangoLateralesA(any(Posicion.class))).thenReturn(true);
        when(parcelaMock.tieneLaMismaPosicion(any((Posicion.class)))).thenReturn(false);
        doAnswer(invocation -> {
            hormiga.mover(new Posicion(1,2));
            return true;
        }).when(parcelaMock).moveElEnemigo(any(Enemigo.class));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcelaMock);
        hormiga.moverse(parcelas);


        assertTrue(hormiga.estaEnRango(1, new Posicion(1,3)));
        assertFalse(hormiga.muerto());
        verify(parcelaMock, times(1)).estaEnRangoLateralesA(any(Posicion.class));
        verify(parcelaMock, times(0)).tieneLaMismaPosicion(any(Posicion.class));
        verify(parcelaMock, times(1)).moveElEnemigo(any(Enemigo.class));
    }

    @Test
    public void test3HormigaQueSeMovioSeMueveAUnaSiguientePosicionCorrectamente(){

        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));
        List<Parcela> parcelas = new ArrayList<>();

        Parcela parcelaMockPrimera = mock(Casilla.class);
        Parcela parcelaMockSegunda = mock(Casilla.class);

        when(parcelaMockPrimera.estaEnRangoLateralesA(any(Posicion.class))).thenReturn(true);
        when(parcelaMockPrimera.tieneLaMismaPosicion(any((Posicion.class)))).thenReturn(false);
        doAnswer(invocation -> {
            hormiga.mover(new Posicion(1, 2));
                return true;
            }).when(parcelaMockPrimera).moveElEnemigo(any(Enemigo.class));

        when(parcelaMockSegunda.estaEnRangoLateralesA(any(Posicion.class))).thenReturn(false);
        when(parcelaMockSegunda.tieneLaMismaPosicion(any((Posicion.class)))).thenReturn(false);
        doAnswer(invocation -> {
            hormiga.mover(new Posicion(1, 3));
            return true;
        }).when(parcelaMockSegunda).moveElEnemigo(any(Enemigo.class));


        parcelas.add(parcelaMockPrimera);
        when(parcelaMockPrimera.tieneLaMismaPosicion(any((Posicion.class)))).thenReturn(true);
        when(parcelaMockSegunda.estaEnRangoLateralesA(any(Posicion.class))).thenReturn(true);
        parcelas.add(parcelaMockSegunda);

        hormiga.moverse(parcelas); // posicion en (1,2)
        hormiga.moverse(parcelas); // posicion en (1,3)
        assertTrue(hormiga.estaEnRango(1, new Posicion(1,4)));
        assertFalse(hormiga.muerto());
        verify(parcelaMockPrimera, times(1)).moveElEnemigo(any(Enemigo.class));
        verify(parcelaMockSegunda, times(1)).moveElEnemigo(any(Enemigo.class));


    }

    @Test
    public void test4HormigaNoSeMueveALaSiguienteParcelaCorrectamenteYMuere(){

        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));

        Parcela parcelaMock = mock(Casilla.class);
        when(parcelaMock.estaEnRangoLateralesA(any(Posicion.class))).thenReturn(false);
        when(parcelaMock.tieneLaMismaPosicion(any((Posicion.class)))).thenReturn(false);
        doAnswer(invocation -> {
            hormiga.mover(new Posicion(1,2));
            return true;
        }).when(parcelaMock).moveElEnemigo(any(Enemigo.class));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcelaMock);
        hormiga.moverse(parcelas);


        assertFalse(hormiga.estaEnRango(1, new Posicion(1,3)));
        assertTrue(hormiga.muerto());
        verify(parcelaMock, times(1)).estaEnRangoLateralesA(any(Posicion.class));
        verify(parcelaMock, times(0)).tieneLaMismaPosicion(any(Posicion.class));
        verify(parcelaMock, times(0)).moveElEnemigo(any(Enemigo.class));
    }

    @Test
    public void test2BisHormigaSeMueveCorrectamenteALaSiguientePosicion() {
        Parcela parcela = new Casilla(new Posicion(1,2));
        Enemigo hormiga = new Hormiga(1,1,1,1,1,new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcela);

        hormiga.moverse(parcelas);

        assertTrue(hormiga.estaEnRango(1,new Posicion(1,3)));
        assertFalse(hormiga.muerto());
    }

    @Test
    public void test3BisHormigaQueSeMovioSeMueveAUnaSiguientePosicionCorrectamente() {
        Parcela primeraParcela = new Casilla(new Posicion(1,2));
        Parcela segundaParcela = new Casilla(new Posicion(1,3));
        Enemigo hormiga = new Hormiga(1,1,1,1,1,new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(primeraParcela);
        parcelas.add(segundaParcela);

        hormiga.moverse(parcelas);
        hormiga.moverse(parcelas);

        assertTrue(hormiga.estaEnRango(1,new Posicion(1,4)));
        assertFalse(hormiga.muerto());
    }


    @Test
    public void test4BisHormigaNoSeMueveALaSiguienteParcelaCorrectamenteYMuere(){

        Parcela parcela = new Casilla(new Posicion(1,2));
        Enemigo hormiga = new Hormiga(1,1,1,1,1,new Posicion(5,5));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcela);

        hormiga.moverse(parcelas);

        assertFalse(hormiga.estaEnRango(1,new Posicion(1,3)));
        assertTrue(hormiga.muerto());
    }

    @Test
    public void test5HormigaHaceElDanioQueTieneAlJugadorCorrectamente() {
        Parcela parcela = new Casilla(new Posicion(1,2));
        Enemigo hormiga = new Hormiga(1,3,1,1,1,new Posicion(5,5));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcela);
        hormiga.moverse(parcelas);

        DatosJugador datosJugador = DatosJugador.getInstance();
        int vidaEsperada = 17;
        assertTrue(hormiga.muerto());
        assertEquals(vidaEsperada, datosJugador.obtenerVidaJugador());
    }

    @Test
    public void test6HormigaNoHaceElDanioQueTieneAlJugadorCorrectamente() {
        Parcela parcela = new Casilla(new Posicion(1,2));
        Enemigo hormiga = new Hormiga(1,2,1,1,1,new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcela);
        hormiga.moverse(parcelas);

        DatosJugador datosJugador = DatosJugador.getInstance();
        int vidaEsperada = 20;
        assertFalse(hormiga.muerto());
        assertEquals(vidaEsperada, datosJugador.obtenerVidaJugador());
    }

    @Test
    public void test5AraniaSeMueveCorrectamenteDosCasilleros() {
        Parcela primeraParcela = new Casilla(new Posicion(1,2));
        Parcela segundaParcela = new Casilla(new Posicion(1,3));
        Parcela terceraParcela = new Casilla(new Posicion(1,4));
        Enemigo arania = new Arania(2,2,2,2,2,new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(primeraParcela);
        parcelas.add(segundaParcela);
        parcelas.add(terceraParcela);

        arania.moverse(parcelas);
        DatosJugador datosJugador = DatosJugador.getInstance();

        assertFalse(arania.estaEnRango(1, new Posicion(1,1)));
        assertTrue(arania.estaEnRango(1,new Posicion(1,4)));
        assertFalse(arania.estaEnRango(1, new Posicion(1,5)));
        assertEquals(20, datosJugador.obtenerVidaJugador());
        assertFalse(arania.muerto());
    }

    @Test
    public void test6AraniaSeMueveSoloUnEspacioYLuegoMuereCorrectamente() {
        Parcela primeraParcela = new Casilla(new Posicion(1,2));


        Enemigo arania = new Arania(2,2,2,2,2,new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(primeraParcela);

        arania.moverse(parcelas);
        DatosJugador datosJugador = DatosJugador.getInstance();

        assertTrue(arania.estaEnRango(1, new Posicion(1,3)));
        assertEquals(18, datosJugador.obtenerVidaJugador());
        assertTrue(arania.muerto());
    }

    @Test
    public void test7AraniaNoPuedeMoverseEnNingunCasilleroYMuere() {
        Parcela primeraParcela = new Casilla(new Posicion(1,3)); // no alcanzable por enemigo

        Enemigo arania = new Arania(2,2,2,2,2,new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(primeraParcela);

        arania.moverse(parcelas);

        DatosJugador datosJugador = DatosJugador.getInstance();

        assertTrue(arania.estaEnRango(1, new Posicion(0,0)));
        assertEquals(18, datosJugador.obtenerVidaJugador());
        assertTrue(arania.muerto());
    }

}
