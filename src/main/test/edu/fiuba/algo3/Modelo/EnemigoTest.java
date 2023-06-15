package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Jugador.Contador;
import edu.fiuba.algo3.modelo.Enemigo.Arania;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Partida.Partida;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



public class EnemigoTest {

    private Jugador jugadorSingleton;

    @BeforeEach
    public void setup() {
        jugadorSingleton = Jugador.getInstance();
        jugadorSingleton.actualizarEstado(20, new Recurso(100), "PEPE");
        jugadorSingleton.actualizarContador(new Contador());
    }

    @Test
    public void test1HormigaSeMueveParaDelanteYHaceDanio() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 1 hormiga se mueve para delante y hace daño.");
        /*Verificarquelasdefensas ataquen dentro del rango esperado (y verificar lo contrario*/

        Partida partida = new Partida();
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(100, new Recurso(10), "Josecito");
        partida.crearPartidaGenerica(jugador);

        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));
        partida.insertarEnemigo(hormiga);

        for(int i = 0; i < 8; i++){
            partida.terminarTurno();
        }

        assertFalse(jugador.estaIntacto()); //hormiga llego al final :c

    }

    @Test
    public void test2HormigaSeMueveCorrectamenteALaSiguientePosicion(){
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 2 hormiga se mueve correctamente a la siguiente posición");

        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));


        Parcela parcelaMock = mock(Casilla.class);
        when(parcelaMock.estaEnRangoLateralesA(any(Posicion.class))).thenReturn(true);
        when(parcelaMock.tieneLaMismaPosicion(any((Posicion.class)))).thenReturn(false);
        doAnswer(invocation -> {
            hormiga.mover(new Posicion(1,2));
            return true;
        }).when(parcelaMock).moveElEnemigo(any(Enemigo.class), any(Posicion.class), any(Posicion.class));



        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcelaMock);
        hormiga.moverse(parcelas);


        assertTrue(hormiga.estaEnRango(1, new Posicion(1,3)));
        assertFalse(hormiga.muerto());
        verify(parcelaMock, times(1)).moveElEnemigo(any(Enemigo.class),any(Posicion.class), any(Posicion.class));
    }

    @Test
    public void test3HormigaQueSeMovioSeMueveAUnaSiguientePosicionCorrectamente(){
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 3 Hormiga se movió se mueve después correctamente");

        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));
        List<Parcela> parcelas = new ArrayList<>();

        Parcela parcelaMockPrimera = mock(Casilla.class);
        Parcela parcelaMockSegunda = mock(Casilla.class);

        when(parcelaMockPrimera.estaEnRangoLateralesA(any(Posicion.class))).thenReturn(true);
        when(parcelaMockPrimera.tieneLaMismaPosicion(any((Posicion.class)))).thenReturn(false);
        doAnswer(invocation -> {
            hormiga.mover(new Posicion(1, 2));
            return true;
            }).when(parcelaMockPrimera).moveElEnemigo(any(Enemigo.class), any(Posicion.class), any(Posicion.class));

        when(parcelaMockSegunda.estaEnRangoLateralesA(any(Posicion.class))).thenReturn(false);
        when(parcelaMockSegunda.tieneLaMismaPosicion(any((Posicion.class)))).thenReturn(false);
        doAnswer(invocation -> {
            hormiga.mover(new Posicion(1, 3));
            return true;
        }).when(parcelaMockSegunda).moveElEnemigo(any(Enemigo.class), any(Posicion.class), any(Posicion.class));


        parcelas.add(parcelaMockPrimera);

        hormiga.moverse(parcelas); // posicion en (1,2)

        doAnswer(invocation -> {
            return false;
        }).when(parcelaMockPrimera).moveElEnemigo(any(Enemigo.class), any(Posicion.class), any(Posicion.class));

        when(parcelaMockPrimera.tieneLaMismaPosicion(any((Posicion.class)))).thenReturn(true);
        when(parcelaMockSegunda.estaEnRangoLateralesA(any(Posicion.class))).thenReturn(true);

        parcelas.add(parcelaMockSegunda);

        hormiga.moverse(parcelas); // posicion en (1,3)

        assertTrue(hormiga.estaEnRango(1, new Posicion(1,4)));
        assertFalse(hormiga.muerto());
        verify(parcelaMockPrimera, times(2)).moveElEnemigo(any(Enemigo.class), any(Posicion.class), any(Posicion.class));
        verify(parcelaMockSegunda, times(1)).moveElEnemigo(any(Enemigo.class), any(Posicion.class), any(Posicion.class));


    }

    @Test
    public void test4HormigaNoSeMueveALaSiguienteParcelaCorrectamenteYMuere(){
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 4 Hormiga se mueve a la siguiente parcela correctamente y muere");

        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));

        Parcela parcelaMock = mock(Casilla.class);
        when(parcelaMock.estaEnRangoLateralesA(any(Posicion.class))).thenReturn(false);
        when(parcelaMock.tieneLaMismaPosicion(any((Posicion.class)))).thenReturn(false);
        doAnswer(invocation -> {
            hormiga.mover(new Posicion(1,2));
            return false;
        }).when(parcelaMock).moveElEnemigo(any(Enemigo.class), any(Posicion.class), any(Posicion.class));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcelaMock);
        hormiga.moverse(parcelas);


        assertTrue(hormiga.estaEnRango(1, new Posicion(1,3)));
        assertTrue(hormiga.muerto());
