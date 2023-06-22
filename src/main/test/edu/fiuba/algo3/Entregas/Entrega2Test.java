package edu.fiuba.algo3.Entregas;

import edu.fiuba.algo3.modelo.Cargador.CargadorJson;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Defensa.Torre;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Factory.EnemigoFactory;
import edu.fiuba.algo3.modelo.Jugador.Contador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Partida.*;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Entrega2Test {
    private final String rutaJsonEnemigos = "data/jsonTests/enemigos.json";
    private final String rutaJsonMapa = "data/jsonTests/mapa.json";
    private Jugador jugadorSingleton;

    @BeforeEach
    public void setup() {
        jugadorSingleton = Jugador.getInstance();
        jugadorSingleton.actualizarEstado(20, new Recurso(100), "PEPE");
        Jugador.getInstance().resetearDefensas();

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
        Logger.getInstance().logEstado("\n--> Caso 13 Verificar formato json de enemigos.");

        CargadorJson cargadorJson = new CargadorJson();

        assertDoesNotThrow(() -> cargadorJson.archivoEsCorrecto(rutaJsonEnemigos, rutaJsonMapa));
    }

    @Test
    public void caso14VerificarElFormatoValidoDelJsonMapa() {
        //Verificar el formato valido del JSON MAPA.
        Logger.getInstance().logEstado("\n--> Caso 14 Verificar formato json de mapa.");

        CargadorJson cargadorJson = new CargadorJson();

        assertDoesNotThrow(() -> cargadorJson.archivoEsCorrecto(rutaJsonEnemigos, rutaJsonMapa));
    }

    @Test
    public void caso15unidadesCargadasAlMapaSonCorrectasMatandoAlJugadorDespuesDeVariosTurnos() {
        Logger.getInstance().logEstado("\n--> Caso 15 Unidades cargadas al mapa json son correctas matando al jugador despues de varios turnos.");
        Juego juego = new Juego();
        juego.cargarEnemigosYMapa(rutaJsonEnemigos, rutaJsonMapa);

        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(20, new Recurso(100), "#Singleton");

        juego.cargarJugador(jugador);

        juego.iniciar();

        for(int i = 0; i < 50; i++) { // Juego termina, danio causado al jugador = 22
            try {
                juego.terminarTurno();

            } catch (Exception e) {

            }
        }

        EstadoPartida estadoPartida = juego.estado();
        assertEquals(estadoPartida, new EstadoPartidaPerdida());
    }

    @Test
    public void caso16unidadesCargadasAlMapaSonCorrectasDandoCreditosAlJugador() {
        Logger.getInstance().logEstado("\n--> Caso 16 Unidades cargadas al mapa json son correctas dando créditos al jugador.");
        Juego juego = new Juego();
        juego.cargarEnemigosYMapa(rutaJsonEnemigos, rutaJsonMapa);
        Recurso recurso = new Recurso(60);
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(20, recurso, "#Singleton");
        jugador.actualizarContador(new Contador());
        juego.cargarJugador(jugador);
        juego.iniciar();

        Torre torrePlateada = new Torre(20,2,5,new EstadoDefensaIncompleto(2), "Torre Plateada");
        Torre torrePlateadaDos = new Torre(20,2,5,new EstadoDefensaIncompleto(2), "Torre Plateada");
        Torre torrePlateadaTres = new Torre(20,2,5,new EstadoDefensaIncompleto(2), "Torre Plateada");

        juego.construir(torrePlateada, new Posicion(3,3));
        juego.construir(torrePlateadaDos, new Posicion(6,1));
        juego.construir(torrePlateadaTres, new Posicion(12,10));

        for(int i = 0; i < 50; i++) { // Juego termina, danio causado al jugador = 22
            try {
                juego.terminarTurno();
            } catch (Exception e) {

            }
        }

        //11 hormigas 10 aranias
        int contadorMuertesArania = jugador.obtenerMuertes(EnemigoFactory.obtener("arana"));
        int contadorMuertesHormiga = jugador.obtenerMuertes(EnemigoFactory.obtener("hormiga"));

        int muertesAraniaEsperada = 10;
        int muertesHormigaEsperada = 11;

        EstadoPartida estadoPartida = juego.estado();
        assertEquals(new EstadoPartidaGanada(), estadoPartida);
        assertTrue(recurso.valorMonetario() > 0);
        assertEquals(20, jugador.obtenerVidaJugador());

        assertEquals(muertesAraniaEsperada, contadorMuertesArania);
        assertEquals(muertesHormigaEsperada, contadorMuertesHormiga);

    }

    @Test
    public void caso16UnidadesDelMapaSonCargadasCorrectamente() {
        Logger.getInstance().logEstado("\n--> Caso 16 Unidades cargadas al mapa json son correctas dando créditos al jugador.");
        Juego juego = new Juego();
        juego.cargarEnemigosYMapa(rutaJsonEnemigos, rutaJsonMapa);
        Recurso recurso = new Recurso(20);
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(20, recurso, "#Singleton");
        jugador.actualizarContador(new Contador());
        juego.cargarJugador(jugador);
        juego.iniciar();

        Torre torrePlateada1 = new Torre(20,2,5,new EstadoDefensaIncompleto(2), "Torre Plateada");
        Torre torrePlateada2 = new Torre(20,2,5,new EstadoDefensaIncompleto(2), "Torre Plateada");


        assertThrows(Exception.class, () -> juego.construir(torrePlateada1, new Posicion(1, 1))); //reviso que construir tire excepcion en la parcela rocoso

        juego.construir(torrePlateada2, new Posicion(3, 1));

        for (int i = 0; i < 4 ; i++) {
            try {
                juego.terminarTurno();

            } catch (Exception e) {

            }
        }

        int muertesHormigaEsperada = 4;
        int contadorMuertesHormiga = Jugador.getInstance().obtenerMuertes(EnemigoFactory.obtener("hormiga"));

        assertEquals(muertesHormigaEsperada, contadorMuertesHormiga); // reviso si la torre ataca de manera correcta, lo que quiere decir que los enemigos aparecieron donde deberian y la torre fue construida en tierra
    }


    @Test
    public void caso17JuegoSeCreaAcordeConAmbosJson() {
        Logger.getInstance().logEstado("\n--> Caso 17 Juego se crea acorde a ambos json.");
        Juego juego = new Juego();
        juego.cargarEnemigosYMapa(rutaJsonEnemigos, rutaJsonMapa);
        Recurso recurso = new Recurso(20);
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(20, recurso, "#Singleton");
        jugador.actualizarContador(new Contador());
        juego.cargarJugador(jugador);
        juego.iniciar();

        Torre torrePlateada1 = new Torre(20,2,5,new EstadoDefensaIncompleto(2), "Torre Plateada");
        Torre torrePlateada2 = new Torre(20,2,5,new EstadoDefensaIncompleto(2), "Torre Plateada");


        assertThrows(Exception.class, () -> juego.construir(torrePlateada1, new Posicion(15, 2))); //reviso que construir tire excepcion en la parcela rocoso

        juego.construir(torrePlateada2, new Posicion(10, 12));

        for (int i = 0; i < 144 ; i++) {
            try {
                juego.terminarTurno();

            } catch (Exception e) {

            }
        }

        int muertesHormigaEsperada = 11;
        int muertesAraniaEsperada = 10;

        int contadorMuertesHormiga = Jugador.getInstance().obtenerMuertes(EnemigoFactory.obtener("hormiga"));
        int contadorMuertesArania = Jugador.getInstance().obtenerMuertes(EnemigoFactory.obtener("Arania"));
        int vidaEsperada = 20;
        assertEquals(vidaEsperada, jugadorSingleton.obtenerVidaJugador());
        assertEquals(muertesHormigaEsperada, contadorMuertesHormiga); // reviso si la torre ataca de manera correcta, lo que quiere decir que los enemigos aparecieron donde y cuando deberian y
        assertEquals(muertesAraniaEsperada, contadorMuertesArania);   // la torre fue construida en tierra
    }


    @Test
    public void caso18JugadorGanaUnaPartidaSimulada(){
        Logger.getInstance().logEstado("\n--> Caso 18 Jugador gana una partida simulada.");
        Juego juego = new Juego();
        juego.cargarEnemigosYMapa(rutaJsonEnemigos, rutaJsonMapa);
        Recurso recurso = new Recurso(20);
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(20, recurso, "Messi");
        juego.cargarJugador(jugador);
        juego.iniciar();

        Torre torreBlanca = new Torre(10, 1, 3, new EstadoDefensaIncompleto(1), "Torre Blanca");
        juego.construir(torreBlanca, new Posicion(3, 3));

        for (int i = 0; i < 29 ; i++) {
            try {
                juego.terminarTurno();

            } catch (Exception e) {

            }
        }

        EstadoPartida estadoPartida = juego.estado();
        assertEquals(estadoPartida, new EstadoPartidaGanada());
    }

    @Test
    public void caso19JugadorPierdeUnaPartidaSimulada(){
        Logger.getInstance().logEstado("\n--> Caso 19 Jugador pierde una partida simulada.");
        Juego juego = new Juego();
        juego.cargarEnemigosYMapa(rutaJsonEnemigos, rutaJsonMapa);
        Recurso recurso = new Recurso(20);
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(20, recurso, "Messi");
        juego.cargarJugador(jugador);
        juego.iniciar();


        for (int i = 0; i < 30 ; i++) {
            try{
                juego.terminarTurno();

            } catch (Exception e) {

            }
        }

        EstadoPartida estadoPartida = juego.estado();
        assertNotEquals(estadoPartida, new EstadoPartidaSigueJugando());
        assertEquals(estadoPartida, new EstadoPartidaPerdida());

    }
}