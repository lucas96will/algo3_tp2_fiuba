package edu.fiuba.algo3.unitarias;

import edu.fiuba.algo3.CargadorJson;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Defensa.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.RutaInvalidaException;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CargadorJsonTest {
    private final String rutaJsonEnemigos = "data/jsonTests/enemigosTest.json";
    private final String rutaJsonMapa = "data/jsonTests/mapaTest.json";

    List<List<Enemigo>> enemigosPorTurno = (new CargadorJson()).procesarEnemigos(rutaJsonEnemigos);

    @Test
    public void laCantidadDeTurnosSeCarganCorrectamente() {
        int cantidadDeTurnosEsperada = 10;
        assertEquals(cantidadDeTurnosEsperada, enemigosPorTurno.size());
    }

    @Test
    public void lasUnidadesDeCadaTurnoSeCarganCorrectamente() {
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
        CargadorJson cargadorJson = new CargadorJson();
        String rutaJsonEnemigosIncorrecto = "data/jsonTests/enemigosTestIncorrecto.json";

        assertThrows(RutaInvalidaException.class, () -> cargadorJson.procesarEnemigos(rutaJsonEnemigosIncorrecto));
    }


    @Test
    public void cargadorRutaJsonMapaIncorrectaLanzaExcepcion() {
        CargadorJson cargadorJson = new CargadorJson();
        String rutaJsonMapaIncorrecto = "data/jsonTests/mapaTestIncorrecto.json.json";

        assertThrows(RutaInvalidaException.class, () -> cargadorJson.procesarMapa(rutaJsonMapaIncorrecto));
    }
    

    @Test
    public void mapaSeCargaCorrectamente() {
        CargadorJson cargadorJson = new CargadorJson();
        Mapa mapa = cargadorJson.procesarMapa(rutaJsonMapa);

        TorreBlanca torreEnPosicionValida = new TorreBlanca(10, 1, 3,new EstadoDefensaIncompleto(1));
        TorreBlanca torreEnPosicionInvalida = new TorreBlanca(10, 1, 3,new EstadoDefensaIncompleto(1));

        assertDoesNotThrow(()-> mapa.construir(torreEnPosicionValida, new Posicion(2,2)));
        assertThrows(RuntimeException.class, () -> mapa.construir(torreEnPosicionInvalida, new Posicion(1,1)));
        assertTrue(mapa.sinEnemigos());
    }
    
}