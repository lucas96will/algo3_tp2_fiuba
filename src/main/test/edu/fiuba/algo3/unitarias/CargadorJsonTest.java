package edu.fiuba.algo3.unitarias;

import edu.fiuba.algo3.CargadorJson;
import edu.fiuba.algo3.modelo.Turno;
import edu.fiuba.algo3.modelo.Mapa;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CargadorJsonTest {
    private final String rutaJsonEnemigos = "data/jsonTests/enemigosTest.json";
    private final String rutaJsonMapa = "data/jsonTests/mapaTest.json";

    List<Turno> turnos = (new CargadorJson()).procesarEnemigos(rutaJsonEnemigos);

    @Test
    public void laCantidadDeTurnosSeCarganCorrectamente() {
        int cantidadDeTurnosEsperada = 10;
        //assertEquals(cantidadDeTurnosEsperada, turnos.size());
    }

    @Test
    public void lasUnidadesDeCadaTurnoSeCarganCorrectamente() {
        /*assertTrue(turnos.get(0).cantidadDeMonstruosEsIgualA(1));
        assertTrue(turnos.get(1).cantidadDeMonstruosEsIgualA(2));
        assertTrue(turnos.get(2).cantidadDeMonstruosEsIgualA(3));
        assertTrue(turnos.get(3).cantidadDeMonstruosEsIgualA(1));
        assertTrue(turnos.get(4).cantidadDeMonstruosEsIgualA(2));
        assertTrue(turnos.get(5).cantidadDeMonstruosEsIgualA(3));
        assertTrue(turnos.get(6).cantidadDeMonstruosEsIgualA(1));
        assertTrue(turnos.get(7).cantidadDeMonstruosEsIgualA(1));
        assertTrue(turnos.get(8).cantidadDeMonstruosEsIgualA(1));
        assertTrue(turnos.get(9).cantidadDeMonstruosEsIgualA(2));*/
    }

    @Test
    public void cargadorRutaJsonEnemigosIncorrectaLanzaExcepcion() {
        CargadorJson cargadorJson = new CargadorJson();
        String rutaJsonEnemigosIncorrecto = "data/jsonTests/enemigosTestIncorrecto.json";

//        assertThrows(RutaInvalidaException.class, () -> cargadorJson.procesarEnemigos(rutaJsonEnemigosIncorrecto));
    }


    @Test
    public void cargadorRutaJsonMapaIncorrectaLanzaExcepcion() {
        CargadorJson cargadorJson = new CargadorJson();
        String rutaJsonMapaIncorrecto = "data/jsonTests/mapaTestIncorrecto.json.json";

//        assertThrows(RutaInvalidaException.class, () -> cargadorJson.procesarMapa(rutaJsonMapaIncorrecto));
    }

    @Test
    public void formatoJsonEnemigosInvalidoTiraExcepcionFormatoInvalidoJsonEnemigos() {

    }

    @Test
    public void mapaSeCargaCorrectamente() {
        CargadorJson cargadorJson = new CargadorJson();
        Mapa mapa = cargadorJson.procesarMapa(rutaJsonMapa);

        //TorreBlanca torreBlanca = new TorreBlanca(1,7);
       //mapa.construir(torreBlanca);

        //assertTrue(mapa.sinEnemigos());
    }
}