//        verify(parcelaMock, times(1)).estaEnRangoLateralesA(any(Posicion.class));
//        verify(parcelaMock, times(0)).tieneLaMismaPosicion(any(Posicion.class));
        verify(parcelaMock, times(1)).moveElEnemigo(any(Enemigo.class), any(Posicion.class), any(Posicion.class));
    }

    @Test
    public void test2BisHormigaSeMueveCorrectamenteALaSiguientePosicion() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 2B Hormiga se mueve correctamente a la siguiente posición");
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
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 3B Hormiga que se movió se mueve a una siguiente posición correctamente");
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
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 4B Hormiga se mueve a la siguiente parcela y muere");

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
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 5 Hormiga hace daño al jugador correctamente");
        Parcela parcela = new Casilla(new Posicion(1,2));
        Enemigo hormiga = new Hormiga(1,3,1,1,1,new Posicion(5,5));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcela);
        hormiga.moverse(parcelas);

        Jugador jugador = Jugador.getInstance();
        int vidaEsperada = 17;
        assertTrue(hormiga.muerto());
        assertEquals(vidaEsperada, jugador.obtenerVidaJugador());
    }

    @Test
    public void test6HormigaNoHaceElDanioQueTieneAlJugadorCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 6 Hormiga no hace el daño que tiene al jugador correctamente");
        Parcela parcela = new Casilla(new Posicion(1,2));
        Enemigo hormiga = new Hormiga(1,2,1,1,1,new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcela);
        hormiga.moverse(parcelas);

        Jugador jugador = Jugador.getInstance();
        int vidaEsperada = 20;
        assertFalse(hormiga.muerto());
        assertEquals(vidaEsperada, jugador.obtenerVidaJugador());
    }

    @Test
    public void test5AraniaSeMueveCorrectamenteDosCasilleros() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 7 Araña se mueve correctamente dos casilleros");
        Parcela primeraParcela = new Casilla(new Posicion(1,2));
        Parcela segundaParcela = new Casilla(new Posicion(1,3));
        Parcela terceraParcela = new Casilla(new Posicion(1,4));
        Enemigo arania = new Arania(2,2,2,2,2,new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(primeraParcela);
        parcelas.add(segundaParcela);
        parcelas.add(terceraParcela);

        arania.moverse(parcelas);
        Jugador jugador = Jugador.getInstance();

        assertFalse(arania.estaEnRango(1, new Posicion(1,1)));
        assertTrue(arania.estaEnRango(1,new Posicion(1,4)));
        assertFalse(arania.estaEnRango(1, new Posicion(1,5)));
        assertEquals(20, jugador.obtenerVidaJugador());
        assertFalse(arania.muerto());
    }

    @Test
    public void test6AraniaSeMueveSoloUnEspacioYLuegoMuereCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 8 Araña se mueve un espacio y luego muere");
        Parcela primeraParcela = new Casilla(new Posicion(1,2));


        Enemigo arania = new Arania(2,2,2,2,2,new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(primeraParcela);

        arania.moverse(parcelas);
        Jugador jugador = Jugador.getInstance();

        assertTrue(arania.estaEnRango(1, new Posicion(1,3)));
        assertEquals(18, jugador.obtenerVidaJugador());
        assertTrue(arania.muerto());
    }

    @Test
    public void test7AraniaNoPuedeMoverseEnNingunCasilleroYMuere() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 9 Araña no puedo moverse y luego muere");
        Parcela primeraParcela = new Casilla(new Posicion(1,3)); // no alcanzable por enemigo

        Enemigo arania = new Arania(2,2,2,2,2,new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(primeraParcela);

        arania.moverse(parcelas);

        Jugador jugador = Jugador.getInstance();

        assertTrue(arania.estaEnRango(1, new Posicion(0,0)));
        assertEquals(18, jugador.obtenerVidaJugador());
        assertTrue(arania.muerto());
    }

}
