package edu.fiuba.algo3.Entregas;

import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Direccion.Derecha;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Enemigo.Arania;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Factory.DefensaFactory;
import edu.fiuba.algo3.modelo.Jugador.Contador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Partida.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Entrega1Test {

    @BeforeEach
    public void SetUp() {


        Jugador.getInstance().actualizarContador(new Contador());
        Jugador.getInstance().resetearDefensas();

    }




    public Mapa obtenerMapaGenerico() {
        Mapa mapa = new Mapa();
        Pasarela pasarela = new Pasarela(new Posicion(1,1), new Largada());
        pasarela.establecerDireccion(new Derecha());
        mapa.agregarParcela(pasarela);

        for(int i = 2; i < 7; i++){
            pasarela = new Pasarela(new Posicion(1,i), new Casilla());
            pasarela.establecerDireccion(new Derecha());
            mapa.agregarParcela(pasarela);
        }
        pasarela = new Pasarela(new Posicion(1,7), new Meta());
        pasarela.establecerDireccion(new Derecha());
        mapa.agregarParcela(pasarela);

        for(int j = 2; j < 8; j++) {
            for(int k = 1; k < 8; k++) {

                mapa.agregarParcela(new Tierra(new Posicion(j, k)));
            }
        }
        for(int h = 1; h < 8; h++) {
            mapa.agregarParcela(new Rocoso(new Posicion(7, h)));
        }

        return mapa;
    }
    
    @Test
    public void caso1jugadorEmpiezaConVidaYCreditosCorrespondientes() {
        Logger.getInstance().logEstado("\n--> Caso 1 jugador empieza con la vida y los créditos correspondientes.");
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, new Recurso(100), "Joaquín");

        assertEquals(10, jugador.obtenerVidaJugador());
        assertEquals(100, jugador.valorCreditos());
    }

    @Test
    public void caso2defensaTardaEnConstruirseLoQueDice(){
        Logger.getInstance().logEstado("\n--> Caso 2 Cada defensa tarde en construirse lo que dice que tarda.");
        Recurso recurso = new Recurso(100);
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, recurso, "Joaquín");
        Mapa mapa = obtenerMapaGenerico();
        Partida partida = new Partida(jugador,mapa);


        Defensa torrePlateada = (new DefensaFactory()).obtenerDefensa("Plateada", new Posicion(2,6));
        partida.insertarEnemigo(new Hormiga(new Posicion(1, 1)));
        partida.construir(torrePlateada); // 2 turnos para construirse
        partida.terminarTurno(); // arania en posicion (1,3)
        assertEquals(80, recurso.valorMonetario());
        partida.terminarTurno(); // (1,5) , torre no construida , arania vida = 2, torrePlateada construida
        assertEquals(80, recurso.valorMonetario());

        int monedasAntesDeMatar = recurso.valorMonetario();
        partida.terminarTurno(); // (1,6), muerte arania
        assertTrue((monedasAntesDeMatar < recurso.valorMonetario()));
    }

    @Test
    public void caso3defensaSeConstruyeSoloSiElJugadorTieneLosCreditosNecesarios() {
        Logger.getInstance().logEstado("\n--> Caso 3 Defensa solo se construye si el jugador tiene los créditos necesarios.");

        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, new Recurso(10), "Josesito");

        Defensa torreBlanca1 = (new DefensaFactory()).obtenerDefensa("Blanca", new Posicion(1,1));
        Defensa torreBlanca2 = (new DefensaFactory()).obtenerDefensa("Blanca", new Posicion(1,1));
        assertDoesNotThrow(() ->jugador.comprar(torreBlanca1));
        assertThrows(RecursosInsuficientesException.class, ()-> jugador.comprar(torreBlanca2));
    }

    @Test
    public void caso4DefensaSoloSePuedeConstruirSobreTierra() {
        Logger.getInstance().logEstado("\n--> Caso 4 Defensa solo se puedo contruir sobre tierra.");
        Tierra tierra = new Tierra(new Posicion(1,1));
        Rocoso rocoso = new Rocoso(new Posicion(2,2));

        assertDoesNotThrow(() ->
                tierra.insertarDefensa(new Torre(10, 1, 3, new EstadoDefensaIncompleto(2),
                        new Posicion(1,1),"Torre Blanca"),
                        new ArrayList<>()));
        assertThrows(Exception.class,() ->
                rocoso.insertarDefensa(new Torre(10, 1, 3, new EstadoDefensaIncompleto(2),
                        new Posicion(2,2), "Torre Blanca"),
                        new ArrayList<>()));
    }

    @Test
    public void caso5DefensasAtacanDentroDelRangoEsperado() {
        Logger.getInstance().logEstado("\n--> Caso 5 Defensa Atacan dentro del rango esperado.");
        /*Verificarquelasdefensas ataquen dentro del rango esperado (y verificar lo contrario*/

        Recurso recurso = new Recurso(100);
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(100, recurso, "Josesito");
        Mapa mapa = obtenerMapaGenerico();
        Partida partida = new Partida(jugador,mapa);

        Defensa torreBlanca1 = new Torre(10, 1, 3, new EstadoDefensaIncompleto(1), new Posicion(2,6),"Torre Blanca");
        partida.construir(torreBlanca1);

        //Enemigo hormiga = Enemigo.crearHormiga(1,null);
        Hormiga hormiga = new Hormiga(new Posicion(1,1));
        partida.insertarEnemigo(hormiga);
        partida.terminarTurno();
        partida.terminarTurno();


        assertEquals(90, recurso.valorMonetario());

        partida.terminarTurno();

        assertEquals(91, recurso.valorMonetario());
    }

    @Test
    public void caso6UnidadesEnemigasSonDaniadasAcordeAlAtaqueRecibido() {
        Logger.getInstance().logEstado("\n--> Caso 6 Las unidades enemigos son Dañadas acorde al daño recibido.");
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, new Recurso(10), "Ariel");
        Mapa mapa = obtenerMapaGenerico();
        Partida partida = new Partida(jugador,mapa);

        Defensa torreBlanca1 = new Torre(10, 1, 3, new EstadoDefensaIncompleto(1), new Posicion(2,2), "Torre Blanca");
        partida.construir(torreBlanca1);

        partida.insertarEnemigo(new Hormiga(new Posicion(1,1)));
        partida.terminarTurno(); // tarda 1 turno en construir la torre blanca
        partida.terminarTurno(); // Muere la hormiga
        partida.actualizarEstado();
        EstadoPartida estadoPartida = partida.estado();
        assertEquals(estadoPartida, new EstadoPartidaGanada()); // No hay enemigos en el mapa y el jugador tiene vida
    }

    @Test
    public void caso7LasUnidadesEnemigasSoloSeMuevenPorLaParcelaAutorizadaCorrectamente() {
        Logger.getInstance().logEstado("\n--> Caso 7 Defensa solo se puedo contruir sobre tierra.");
        Pasarela pasarela = new Pasarela(new Posicion(1,1), new Casilla());
        Tierra tierra = new Tierra(new Posicion(1,1));
        Rocoso rocoso = new Rocoso(new Posicion(1,1));

        assertThrows(Exception.class,() -> tierra.insertarEnemigo(new Hormiga(new Posicion(1,1))));
        assertThrows(Exception.class,() -> rocoso.insertarEnemigo(new Hormiga(new Posicion(1,1))));
        assertDoesNotThrow(() -> pasarela.insertarEnemigo(new Hormiga(new Posicion(1,1))));
    }

    @Test
    public void caso8JugadorMataUnidadYCobraCreditoCorrespondiente(){
        Logger.getInstance().logEstado("\n--> Caso 8 Jugador recibe los créditos correspondientes luego de matar a una unidad.");

        Recurso recurso = new Recurso(100);
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(100, recurso, "Josesito");
        /*100*/

        Mapa mapa = obtenerMapaGenerico();
        Partida partida = new Partida(jugador,mapa);

        Defensa torreBlanca1 = new Torre(10, 1, 3, new EstadoDefensaIncompleto(1), new Posicion(2,2), "Torre Blanca");
        partida.construir(torreBlanca1);
        // 100-10 = 90

        Hormiga hormiga = new Hormiga(new Posicion(1,1));
        partida.insertarEnemigo(hormiga);
        // 1 credito

        partida.terminarTurno();
        partida.terminarTurno();
        //90 + 1 = 91
        assertEquals(91, recurso.valorMonetario());
    }

    @Test
    public void test9AlPasarTurnoLasUnidadesEnemigasSeMovieronSegunSusCapacidadesCorrectamente() {
        Logger.getInstance().logEstado("\n--> Caso 9 Al pasar el turno, los enemigos se mueven lo que debían.");
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(10, new Recurso(10), "Josesito");

        Mapa mapa = obtenerMapaGenerico();
        Partida partida = new Partida(jugador,mapa);
        partida.insertarEnemigo(new Hormiga(new Posicion(1,1)));
        partida.insertarEnemigo(new Arania(new Posicion(1,1)));

        Defensa torreBlanca1 = new Torre( 10, 1, 3, new EstadoDefensaIncompleto(1), new Posicion(2,2), "Torre Blanca");
        partida.construir(torreBlanca1);

        /*rango de ataque de torre blanca = 0 - 4 fila ; 0 - 4 columna*/
        partida.terminarTurno(); // Torre construida

        partida.terminarTurno(); // enemigos en posicion (1,2) muere hormiga
        partida.actualizarEstado();
        partida.terminarTurno(); // enemigos en posicion (1, 4) (muere 1 arania)
        partida.actualizarEstado();


        EstadoPartida estadoPartida = partida.estado();
        assertEquals(estadoPartida, new EstadoPartidaGanada());
        assertEquals(10, jugador.obtenerVidaJugador());
    }

    @Test
    public void caso10SeGanaElJuegoMatandoTodosLosEnemigosCorrectamente(){
        Logger.getInstance().logEstado("\n--> Caso 10 Se gana el juego matando todos los enemigos.");
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(100, new Recurso(100), "Ariel");

        Mapa mapa = obtenerMapaGenerico();
        Partida partida = new Partida(jugador,mapa);

        Defensa torreBlanca1 = new Torre(10, 1, 3, new EstadoDefensaIncompleto(1), new Posicion(2,2),"Torre Blanca");
        partida.construir(torreBlanca1);

        Hormiga hormiga = new Hormiga(new Posicion(1,1));
        partida.insertarEnemigo(hormiga);

        Hormiga hormiga2 = new Hormiga(new Posicion(1,1));
        partida.insertarEnemigo(hormiga2);

        partida.terminarTurno(); // No ataca
        partida.actualizarEstado();
        partida.terminarTurno(); // Ataca una hormiga
        partida.actualizarEstado();

        EstadoPartida estadoPartida = partida.estado();
        assertEquals(estadoPartida, new EstadoPartidaGanada());
    }

    @Test
    public void caso11SeGanaElJuegoSobreviviendoAlDanioDeLosEnemigos() {
        Logger.getInstance().logEstado("\n--> Caso 11 Se gana el juego sobreviviendo al daño de los enemigos");
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(100, new Recurso(10), "Diego");

        Mapa mapa = obtenerMapaGenerico();
        Partida partida = new Partida(jugador,mapa);
        

        for(int i = 0; i < 10; i++) {
            partida.insertarEnemigo(new Hormiga(new Posicion(1,1)));
        }

        for(int i = 0; i < 7; i++){
            partida.terminarTurno();
            partida.actualizarEstado();
        }

        EstadoPartida estadoPartida = partida.estado();
        assertEquals(estadoPartida, new EstadoPartidaGanada());
        assertNotEquals(100, Jugador.getInstance().obtenerVidaJugador());
    }

    @Test
    public void caso12SePierdeElJuegoPorElDanioDeLosEnemigos() {
        Logger.getInstance().logEstado("\n--> Caso 12 Se pierde el juego por el daño de los enemigos.");
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarEstado(8, new Recurso(10), "Diego");
        Mapa mapa = obtenerMapaGenerico();
        Partida partida = new Partida(jugador,mapa);


        for(int i = 0; i < 14; i++) {
            partida.insertarEnemigo(new Hormiga(new Posicion(1,1)));
        }

        for(int i = 0; i < 10; i++){
            try {
                partida.terminarTurno();

            } catch (Exception e) {

            }
            partida.actualizarEstado();
        }
        EstadoPartida estadoPartida = partida.estado();
        assertEquals(estadoPartida, new EstadoPartidaPerdida());
    }
}
