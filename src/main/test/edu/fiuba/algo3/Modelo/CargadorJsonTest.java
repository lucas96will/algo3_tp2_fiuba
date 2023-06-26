package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Cargador.Cargador;
import edu.fiuba.algo3.modelo.Cargador.CargadorJson;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Defensa.Torre;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Excepciones.EnemigosJsonParseException;
import edu.fiuba.algo3.modelo.Excepciones.RutaInvalidaException;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CargadorJsonTest {
    private final String rutaJsonEnemigosTest = "data/jsonTests/enemigosTest.json";
    private final String rutaJsonMapaTest = "data/jsonTests/mapaTest.json";
    private final String rutaJsonEnemigosV2 = "data/jsonTests/enemigosV2.json";
    private final String rutaJsonConEnemigoNoReconocido = "data/jsonTests/enemigosErroneo.json";
    private final String rutaJsonEnemigoMalFormato = "data/jsonTests/enemigosMalFormato.json";


    List<List<Enemigo>> enemigosPorTurno;

    @Test
    public void laCantidadDeTurnosSeCarganCorrectamente() {
        enemigosPorTurno = (new CargadorJson()).procesarEnemigos(rutaJsonEnemigosTest);
        Logger.getInstance().logEstado("\n--> TESTUNITARIO La cantidad de turnos se cargan correctamente.");
        int cantidadDeTurnosEsperada = 10;
        assertEquals(cantidadDeTurnosEsperada, enemigosPorTurno.size());
    }

    @Test
    public void lasUnidadesDeCadaTurnoSeCarganCorrectamente() {
        enemigosPorTurno = (new CargadorJson()).procesarEnemigos(rutaJsonEnemigosTest);
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Las unidades de cada turno se cargan correctamente.");
        assertEquals(1, enemigosPorTurno.get(0).size());
        assertEquals(2, enemigosPorTurno.get(1).size());
        assertEquals(3, enemigosPorTurno.get(2).size());
        assertEquals(1, enemigosPorTurno.get(3).size());
        assertEquals(2, enemigosPorTurno.get(4).size());
        assertEquals(3, enemigosPorTurno.get(5).size());
        assertEquals(1, enemigosPorTurno.get(6).size());
        assertEquals(1, enemigosPorTurno.get(7).size());
        assertEquals(1, enemigosPorTurno.get(8).size());
        assertEquals(2, enemigosPorTurno.get(9).size());
    }

    @Test
    public void cargadorRutaJsonEnemigosIncorrectaLanzaExcepcion() {

        Logger.getInstance().logEstado("\n--> TESTUNITARIO Cargador ruta json enemigos incorrecta lanza Excepcion.");
        CargadorJson cargadorJson = new CargadorJson();
        String rutaJsonEnemigosIncorrecto = "data/jsonTests/enemigosTestIncorrecto.json";

        assertThrows(RutaInvalidaException.class, () -> cargadorJson.procesarEnemigos(rutaJsonEnemigosIncorrecto));
    }


    @Test
    public void cargadorRutaJsonMapaIncorrectaLanzaExcepcion() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Cargador ruta json mapa incorrecta lanza Excepcion.");
        CargadorJson cargadorJson = new CargadorJson();
        String rutaJsonMapaIncorrecto = "data/jsonTests/mapaTestIncorrecto.json.json";

        assertThrows(RutaInvalidaException.class, () -> cargadorJson.procesarMapa(rutaJsonMapaIncorrecto));
    }
    

    @Test
    public void mapaSeCargaCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO Mapa se carga correctamente.");
        CargadorJson cargadorJson = new CargadorJson();
        Mapa mapa = cargadorJson.procesarMapa(rutaJsonMapaTest);

        Torre torreEnPosicionValida = new Torre(10, 1, 3,new EstadoDefensaIncompleto(1), new Posicion(2,2), "Torre Blanca");
        Torre torreEnPosicionInvalida = new Torre(10, 1, 3,new EstadoDefensaIncompleto(1), new Posicion(1,1),"Torre Blanca");

        assertDoesNotThrow(()-> mapa.construir(torreEnPosicionValida));
        assertThrows(DefensaNoSePudoConstruir.class, () -> mapa.construir(torreEnPosicionInvalida));
        assertTrue(mapa.sinEnemigos());
    }

    @Test
    public void cargadorJsonV2yTestCargaCorrectamente() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO CargadorJson carga correctamente la version 2 de enemigos.");
        enemigosPorTurno = (new CargadorJson()).procesarEnemigos(rutaJsonEnemigosV2);
        assertEquals(1, enemigosPorTurno.get(0).size());
        assertEquals(2, enemigosPorTurno.get(1).size());
        assertEquals(3, enemigosPorTurno.get(2).size());
        assertEquals(1, enemigosPorTurno.get(3).size());
        assertEquals(1, enemigosPorTurno.get(4).size());
        assertEquals(1, enemigosPorTurno.get(5).size());
        assertEquals(1, enemigosPorTurno.get(6).size());
        assertEquals(1, enemigosPorTurno.get(7).size());
        assertEquals(1, enemigosPorTurno.get(8).size());
        assertEquals(3, enemigosPorTurno.get(9).size());
        assertEquals(1, enemigosPorTurno.get(10).size());
        assertEquals(2, enemigosPorTurno.get(11).size());
    }

    @Test
    public void cargadorJsonAlIntroducirUnJsonConEnemigosNoReconocidosLanzaExcepcionEnemigosJsonParseException() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO CargadorJson no carga correctamente jsonEnemigosErroneo.");
        Cargador cargador = new CargadorJson();
        assertThrows(EnemigosJsonParseException.class, () -> cargador.procesarEnemigos(rutaJsonConEnemigoNoReconocido));
    }

    @Test
    public void cargadorJsonAlIntroducirUnJsonMalFormateadoSeLanzaExcepcionEnemigosJsonparseException() {
        Logger.getInstance().logEstado("\n--> TESTUNITARIO CargadorJson no carga correctamente jsonEnemigosErroneo.");
        Cargador cargador = new CargadorJson();
        assertThrows(EnemigosJsonParseException.class, () -> cargador.procesarEnemigos(rutaJsonEnemigoMalFormato));
    }
}
