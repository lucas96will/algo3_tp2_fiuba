package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Enemigo.Arania;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsosTest {
    @Test
    public void caso1jugadorEmpiezaConVidaYCreditosCorrespondientes() {
        Jugador jugador = new Jugador(new Recurso(100), 10, "Joaquin");

        assertTrue(jugador.estaIntacto());
        assertEquals(100, jugador.valorCreditos());
    }

    @Test
    public void caso2defensaTardaEnConstruirseLoQueDice(){
        Partida partida = new Partida();
        Recurso recurso = new Recurso(100);
        Jugador jugador = new Jugador(recurso, 10, "Joaquin");
        Mapa mapa = new Mapa(7);
        partida.crearPartida(jugador,mapa);
        partida.comenzar();

        //Defensa torreBlanca = new TorreBlanca(1,1);
        Defensa torrePlateada = new TorrePlateada(new Posicion(1,6), new EstadoDefensaIncompleto(2));
        partida.insertarEnemigo(new Arania(1,2,2,2,20,new Posicion(0,0)));
        partida.construir(torrePlateada); // 2 turnos para construirse
        partida.terminarTurno(); // arania en posicion (0,2)
        assertEquals(80, recurso.valorMonetario());
        partida.terminarTurno(); // (0,4) , torre no construida , arania vida = 2, torrePlateada construida
        assertEquals(80, recurso.valorMonetario());

        partida.terminarTurno(); // (0,6), muerte arania
        assertEquals(100, recurso.valorMonetario());
    }

    @Test
    public void caso3defensaSeConstruyeSoloSiElJugadorTieneLosCreditosNecesarios() {
        Jugador jugador = new Jugador(new Recurso(10), 10, "Josecito");

        Defensa torreBlanca1 = new TorreBlanca(new Posicion(1,5), new EstadoDefensaIncompleto(2));
        Defensa torreBlanca2 = new TorreBlanca(new Posicion(1,6), new EstadoDefensaIncompleto(2));
        assertTrue(jugador.comprarDefensa(torreBlanca1));
        assertFalse(jugador.comprarDefensa(torreBlanca2));
    }

    @Test
    public void caso4DefensaSoloSePuedeConstruirSobreTierra() {
        Tierra tierra = new Tierra();
        Rocoso rocoso = new Rocoso();

        assertDoesNotThrow(() -> tierra.insertarDefensa(new TorreBlanca(new Posicion(1,1), new EstadoDefensaIncompleto(2))));
        assertThrows(Exception.class,() -> rocoso.insertarDefensa(new TorreBlanca(new Posicion(1,1), new EstadoDefensaIncompleto(2))));
    }

    @Test
    public void caso5DefensasAtacanDentroDelRangoEsperado() {
        /*Verificarquelasdefensas ataquen dentro del rango esperado (y verificar lo contrario*/

        Partida partida = new Partida();
        Jugador jugador = new Jugador(new Recurso(10), 100, "Josecito");
        Mapa mapa = new Mapa(7);
        partida.crearPartida(jugador,mapa);

        Defensa torreBlanca1 = new TorreBlanca(new Posicion(1,1), new EstadoDefensaIncompleto(2));
        partida.construir(torreBlanca1);

        //Enemigo hormiga = Enemigo.crearHormiga(1,null);
        Hormiga hormiga = new Hormiga(1,1,1,1,10, new Posicion(0,0));
        partida.insertarEnemigo(hormiga);

        for(int i = 0; i < 8; i++){
            partida.terminarTurno();
        }

        assertTrue(jugador.estaIntacto()); //hormiga no llego al final :D
    }

    @Test
    public void caso6UnidadesEnemigasSonDaniadasAcordeAlAtaqueRecibido() {
        Partida partida = new Partida();
        Jugador jugador = new Jugador(new Recurso(10), 10, "Ariel");
        Mapa mapa = new Mapa(7);
        partida.crearPartida(jugador,mapa);;

        Defensa torreBlanca1 = new TorreBlanca(new Posicion(1,1), new EstadoDefensaIncompleto(1));
        partida.construir(torreBlanca1);

        partida.insertarEnemigo(new Hormiga(1,1,1,1,10, new Posicion(1,1)));
        partida.terminarTurno(); // tarda 1 turno en construir la torre blanca
        partida.terminarTurno(); // Muere la hormiga
        CondicionPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano()); // No hay enemigos en el mapa y el jugador tiene vida

        partida.insertarEnemigo(new Arania(2,2,2,2,2,  new Posicion(1,1))); // arania con 2 de vida
        partida.terminarTurno(); //
        partida.terminarTurno(); // se dania a la arania, queda con 1 de vida

        condicionPartida = partida.estado();
        assertTrue(condicionPartida.sigueJugando());

        partida.terminarTurno(); // muere la arania

        condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano());
    }

    @Test
    public void caso7LasUnidadesEnemigasSoloSeMuevenPorLaParcelaAutorizadaCorrectamente() {
        Pasarela pasarela = new Pasarela(null, new Posicion(1,1));
        Tierra tierra = new Tierra();
        Rocoso rocoso = new Rocoso();

        assertThrows(Exception.class,() -> tierra.insertarEnemigo(new Hormiga(1,1,1,1,10, new Posicion(0,0))));
        assertThrows(Exception.class,() -> rocoso.insertarEnemigo(new Hormiga(1,1,1,1,10, new Posicion(0,0))));
        assertDoesNotThrow(() -> pasarela.insertarEnemigo(new Hormiga(1,1,1,1,10, new Posicion(0,0))));
    }

    @Test
    public void caso8JugadorMataUnidadYCobraCreditoCorrespondiente(){

        Partida partida = new Partida();
        Recurso recurso = new Recurso(100);
        Jugador jugador = new Jugador(recurso, 100, "Josecito");
        /*100*/

        Mapa mapa = new Mapa(7);
        partida.crearPartida(jugador,mapa);;

        Defensa torreBlanca1 = new TorreBlanca(new Posicion(1,1), new EstadoDefensaIncompleto(1));
        partida.construir(torreBlanca1);
        // 100-10 = 90

        Hormiga hormiga = new Hormiga(1,1,1,1,1, new Posicion(0,0));
        partida.insertarEnemigo(hormiga);
        // 1 credito

        partida.terminarTurno();
        partida.terminarTurno();
        //90 + 1 = 91
        assertEquals(91, recurso.valorMonetario());
    }

    @Test
    public void test9AlPasarTurnoLasUnidadesEnemigasSeMovieronSegunSusCapacidadesCorrectamente() {
        Partida partida = new Partida();
        Jugador jugador = new Jugador(new Recurso(10), 10, "Ariel");

        Mapa mapa = new Mapa(7);
        partida.crearPartida(jugador,mapa);

        Defensa torreBlanca1 = new TorreBlanca(new Posicion(1,1), new EstadoDefensaIncompleto(2));
        partida.construir(torreBlanca1);

        /*rango de ataque de torre blanca = 0 - 4 fila ; 0 - 4 columna*/
        partida.terminarTurno(); // Torre construida
        partida.insertarEnemigo(new Hormiga(2,2,2,2,2, new Posicion(1,1)));
        partida.insertarEnemigo(new Hormiga(2,2,2,2,2, new Posicion(1,1)));

        partida.terminarTurno(); // enemigos en posicion (0,2)
        partida.terminarTurno(); // enemigos en posicion (0, 4) (muere 1 arania)
        partida.terminarTurno(); // enemigo en posicion (0, 6)
        partida.terminarTurno(); // enemigo paso la meta que estaba en (0, 7)

        CondicionPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano());
        assertFalse(jugador.estaIntacto());
    }

    @Test
    public void caso10SeGanaElJuegoMatandoTodosLosEnemigosCorrectamente(){
        Partida partida = new Partida();
        Jugador jugador = new Jugador(new Recurso(100), 100, "Ariel");

        Mapa mapa = new Mapa(7);
        partida.crearPartida(jugador,mapa);

        Defensa torreBlanca1 = new TorreBlanca(new Posicion(1,1), new EstadoDefensaIncompleto(2));
        partida.construir(torreBlanca1);

        Hormiga hormiga = new Hormiga(1,1,1,1,10, new Posicion(1,1));
        partida.insertarEnemigo(hormiga);

        Hormiga hormiga2 = new Hormiga(1,1,1,1,10, new Posicion(1,1));
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
        Jugador jugador = new Jugador(new Recurso(10), 100, "Ariel");

        Mapa mapa = new Mapa(7);
        partida.crearPartida(jugador,mapa);

        Defensa torreBlanca1 = new TorreBlanca(new Posicion(1,1), new EstadoDefensaIncompleto(2));
        partida.construir(torreBlanca1);

        for(int i = 0; i < 10; i++) {
            partida.insertarEnemigo(new Hormiga(1,1,1,1,10, new Posicion(1,1)));
        }

        for(int i = 0; i < 10; i++){
            partida.terminarTurno();
        }

        CondicionPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.gano());
    }

    @Test
    public void caso12SePierdeElJuegoPorElDanioDeLosEnemigos() {
        Partida partida = new Partida();
        Jugador jugador = new Jugador(new Recurso(10), 10, "Ariel");
        Mapa mapa = new Mapa(7);
        partida.crearPartida(jugador,mapa);

        Defensa torreBlanca1 = new TorreBlanca(new Posicion(1,1), new EstadoDefensaIncompleto(2));
        partida.construir(torreBlanca1);

        for(int i = 0; i < 14; i++) {
            partida.insertarEnemigo(new Hormiga(1,1,1,1,10, new Posicion(1,1)));
        }

        for(int i = 0; i < 10; i++){
            partida.terminarTurno();
        }
        CondicionPartida condicionPartida = partida.estado();
        assertTrue(condicionPartida.perdio());
    }
}
