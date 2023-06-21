package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Cargador.CargadorJson;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaCompleto;
import edu.fiuba.algo3.modelo.Defensa.Torre;
import edu.fiuba.algo3.modelo.Direccion.Derecha;
import edu.fiuba.algo3.modelo.Enemigo.*;
import edu.fiuba.algo3.modelo.Jugador.Contador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Partida.Partida;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.Factory.DefensaFactory;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


public class EnemigoTest {

    private Jugador jugadorSingleton;
    private ContadorTurnos turnos;
    private CargadorJson cargador;

    @BeforeEach
    public void setup() {
        jugadorSingleton = Jugador.getInstance();
        jugadorSingleton.actualizarEstado(20, new Recurso(100), "PEPE");
        jugadorSingleton.actualizarContador(new Contador());
        jugadorSingleton.resetearDefensas();
        turnos = ContadorTurnos.obtenerContador();
        turnos.resetear();
        cargador = new CargadorJson();
    }

    public Mapa obtenerMapaGenerico() {
            Mapa mapa = new Mapa();
            Pasarela pasarela = new Pasarela(new Largada());
            pasarela.establecerDireccion(new Derecha());
            mapa.agregarParcelaEnPosicion(pasarela, new Posicion(1,1));
    
            for(int i = 2; i < 7; i++){
                pasarela = new Pasarela(new Casilla());
                pasarela.establecerDireccion(new Derecha());
                mapa.agregarParcelaEnPosicion(pasarela, new Posicion(1,i));
            }
            pasarela = new Pasarela(new Meta());
            pasarela.establecerDireccion(new Derecha());
            mapa.agregarParcelaEnPosicion(pasarela, new Posicion(1,7));
    
            for(int j = 2; j < 8; j++) {
                for(int k = 1; k < 8; k++) {
                    mapa.agregarParcelaEnPosicion(new Tierra(), new Posicion(j, k));
                }
            }
            for(int h = 1; h < 8; h++) {
                mapa.agregarParcelaEnPosicion(new Rocoso(), new Posicion(7, h));
            }
            
            return mapa;
    }
        
    @Test
    public void test1HormigaSeMueveParaDelanteYHaceDanio() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 1 hormiga se mueve para delante y hace daño.");
        /*Verificarquelasdefensas ataquen dentro del rango esperado (y verificar lo contrario*/

        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(100, new Recurso(10), "Josecito");
        Mapa mapa = obtenerMapaGenerico();
        Partida partida = new Partida(jugador, mapa);

        Enemigo hormiga = new Hormiga(new Posicion(1,1));
        partida.insertarEnemigo(hormiga);

        for(int i = 0; i < 8; i++){
            partida.terminarTurno();
        }

