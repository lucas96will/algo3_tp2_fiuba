package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Enemigo.Arania;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Partida.DatosJugador;
import edu.fiuba.algo3.modelo.Partida.EstadoPartida;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Partida.Partida;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsosTest {
    @Test
    public void caso1jugadorEmpiezaConVidaYCreditosCorrespondientes() {
        Logger.getInstance().logEstado("\n--> Caso 1 jugador empieza con la vida y los créditos correspondientes.");
        Jugador jugador = new Jugador(new Recurso(100), 10, "Joaquin");

        assertTrue(jugador.estaIntacto());
        assertEquals(100, jugador.valorCreditos());
    }

    @Test
    public void caso2defensaTardaEnConstruirseLoQueDice(){
        Logger.getInstance().logEstado("\n--> Caso 2 Cada defensa tarde en construirse lo que dice que tarda.");
        Partida partida = new Partida();
        Recurso recurso = new Recurso(100);
        Jugador jugador = new Jugador(recurso, 10, "Joaquin");
        Mapa mapa = Mapa.generico();
        partida.crearPartida(jugador,mapa);


        Defensa torrePlateada = new TorrePlateada(20, 2,5, new EstadoDefensaIncompleto(2));
        partida.insertarEnemigo(new Hormiga(2,2,2,2,2,new Posicion(1, 1)));
        partida.construir(torrePlateada, new Posicion(2,6)); // 2 turnos para construirse
        partida.terminarTurno(); // arania en posicion (1,3)
        assertEquals(80, recurso.valorMonetario());
        partida.terminarTurno(); // (1,5) , torre no construida , arania vida = 2, torrePlateada construida
        assertEquals(80, recurso.valorMonetario());

        partida.terminarTurno(); // (1,6), muerte arania
        assertEquals(82, recurso.valorMonetario());
    }

    @Test
    public void caso3defensaSeConstruyeSoloSiElJugadorTieneLosCreditosNecesarios() {
        Logger.getInstance().logEstado("\n--> Caso 3 Defensa solo se construye si el jugador tiene los créditos necesarios.");
        Jugador jugador = new Jugador(new Recurso(10), 10, "Josecito");

        Defensa torreBlanca1 = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(2));
        Defensa torreBlanca2 = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(2));
        assertTrue(jugador.comprarDefensa(torreBlanca1));
        assertFalse(jugador.comprarDefensa(torreBlanca2));
    }

    @Test
    public void caso4DefensaSoloSePuedeConstruirSobreTierra() {
        Logger.getInstance().logEstado("\n--> Caso 4 Defensa solo se puedo contruir sobre tierra.");
        Tierra tierra = new Tierra(new Posicion(1,1));
        Rocoso rocoso = new Rocoso(new Posicion(2,2));

        assertDoesNotThrow(() -> tierra.insertarDefensa(new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(2))));
        assertThrows(Exception.class,() -> rocoso.insertarDefensa(new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(2))));
    }

    @Test
    public void caso5DefensasAtacanDentroDelRangoEsperado() {
        Logger.getInstance().logEstado("\n--> Caso 5 Defensa Atacan dentro del rango esperado.");
        /*Verificarquelasdefensas ataquen dentro del rango esperado (y verificar lo contrario*/

        Partida partida = new Partida();
        Recurso recurso = new Recurso(100);
        Jugador jugador = new Jugador(recurso, 100, "Josecito");
        Mapa mapa = Mapa.generico();
        partida.crearPartida(jugador,mapa);

        Defensa torreBlanca1 = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(1));
        partida.construir(torreBlanca1, new Posicion(2,6));

        //Enemigo hormiga = Enemigo.crearHormiga(1,null);
        Hormiga hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));
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
        Partida partida = new Partida();
        Jugador jugador = new Jugador(new Recurso(10), 10, "Ariel");
        Mapa mapa = Mapa.generico();
        partida.crearPartida(jugador,mapa);

        Defensa torreBlanca1 = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(1));
        partida.construir(torreBlanca1, new Posicion(2,2));

        partida.insertarEnemigo(new Hormiga(1,1,1,1,10, new Posicion(1,1)));
        partida.terminarTurno(); // tarda 1 turno en construir la torre blanca
        partida.terminarTurno(); // Muere la hormiga
        EstadoPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano()); // No hay enemigos en el mapa y el jugador tiene vida

        partida.insertarEnemigo(new Arania(2,2,2,2,2,  new Posicion(1,1))); // arania con 2 de vida
        partida.terminarTurno(); // se dania a la arania, queda con 1 de vida

        condicionPartida = partida.estado();
        assertTrue(condicionPartida.sigueJugando());

        partida.terminarTurno(); // muere la arania

        condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano());
    }

    @Test
    public void caso7LasUnidadesEnemigasSoloSeMuevenPorLaParcelaAutorizadaCorrectamente() {
        Logger.getInstance().logEstado("\n--> Caso 7 Defensa solo se puedo contruir sobre tierra.");
        Pasarela pasarela = new Casilla(new Posicion(1,1));
        Tierra tierra = new Tierra(new Posicion(1,1));
        Rocoso rocoso = new Rocoso(new Posicion(1,1));

        assertThrows(Exception.class,() -> tierra.insertarEnemigo(new Hormiga(1,1,1,1,10, new Posicion(1,1))));
        assertThrows(Exception.class,() -> rocoso.insertarEnemigo(new Hormiga(1,1,1,1,10, new Posicion(1,1))));
        assertDoesNotThrow(() -> pasarela.insertarEnemigo(new Hormiga(1,1,1,1,10, new Posicion(1,1))));
    }

    @Test
    public void caso8JugadorMataUnidadYCobraCreditoCorrespondiente(){
        Logger.getInstance().logEstado("\n--> Caso 8 Jugador recibe los créditos correspondientes luego de matar a una unidad.");

        Partida partida = new Partida();
        Recurso recurso = new Recurso(100);
        Jugador jugador = new Jugador(recurso, 100, "Josecito");
        /*100*/

        Mapa mapa = Mapa.generico();
        partida.crearPartida(jugador,mapa);

        Defensa torreBlanca1 = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(1));
        partida.construir(torreBlanca1, new Posicion(2,2));
        // 100-10 = 90

        Hormiga hormiga = new Hormiga(1,1,1,1,1, new Posicion(1,1));
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
        Partida partida = new Partida();
        Jugador jugador = new Jugador(new Recurso(10), 10, "Ariel");

        Mapa mapa = Mapa.generico();
        partida.crearPartida(jugador,mapa);

        Defensa torreBlanca1 = new TorreBlanca( 10, 1, 3, new EstadoDefensaIncompleto(1));
        partida.construir(torreBlanca1, new Posicion(2,2));

        /*rango de ataque de torre blanca = 0 - 4 fila ; 0 - 4 columna*/
        partida.terminarTurno(); // Torre construida
        partida.insertarEnemigo(new Hormiga(1,1,1,2,1, new Posicion(1,1)));
        partida.insertarEnemigo(new Arania(2,2,2,2,2, new Posicion(1,1)));

        partida.terminarTurno(); // enemigos en posicion (1,2) muere hormiga
        partida.terminarTurno(); // enemigos en posicion (1, 4) (muere 1 arania)
        partida.terminarTurno(); // enemigo en posicion (1, 6)
        partida.terminarTurno(); // enemigo paso la meta que estaba en (0, 8)

        EstadoPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano());
        assertTrue(jugador.estaIntacto());
    }

    @Test
    public void caso10SeGanaElJuegoMatandoTodosLosEnemigosCorrectamente(){
        Logger.getInstance().logEstado("\n--> Caso 10 Se gana el juego matando todos los enemigos.");
        Partida partida = new Partida();
        Jugador jugador = new Jugador(new Recurso(100), 100, "Ariel");

        Mapa mapa = Mapa.generico();
        partida.crearPartida(jugador,mapa);

        Defensa torreBlanca1 = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(1));
        partida.construir(torreBlanca1, new Posicion(2,2));

        Hormiga hormiga = new Hormiga(1,1,1,1,10, new Posicion(1,1));
        partida.insertarEnemigo(hormiga);

        Hormiga hormiga2 = new Hormiga(1,1,1,1,10, new Posicion(1,1));
        partida.insertarEnemigo(hormiga2);

        partida.terminarTurno(); // No ataca
        partida.terminarTurno(); // Ataca una hormiga
        partida.terminarTurno(); // Ataca una segunda hormiga

        EstadoPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano());
    }

    @Test
    public void caso11SeGanaElJuegoSobreviviendoAlDanioDeLosEnemigos() {
        Logger.getInstance().logEstado("\n--> Caso 11 Se gana el juego sobreviviendo al daño de los enemigos");
        Partida partida = new Partida();
        Jugador jugador = new Jugador(new Recurso(10), 100, "Ariel");

        Mapa mapa = new Mapa();
        mapa.crearMapaGenerico();
        partida.crearPartida(jugador,mapa);

        Defensa torreBlanca1 = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(2));
        partida.construir(torreBlanca1, new Posicion(2,2));

        for(int i = 0; i < 10; i++) {
            partida.insertarEnemigo(new Hormiga(20,1,1,1,10, new Posicion(1,1)));
        }

        for(int i = 0; i < 8; i++){
            partida.terminarTurno();
        }

        EstadoPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano());
        //assertFalse(jugador.estaIntacto());
        assertNotEquals(100, DatosJugador.getInstance().obtenerVidaJugador());
    }

    @Test
    public void caso12SePierdeElJuegoPorElDanioDeLosEnemigos() {
        Logger.getInstance().logEstado("\n--> Caso 12 Se pierde el juego por el daño de los enemigos.");
        Partida partida = new Partida();
        Jugador jugador = new Jugador(new Recurso(10), 8, "Ariel");
        Mapa mapa = Mapa.generico();
        partida.crearPartida(jugador,mapa);

      // Defensa torreBlanca1 = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(2));
       // partida.construir(torreBlanca1, new Posicion(2,2));

        for(int i = 0; i < 14; i++) {
            partida.insertarEnemigo(new Hormiga(10,1,1,1,10, new Posicion(1,1)));
        }

        for(int i = 0; i < 10; i++){
            partida.terminarTurno();
        }
        EstadoPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.perdio());
    }
}
