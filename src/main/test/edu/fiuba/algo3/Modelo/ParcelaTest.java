package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Parcela.Pasarela.*;
import edu.fiuba.algo3.modelo.Partida.Partida;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaCompleto;
import edu.fiuba.algo3.modelo.Jugador.Contador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Defensa.Torre;
import edu.fiuba.algo3.modelo.Factory.EnemigoFactory;
import edu.fiuba.algo3.modelo.Cargador.CargadorJson;
import edu.fiuba.algo3.modelo.Cargador.Cargador;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelaTest {

    private Jugador jugadorSingleton;
    private ContadorTurnos turnos;
    private CargadorJson cargador;
    private final String rutaJsonEnemigos = "data/jsonTests/enemigos.json";
    private final String rutaJsonMapa = "data/jsonTests/mapa.json";

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


    @Test
    public void enemigoNoSePuedeCrearEnTierra() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test 1: Enemigo no se puede crear en tierra");
        Parcela pasarela = new Pasarela(new Posicion(0, 0), new Casilla());
        Parcela tierra = new Tierra(new Posicion(0, 0));
        Parcela rocoso = new Rocoso(new Posicion(0, 0));
        Enemigo hormiga = new Hormiga(new Posicion(1, 1));

        assertThrows(Exception.class, () -> tierra.insertarEnemigo(hormiga));
        assertThrows(Exception.class, () -> rocoso.insertarEnemigo(hormiga));

        assertDoesNotThrow(() -> pasarela.insertarEnemigo(hormiga));

    }

    @Test
    public void enemigoNoSePuedeCrearEnRocoso() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test 2: Enemigo no se puede crear en rocoso");
        Parcela pasarela = new Pasarela(new Posicion(0, 0), new Casilla());
        Parcela rocoso = new Rocoso(new Posicion(0, 0));
        Enemigo hormiga = new Hormiga(new Posicion(1, 1));

        assertThrows(Exception.class, () -> rocoso.insertarEnemigo(hormiga));

        assertDoesNotThrow(() -> pasarela.insertarEnemigo(hormiga));

    }

    @Test
    public void enemigoSePuedeCrearEnPasarela() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test 3: Enemigo se puede crear en pasarela");
        Parcela pasarela = new Pasarela(new Posicion(0, 0), new Casilla());
        Parcela tierra = new Tierra(new Posicion(0, 0));
        Parcela rocoso = new Rocoso(new Posicion(0, 0));

        Enemigo hormiga = new Hormiga(new Posicion(1, 1));

        assertThrows(Exception.class, () -> tierra.insertarEnemigo(hormiga));
        assertThrows(Exception.class, () -> rocoso.insertarEnemigo(hormiga));

        assertDoesNotThrow(() -> pasarela.insertarEnemigo(hormiga));

    }


    @Test
    public void araniaQueCaeEnTrampaArenosaSeMueveConLaMitadDeSuVelocidad() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test 4: Arania cae en trampa arenosa y su velocidad se reduce a la mitad correctamente");
        Cargador cargador = new CargadorJson();
        Mapa mapa = cargador.procesarMapa("data/jsonTests/mapa.json");
        Enemigo arania = EnemigoFactory.obtener("Arania");
        mapa.construirTrampa(new TrampaDeArena(), new Posicion(3, 2));
        mapa.actualizarEstadoDefensas();
        mapa.insertarEnemigo(arania);
        mapa.moverEnemigos();
        mapa.moverEnemigos();

        assertTrue(arania.estaEnRango(0, new Posicion(4, 2)));
    }

    @Test
    public void hormigaNoSeVeAfectadaPorTrampaArenosaCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test 4: Hormiga pasa por trampa arenosa correctamente con velocidad 1");
        Cargador cargador = new CargadorJson();
        Mapa mapa = cargador.procesarMapa("data/jsonTests/mapa.json");
        Enemigo hormiga = EnemigoFactory.obtener("Hormiga");
        mapa.construirTrampa(new TrampaDeArena(), new Posicion(2, 2));
        mapa.actualizarEstadoDefensas();
        mapa.insertarEnemigo(hormiga);
        mapa.moverEnemigos();
        mapa.moverEnemigos();
        mapa.moverEnemigos();

        assertTrue(hormiga.estaEnRango(0, new Posicion(4, 2)));
    }

    @Test
    public void topoNoSeVeAfectadoPorTrampaArenosaCorrectamenteConVelocidad1() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test 4: Hormiga pasa por trampa arenosa correctamente con velocidad 1");
        Cargador cargador = new CargadorJson();
        Mapa mapa = cargador.procesarMapa("data/jsonTests/mapa.json");
        Enemigo topo = EnemigoFactory.obtener("Topo");
        mapa.construirTrampa(new TrampaDeArena(), new Posicion(2, 2));
        mapa.actualizarEstadoDefensas();
        mapa.insertarEnemigo(topo);
        mapa.moverEnemigos();
        mapa.moverEnemigos();
        mapa.moverEnemigos();

        assertTrue(topo.estaEnRango(0, new Posicion(4, 2)));
    }

    @Test
    public void topoSeVeAfectadoPorTrampaArenosaCorrectamenteConVelocidad2() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test: Topo se ve afectado por arenosa correctamente con velocidad 2");
        Cargador cargador = new CargadorJson();
        Mapa mapa = cargador.procesarMapa("data/jsonTests/mapa.json");
        Enemigo topo = EnemigoFactory.obtener("Topo");
        mapa.construirTrampa(new TrampaDeArena(), new Posicion(7, 2));
        mapa.actualizarEstadoDefensas();
        mapa.insertarEnemigo(topo);
        mapa.moverEnemigos();
        mapa.moverEnemigos();
        mapa.moverEnemigos();
        mapa.moverEnemigos();
        mapa.moverEnemigos();
        mapa.moverEnemigos();
        assertTrue(topo.estaEnRango(0, new Posicion(7, 2)));
        mapa.moverEnemigos();
        assertTrue(topo.estaEnRango(0, new Posicion(7, 3)));

    }

    @Test
    public void lechuzaNoSeVeAfectadoPorTrampaArenosaCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test: Lechuza no trampa arenosa correctamente");
        Cargador cargador = new CargadorJson();
        Mapa mapa = cargador.procesarMapa("data/jsonTests/mapa.json");
        Defensa torre = new Torre(10, 3, 3, new EstadoDefensaCompleto(), "Torrecilla");
        mapa.construir(torre, new Posicion(1, 3));
        Enemigo lechuza = EnemigoFactory.obtener("Lechuza");
        mapa.construirTrampa(new TrampaDeArena(), new Posicion(2, 2));
        mapa.actualizarEstadoDefensas();
        mapa.insertarEnemigo(lechuza);
        mapa.defensasAtacar();
        mapa.moverEnemigos();
        mapa.moverEnemigos();


        assertTrue(lechuza.estaEnRango(0, new Posicion(9, 12)));
    }

    @Test
    public void trampaArenosaDuraLoQueTieneQueDurarCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test: Lechuza no trampa arenosa correctamente");
        Cargador cargador = new CargadorJson();
        Mapa mapa = cargador.procesarMapa("data/jsonTests/mapa.json");
        Enemigo enemigo = EnemigoFactory.obtener("arania");
        mapa.construirTrampa(new TrampaDeArena(), new Posicion(2, 2));
        mapa.actualizarEstadoDefensas();
        mapa.actualizarEstadoDefensas();
        mapa.actualizarEstadoDefensas();
        mapa.insertarEnemigo(enemigo);
        mapa.moverEnemigos();

        assertTrue(enemigo.estaEnRango(0, new Posicion(3, 2)));
    }

    @Test
    public void trampaArenosaSeCobraCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test: Trampa arenosa se cobra correctamente cuando se compra");
        TrampaDeArena trampa = new TrampaDeArena();
        Recurso recursoUsado = new Recurso(50);
        jugadorSingleton.actualizarEstado(10, recursoUsado, "Ariel");
        jugadorSingleton.comprar(trampa);
        assertEquals(25, recursoUsado.valorMonetario());
    }

    @Test
    public void trampaArenosaNoSePuedeComprarPorFondosLanzaExcepcion() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Parcela test: Trampa arenosa no se puede comprar por fondos lanza excepcion");
        TrampaDeArena trampa = new TrampaDeArena();
        Recurso recursoUsado = new Recurso(24);
        jugadorSingleton.actualizarEstado(10, recursoUsado, "Ariel");
        assertThrows(RuntimeException.class, () -> jugadorSingleton.comprar(trampa));
    }

    @Test
    public void trampaArenosaNoSePuedeComprarEnTierraLanzaExcepcionDevuelveFondos() {
        TrampaDeArena trampa = new TrampaDeArena();
        Recurso recursoUsado = new Recurso(25);
        jugadorSingleton.actualizarEstado(10, recursoUsado, "Ariel");
        Cargador cargador = new CargadorJson();
        Mapa mapa = cargador.procesarMapa(rutaJsonMapa);
        Partida partida = new Partida(jugadorSingleton, mapa);
        assertThrows(RuntimeException.class, () -> partida.construir(trampa, new Posicion(2, 1)));
        assertEquals(25, recursoUsado.valorMonetario());
    }

    @Test
    public void trampaArenosaNoSePuedeComprarEnRocosoLanzaExcepcionDevuelveFondos() {
        TrampaDeArena trampa = new TrampaDeArena();
        Recurso recursoUsado = new Recurso(25);
        jugadorSingleton.actualizarEstado(10, recursoUsado, "Ariel");
        Cargador cargador = new CargadorJson();
        Mapa mapa = cargador.procesarMapa(rutaJsonMapa);
        Partida partida = new Partida(jugadorSingleton, mapa);
        assertThrows(RuntimeException.class, () -> partida.construir(trampa, new Posicion(1, 1)));
        assertEquals(25, recursoUsado.valorMonetario());
    }

    @Test
    public void trampaArenosaNoSePuedeComprarPorMetaLanzaExcepcionDevuelveFondos() {
        TrampaDeArena trampa = new TrampaDeArena();
        Recurso recursoUsado = new Recurso(25);
        jugadorSingleton.actualizarEstado(10, recursoUsado, "Ariel");
        Cargador cargador = new CargadorJson();
        Mapa mapa = cargador.procesarMapa(rutaJsonMapa);
        Partida partida = new Partida(jugadorSingleton, mapa);
        assertThrows(RuntimeException.class, () -> partida.construir(trampa, new Posicion(11, 15)));
        assertEquals(25, recursoUsado.valorMonetario());
    }

    @Test
    public void trampaArenosaNoSePuedeComprarPorLargadaLanzaExcepcionDevuelveFondos() {
        TrampaDeArena trampa = new TrampaDeArena();
        Recurso recursoUsado = new Recurso(50);
        jugadorSingleton.actualizarEstado(10, recursoUsado, "Ariel");
        Cargador cargador = new CargadorJson();
        Mapa mapa = cargador.procesarMapa(rutaJsonMapa);
        Partida partida = new Partida(jugadorSingleton, mapa);
        assertThrows(RuntimeException.class, () -> partida.construir(trampa, new Posicion(1, 2)));
        assertEquals(50, recursoUsado.valorMonetario());
    }


}