        assertFalse(jugador.estaIntacto()); //hormiga llego al final :c

    }

    @Test
    public void test2BisHormigaSeMueveCorrectamenteALaSiguientePosicion() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 2B Hormiga se mueve correctamente a la siguiente posición");

        Pasarela largada = new Pasarela(new Posicion(1,1), new Casilla());
        largada.establecerDireccion(new Derecha());
        Pasarela parcela = new Pasarela(new Posicion(1,2), new Casilla());
        parcela.establecerDireccion(new Derecha());

        Enemigo hormiga = new Hormiga(new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(largada);
        parcelas.add(parcela);

        hormiga.moverse(parcelas);

        assertTrue(hormiga.estaEnRango(1,new Posicion(1,3)));
        assertFalse(hormiga.muerto());
    }

    @Test
    public void test3BisHormigaQueSeMovioSeMueveAUnaSiguientePosicionCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 3B Hormiga que se movió se mueve a una siguiente posición correctamente");
        Pasarela inicialParcela = new Pasarela(new Posicion(1,1), new Casilla());
        inicialParcela.establecerDireccion(new Derecha());
        Pasarela primeraParcela = new Pasarela(new Posicion(1,2), new Casilla());
        primeraParcela.establecerDireccion(new Derecha());
        Pasarela segundaParcela = new Pasarela(new Posicion(1,3), new Casilla());
        Enemigo hormiga = new Hormiga(new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(inicialParcela);
        parcelas.add(primeraParcela);
        parcelas.add(segundaParcela);

        hormiga.moverse(parcelas);
        hormiga.moverse(parcelas);

        assertTrue(hormiga.estaEnRango(1,new Posicion(1,4)));
        assertFalse(hormiga.muerto());
    }


    @Test
    public void test4BisHormigaSeMueveALaMetaCorrectamenteYMuere(){
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 4B Hormiga se mueve a la siguiente parcela y muere");

        Pasarela parcela = new Pasarela(new Posicion(1,2), new Casilla());
        parcela.establecerDireccion(new Derecha());
        Pasarela meta = new Pasarela(new Posicion(1,3), new Meta());
        Enemigo hormiga = new Hormiga(new Posicion(1,2));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcela);
        parcelas.add(meta);

        hormiga.moverse(parcelas);
        hormiga.moverse(parcelas);
        assertTrue(hormiga.estaEnRango(1,new Posicion(1,4)));
        assertTrue(hormiga.muerto());
    }

    @Test
    public void test5HormigaHaceElDanioQueTieneAlJugadorCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 5 Hormiga hace daño al jugador correctamente");
        Pasarela parcela = new Pasarela(new Posicion(1,2), new Casilla());
        parcela.establecerDireccion(new Derecha());
        Pasarela meta = new Pasarela(new Posicion(1,3), new Meta());
        Enemigo hormiga = new Hormiga(new Posicion(1,2));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcela);
        parcelas.add(meta);
        hormiga.moverse(parcelas);
        hormiga.moverse(parcelas);

        Jugador jugador = Jugador.getInstance();
        int vidaEsperada = 19;
        assertTrue(hormiga.muerto());
        assertEquals(vidaEsperada, jugador.obtenerVidaJugador());
    }

    @Test
    public void test6HormigaNoHaceElDanioQueTieneAlJugadorCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 6 Hormiga no hace el daño que tiene al jugador correctamente");
        Parcela parcela = new Pasarela(new Posicion(1,2), new Casilla());
        Enemigo hormiga = new Hormiga(new Posicion(1,1));

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
        Pasarela parcelaInicial = new Pasarela(new Posicion(1,1), new Casilla());
        parcelaInicial.establecerDireccion(new Derecha());
        Pasarela primeraParcela = new Pasarela(new Posicion(1,2), new Casilla());
        primeraParcela.establecerDireccion(new Derecha());
        Pasarela segundaParcela = new Pasarela(new Posicion(1,3), new Casilla());
        segundaParcela.establecerDireccion(new Derecha());
        Pasarela terceraParcela = new Pasarela(new Posicion(1,4), new Casilla());
        terceraParcela.establecerDireccion(new Derecha());
        Enemigo arania = new Arania(new Posicion(1,1));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(parcelaInicial);
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

        Pasarela primeraParcela = new Pasarela(new Posicion(1,2), new Meta());

        Enemigo arania = new Arania(new Posicion(1,2));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(primeraParcela);

        arania.moverse(parcelas);
        Jugador jugador = Jugador.getInstance();

        assertTrue(arania.estaEnRango(1, new Posicion(1,3)));
        assertEquals(18, jugador.obtenerVidaJugador());
        assertTrue(arania.muerto());
    }

    @Test
    public void test7AraniaSeMueveALaMetaYMuereCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 9 Araña no puedo moverse y luego muere");
        Pasarela primeraParcela = new Pasarela(new Posicion(1,2), new Casilla());
        primeraParcela.establecerDireccion(new Derecha());
        Pasarela segundaParcela = new Pasarela(new Posicion(1,3), new Meta());
        segundaParcela.establecerDireccion(new Derecha());

        Enemigo arania = new Arania(new Posicion(1,2));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(primeraParcela);
        parcelas.add(segundaParcela);

        arania.moverse(parcelas);
        arania.moverse(parcelas);
        Jugador jugador = Jugador.getInstance();

        assertTrue(arania.estaEnRango(1, new Posicion(0,4)));
        assertEquals(18, jugador.obtenerVidaJugador());
        assertTrue(arania.muerto());
    }

    @Test
    public void test8TopoSeMueveCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 10 Topo se mueve 2 casillas correctamente");
        Pasarela primeraParcela = new Pasarela(new Posicion(1,2), new Casilla());
        primeraParcela.establecerDireccion(new Derecha());
        Pasarela segundaParcela = new Pasarela(new Posicion(1,3), new Casilla());
        segundaParcela.establecerDireccion(new Derecha());
        Pasarela terceraParcela = new Pasarela(new Posicion(1,4), new Casilla());
        terceraParcela.establecerDireccion(new Derecha());

        Enemigo topo = new Topo(new Posicion(1, 2));

        List<Parcela> parcelas = new ArrayList<>();
        parcelas.add(primeraParcela);
        parcelas.add(segundaParcela);
        parcelas.add(terceraParcela);

        topo.moverse(parcelas);
        topo.moverse(parcelas);

        assertTrue(topo.estaEnRango(1, new Posicion(1, 5)));
        assertFalse(topo.muerto());
    }

    @Test
    public void test9TopoAumentaVelocidadCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 11 Topo aumenta su velocidad correctamente");

        Enemigo topo = new Topo(new Posicion(1, 1));

        List<Parcela> parcelas = new ArrayList<>();
        Pasarela parcela;

        for(int i = 1; i < 25; i++) {
            parcela = new Pasarela(new Posicion(1, i),  new Casilla());
            parcela.establecerDireccion(new Derecha());
            parcelas.add(parcela);
        }

        for (int i = 0; i < 5; i++) {
            topo.moverse(parcelas);
        }
        assertTrue(topo.estaEnRango(1, new Posicion(1, 6)));

        for (int i = 0; i < 6; i++) {
            topo.moverse(parcelas);
        }
        assertTrue(topo.estaEnRango(1, new Posicion(1, 18)));

        topo.moverse(parcelas);
        topo.moverse(parcelas);

        assertTrue(topo.estaEnRango(1, new Posicion(1, 24)));
    }

    @Test
    public void test10TopoDaniaCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 12 Topo daña correctamente al jugador dependiendo del turno");
        Enemigo topo1 = new Topo(new Posicion(1, 1));
        Enemigo topo2= new Topo(new Posicion(1, 1));

        List<Parcela> parcelas = new ArrayList<>();

        Pasarela parcela = new Pasarela(new Posicion(1,1), new Meta());
        parcela.establecerDireccion(new Derecha());

        parcelas.add(parcela);

        topo1.moverse(parcelas);
        turnos.incrementarTurno();

        assertEquals(18, jugadorSingleton.obtenerVidaJugador());

        topo2.moverse(parcelas);

        assertEquals(13, jugadorSingleton.obtenerVidaJugador());
    }

    @Test
    public void test11TopoNoRecibeDanioDeTorres() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 13 Topo no recibe ataque de torre");
        Enemigo topo = new Topo(new Posicion(1, 1));
        Defensa torre = new Torre(10 , 6, 3, new EstadoDefensaCompleto(), new Posicion(1,2), "Torrecilla");

        List<Enemigo> enemigos = new ArrayList<>();
        enemigos.add(topo);

        torre.atacar(enemigos);

        assertFalse(topo.muerto());

    }

    @Test
    public void test12LechuzaSeMuereAlserAtacadaPorTorre() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 14 Lechuza debería morir");
        Enemigo lechuza = new Lechuza(new Posicion(1, 1));
        Defensa torre = new Torre(10 , 6, 3, new EstadoDefensaCompleto(), new Posicion(1,2), "Torrecilla");

        List<Enemigo> enemigos = new ArrayList<>();
        enemigos.add(lechuza);

        torre.atacar(enemigos);

        assertTrue(lechuza.muerto());
    }

    @Test
    public void test13LechuzaSeMueveEnL() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 15 Lechuza debería estar en la posicion esperada");
        Mapa mapa = cargador.procesarMapa("data/jsonTests/mapa.json");
        Enemigo lechuza = new Lechuza(new Posicion(100, 200));

        mapa.insertarEnemigo(lechuza);

        mapa.moverEnemigos();
        mapa.moverEnemigos();
        mapa.moverEnemigos();

        assertTrue(lechuza.estaEnRango(0, new Posicion(3,15))); //está sobre la misma casilla
    }
    
    @Test
    public void test14LechuzaMuereYRompeUnaTorre() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 16 Lechuza debería romper la torre al llegar a meta");

        List<Defensa> defensas = jugadorSingleton.obtenerDefensas();

        Enemigo lechuza = new Lechuza(new Posicion(1, 1));

        DefensaFactory factoria = new DefensaFactory();
        defensas.add(factoria.obtenerDefensa("Blanca"));
        defensas.add(factoria.obtenerDefensa("Blanca"));

        Pasarela parcela = new Pasarela(new Posicion(1,1), new Meta());
        List<Parcela> parcelas = new ArrayList<>();

        parcelas.add(parcela);

        lechuza.moverse(parcelas);

        assertEquals(1, defensas.size());
    }

    @Test
    public void test15LechuzaSeMueveEnDiagonalCuandoEstaHerida() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO enemigo test 17 Lechuza debería estar en la posicion esperada");
        Mapa mapa = cargador.procesarMapa("data/jsonTests/mapa.json");
        Enemigo lechuza = new Lechuza(new Posicion(9, 12));
        Defensa torre = new Torre(10 , 3, 3, new EstadoDefensaCompleto(), "Torrecilla");

        mapa.construir(torre, new Posicion(1,3));
        mapa.actualizarEstadoDefensas();
        mapa.insertarEnemigo(lechuza);
        mapa.defensasAtacar();

        mapa.moverEnemigos();
        mapa.moverEnemigos();

        assertTrue(lechuza.estaEnRango(0, new Posicion(9,12))); //está sobre la misma casilla
        mapa.moverEnemigos();
    }
}
