package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.CargadorJson;
import edu.fiuba.algo3.JuegoFacade;
import edu.fiuba.algo3.modelo.Contador;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaCompleto;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Defensa.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensa.TorrePlateada;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Partida.DatosJugador;
import edu.fiuba.algo3.modelo.Partida.EstadoPartida;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recurso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsosTest {
    private final String rutaJsonEnemigos = "data/jsonTests/enemigos.json";
    private final String rutaJsonMapa = "data/jsonTests/mapa.json";
    private DatosJugador datosPartidaSingleton;

    @BeforeEach
    public void setup() {
        datosPartidaSingleton = DatosJugador.getInstance();
        datosPartidaSingleton.actualizarEstado(20, new Recurso(100), new Contador());
    }

    @Test
    public void caso13VerificarElFormatoValidoDelJsonEnemigos() {
        //Verificar el formato valido del JSON enemigos.
        // algo3_tp2_fiuba\src\main\test\edu\fiuba\algo3\entrega_2
            //algo3_tp2_fiuba
            //├── src
            //│   └── main
            //│        └── test
            //│             └── edu
            //│                 └── fiuba
            //│                     └── algo3
            //│                         └── entrega_2
            //│                             └── CasosDeUso,java
            //│
            //└── data
            //│    └──jsonTests
            //│         └──enemigos.json


        CargadorJson cargadorJson = new CargadorJson();

        assertTrue(cargadorJson.archivoEsCorrecto(rutaJsonEnemigos, rutaJsonMapa));
    }

    @Test
    public void caso14VerificarElFormatoValidoDelJsonMapa() {
        //Verificar el formato valido del JSON MAPA.

        CargadorJson cargadorJson = new CargadorJson();
        assertTrue(cargadorJson.archivoEsCorrecto(rutaJsonEnemigos, rutaJsonMapa));
    }

    @Test
    public void caso15unidadesCargadasAlMapaSonCorrectasMatandoAlJugadorDespuesDeVariosTurnos() {

        JuegoFacade juego = new JuegoFacade();
        juego.cargarConJson(rutaJsonEnemigos, rutaJsonMapa);

        Jugador jugador = new Jugador(new Recurso(100), 20, "#Singleton");
        juego.cargarJugador(jugador);

        juego.iniciar();

        for(int i = 0; i < 50; i++) { // Juego termina, danio causado al jugador = 22
            juego.terminarTurno();
        }

        EstadoPartida estadoPartida = juego.estado();
        assertTrue(estadoPartida.perdio());
    }

    @Test
    public void caso15unidadesCargadasAlMapaSonCorrectasDandoCreditosAlJugador() {
        JuegoFacade juego = new JuegoFacade();
        juego.cargarConJson(rutaJsonEnemigos, rutaJsonMapa);
        Recurso recurso = new Recurso(60);
        Jugador jugador = new Jugador(recurso, 20, "#Singleton");
        juego.cargarJugador(jugador);
        juego.iniciar();

        TorrePlateada torrePlateada = new TorrePlateada(20,2,5,new EstadoDefensaIncompleto(2));
        TorrePlateada torrePlateadaDos = new TorrePlateada(20,2,5,new EstadoDefensaIncompleto(2));
        TorrePlateada torrePlateadaTres = new TorrePlateada(20,2,5,new EstadoDefensaIncompleto(2));

        juego.construir(torrePlateada, new Posicion(3,3));
        juego.construir(torrePlateadaDos, new Posicion(8,3));
        juego.construir(torrePlateadaTres, new Posicion(12,10));

        for(int i = 0; i < 50; i++) { // Juego termina, danio causado al jugador = 22
            juego.terminarTurno();
        }

        //11 hormigas 10 aranias
        DatosJugador datosJugador = DatosJugador.getInstance();
        int contadorMuertesArania = datosJugador.obtenerMuertesArania();
        int contadorMuertesHormiga = datosJugador.obtenerMuertesHormigas();

        int muertesAraniaEsperada = 10;
        int muertesHormigaEsperada = 11;

        EstadoPartida estadoPartida = juego.estado();
        assertTrue(estadoPartida.gano());
        assertTrue(recurso.valorMonetario() > 0);
        assertEquals(20, datosJugador.obtenerVidaJugador());

        assertEquals(muertesAraniaEsperada, contadorMuertesArania);
        assertEquals(muertesHormigaEsperada, contadorMuertesHormiga);

    }

    @Test
    public void caso16UnidadesDelMapaSonCargadasCorrectamente() {
        JuegoFacade juego = new JuegoFacade();
        juego.cargarConJson(rutaJsonEnemigos, rutaJsonMapa);
        Recurso recurso = new Recurso(20);
        Jugador jugador = new Jugador(recurso, 20, "#Singleton");
        juego.cargarJugador(jugador);
        juego.iniciar();

        TorrePlateada torrePlateada1 = new TorrePlateada(20,2,5,new EstadoDefensaIncompleto(2));
        TorrePlateada torrePlateada2 = new TorrePlateada(20,2,5,new EstadoDefensaIncompleto(2));


        assertThrows(Exception.class, () -> juego.construir(torrePlateada1, new Posicion(1, 1))); //reviso que construir tire excepcion en la parcela rocoso

        juego.construir(torrePlateada2, new Posicion(10, 12));

        for (int i = 0; i < 3 ; i++) {
            juego.terminarTurno();
        }

        int muertesHormigaEsperada = 4;
        int contadorMuertesHormiga = DatosJugador.getInstance().obtenerMuertesHormigas();

        assertEquals(muertesHormigaEsperada, contadorMuertesHormiga); // reviso si la torre ataca de manera correcta, lo que quiere decir que los enemigos aparecieron donde deberian y la torre fue construida en tierra
    }


    @Test
    public void caso17JuegoSeCreaAcordeConAmbosJson() {
        JuegoFacade juego = new JuegoFacade();
        juego.cargarConJson(rutaJsonEnemigos, rutaJsonMapa);
        Recurso recurso = new Recurso(20);
        Jugador jugador = new Jugador(recurso, 20, "#Singleton");
        juego.cargarJugador(jugador);
        juego.iniciar();

        TorrePlateada torrePlateada1 = new TorrePlateada(20,2,5,new EstadoDefensaIncompleto(2));
        TorrePlateada torrePlateada2 = new TorrePlateada(20,2,5,new EstadoDefensaIncompleto(2));


        assertThrows(Exception.class, () -> juego.construir(torrePlateada1, new Posicion(15, 2))); //reviso que construir tire excepcion en la parcela rocoso

        juego.construir(torrePlateada2, new Posicion(10, 12));

        for (int i = 0; i < 144 ; i++) {
            juego.terminarTurno();
        }

        int muertesHormigaEsperada = 11;
        int muertesAraniaEsperada = 10;

        int contadorMuertesHormiga = DatosJugador.getInstance().obtenerMuertesHormigas();
        int contadorMuertesArania = DatosJugador.getInstance().obtenerMuertesArania();
        int vidaEsperada = 20;
        assertEquals(vidaEsperada, datosPartidaSingleton.obtenerVidaJugador());
        assertEquals(muertesHormigaEsperada, contadorMuertesHormiga); // reviso si la torre ataca de manera correcta, lo que quiere decir que los enemigos aparecieron donde y cuando deberian y
        assertEquals(muertesAraniaEsperada, contadorMuertesArania);   // la torre fue construida en tierra

    }


    @Test
    public void caso18JugadorGanaUnaPartidaSimulada(){
        JuegoFacade juego = new JuegoFacade();
        juego.cargarConJson(rutaJsonEnemigos, rutaJsonMapa);
        Recurso recurso = new Recurso(20);
        Jugador jugador = new Jugador(recurso, 20, "Messi");
        juego.cargarJugador(jugador);
        juego.iniciar();

        TorreBlanca torreBlanca = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(1));
        juego.construir(torreBlanca, new Posicion(3, 3));

        for (int i = 0; i < 29 ; i++) {
            juego.terminarTurno();
        }

        EstadoPartida estadoPartida = juego.estado();
        assertTrue(estadoPartida.sigueJugando());

        juego.terminarTurno();

        estadoPartida = juego.estado();
        assertTrue(estadoPartida.gano());
    }

    @Test
    public void caso19JugadorPierdeUnaPartidaSimulada(){
        JuegoFacade juego = new JuegoFacade();
        juego.cargarConJson(rutaJsonEnemigos, rutaJsonMapa);
        Recurso recurso = new Recurso(20);
        Jugador jugador = new Jugador(recurso, 20, "Messi");
        juego.cargarJugador(jugador);
        juego.iniciar();


        for (int i = 0; i < 30 ; i++) {
            juego.terminarTurno();
        }

        EstadoPartida estadoPartida = juego.estado();
        assertFalse(estadoPartida.sigueJugando());
        assertTrue(estadoPartida.perdio());

    }
}