package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensa.TorrePlateada;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsosTest {
    @Test
    public void caso1jugadorEmpiezaConVidaYCreditosCorrespondientes() {
        //Verificarquejugadorempiezaconlavida ylos créditos correspondientes.
        /*
        Mapa mapa = new Mapa(7);
        mapa.crearMapaGenerico(); // Línea recta con tierra construible en todas sus posiciones abajo.

        Jugador jugador = Jugador.crearJugadorBase("Joaquin"); //100 creditos y 10 puntos de vida

        assertEquals();
        Discutir sobre que modelo implementar
        */
        //-------------------------------------------------------
        Partida partida = new Partida();
        Jugador jugador = Jugador.crearJugadorBase("Joaquin");
        partida.crearPartidaGenerica(jugador);

        partida.comenzar();

        assertTrue(partida.jugadorTieneTodaLaVidaYMaximosCreditos());
        assertFalse(partida.terminarPartida());
    }

    @Test
    public void caso2defensaTardaEnConstruirseLoQueDice(){
        //Verificarquecadadefensatardeenconstruirse lo que dice que tarda y que recién están
        // “operativas” cuando ya se terminaron de construir.
        Partida partida = new Partida();
        Jugador jugador = Jugador.crearJugadorBase("Joaquin");
        partida.crearPartidaGenerica(jugador);

        partida.comenzar();

        Defensa torreBlanca = new TorreBlanca(1,3);
        Defensa torrePlateada = new TorrePlateada(1,6);
        partida.construir(torreBlanca);
        partida.construir(torrePlateada);

        partida.terminarTurno();

        /*Torre blanca ya terminada*/
        assertTrue(partida.construccionTerminadaEn(1,3));

        /*Torre plateada no se termino (2 turnos)*/
        assertFalse(partida.construccionTerminadaEn(1,6));

        partida.terminarTurno();

        assertTrue(partida.construccionTerminadaEn(1,6));

    }

    @Test
    public void caso3defensaSeConstruyeSoloSiElJugadorTieneLosCreditosNecesarios() {

        Partida partida = new Partida();
        Jugador jugador = new Jugador(10, 100, "Josecito");
        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 5);
        Defensa torreBlanca2 = new TorreBlanca(1, 6);
        assertTrue(partida.construir(torreBlanca1));
        assertFalse(partida.construir(torreBlanca2));


    }

    @Test
    public void caso4DefensaSoloSePuedeConstruirSobreTierra() {
        Partida partida = new Partida();
        Jugador jugador = Jugador.crearJugadorBase("Joaquin");
        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 5);
        Defensa torreBlanca2 = new TorreBlanca(6, 5);
        assertTrue(partida.construir(torreBlanca1));
        assertFalse(partida.construir(torreBlanca2)); //construyo sobre rocoso

    }

    @Test
    public void caso5DefensasAtacanDentroDelRangoEsperado() {
        /*Verificarquelasdefensas ataquen dentro del rango esperado (y verificar lo contrario*/

        Partida partida = new Partida();
        Jugador jugador = new Jugador(10, 100, "Josecito");
        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 1);
        partida.construir(torreBlanca1);

        Enemigo hormiga = Enemigo.crearHormiga(1);
        partida.insertarEnemigo(hormiga);

        for(int i = 0; i < 8; i++){
            partida.terminarTurno();
        }

        assertTrue(partida.jugadorTieneTodaLaVidaYMaximosCreditos()); //hormiga no llego al final :D
    }

    @Test
    public void caso8JugadorMataUnidadYCobraCreditoCorrespondiente(){

        Partida partida = new Partida();
        Jugador jugador = new Jugador(100, 100, "Josecito");
        /*100*/

        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 1);
        partida.construir(torreBlanca1);
        // 100-10 = 90

        Enemigo hormiga = Enemigo.crearHormiga(1);
        partida.insertarEnemigo(hormiga);
        // 1 credito

        partida.terminarTurno();
        partida.terminarTurno();
        //90 + 1 = 91
        assertTrue(partida.jugadorTieneTantosCreditos(91));
    }

    @Test
    public void caso10SeGanaElJuegoMatandoTodosLosEnemigosCorrectamente(){

        Partida partida = new Partida();
        Jugador jugador = new Jugador(100, 100, "Ariel");

        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 1);
        partida.construir(torreBlanca1);

        Enemigo hormiga = Enemigo.crearHormiga(1);
        partida.insertarEnemigo(hormiga);

        Enemigo hormiga2 = Enemigo.crearHormiga(1);
        partida.insertarEnemigo(hormiga2);

        partida.terminarTurno(); // No ataca
        partida.terminarTurno(); // Ataca una hormiga
        partida.terminarTurno(); // Ataca una segunda hormiga

        CondicionPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano());
    }

    @Test
    public void caso11SeGanaElJuegoSobreviviendoAlDanioDeLosEnemigos() {
        Partida partida = new Partida();
        Jugador jugador = new Jugador(10, 100, "Ariel");

        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 1);
        partida.construir(torreBlanca1);

        for(int i = 0; i < 10; i++) {
            partida.insertarEnemigo(Enemigo.crearHormiga(1));
        }

        for(int i = 0; i < 10; i++){
            partida.terminarTurno();
        }

        CondicionPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano());
        assertFalse(partida.jugadorTieneTodaLaVidaYMaximosCreditos());
    }

    @Test
    public void caso12SePierdeElJuegoPorElDanioDeLosEnemigos() {
        Partida partida = new Partida();
        Jugador jugador = new Jugador(10, 10, "Ariel");

        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 1);
        partida.construir(torreBlanca1);

        for(int i = 0; i < 14; i++) {
            partida.insertarEnemigo(Enemigo.crearHormiga(1));
        }

        for(int i = 0; i < 10; i++){
            partida.terminarTurno();
        }
        CondicionPartida condicionPartida = partida.estado();

        assertTrue(condicionPartida.perdio());
    }

    @Test
    public void caso6UnidadesEnemigasSonDaniadasAcordeAlAtaqueRecibido() {
        Partida partida = new Partida();
        Jugador jugador = new Jugador(10, 10, "Ariel");

        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 1);
        partida.construir(torreBlanca1);

        partida.insertarEnemigo(Enemigo.crearHormiga(1));
        partida.terminarTurno(); // tarda 1 turno en construir la torre blanca
        partida.terminarTurno(); // Muere la hormiga
        CondicionPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano()); // No hay enemigos en el mapa y el jugador tiene vida

        partida.insertarEnemigo(Enemigo.crearArania(2)); // arania con 2 de vida
        partida.terminarTurno(); //
        partida.terminarTurno(); // se dania a la arania, queda con 1 de vida

        condicionPartida = partida.estado();
        assertTrue(condicionPartida.sigueJugando());

        partida.terminarTurno(); // muere la arania

        condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano());

    }

    @Test
    public void test9AlPasarTurnoLasUnidadesEnemigasSeMovieronSegunSusCapacidadesCorrectamente() {
        Partida partida = new Partida();
        Jugador jugador = new Jugador(10, 10, "Ariel");

        partida.crearPartidaGenerica(jugador);

        Defensa torreBlanca1 = new TorreBlanca(1, 1);
        partida.construir(torreBlanca1);

        /*rango de ataque de torre blanca = 0 - 4 fila ; 0 - 4 columna*/
        partida.terminarTurno(); // Torre construida
        partida.insertarEnemigo(Enemigo.crearArania(2));
        partida.insertarEnemigo(Enemigo.crearArania(3));

        partida.terminarTurno(); // enemigos en posicion (0,2)
        partida.terminarTurno(); // enemigos en posicion (0, 4) (muere 1 arania)
        partida.terminarTurno(); // enemigo en posicion (0, 6)
        partida.terminarTurno(); // enemigo paso la meta que estaba en (0, 7)

        CondicionPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano());
        assertFalse(partida.jugadorTieneTodaLaVidaYMaximosCreditos());
    }
}
