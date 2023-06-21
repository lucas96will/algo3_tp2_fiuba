package edu.fiuba.algo3.Modelo;

import edu.fiuba.algo3.modelo.Excepciones.DefensaNoIdentificadaException;
import edu.fiuba.algo3.modelo.Excepciones.EnemigoNoIdentificadaException;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoIdentificadaException;
import edu.fiuba.algo3.modelo.Factory.DefensaFactory;
import edu.fiuba.algo3.modelo.Factory.EnemigoFactory;
import edu.fiuba.algo3.modelo.Factory.ParcelaFactory;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactoryTest {
    @Test
    public void test01DefensaFactoryDevuelveExcepcionSiLePidoUnaDefensaInexistente() {
        DefensaFactory defensaFactory = new DefensaFactory();

        assertThrows(DefensaNoIdentificadaException.class, () -> defensaFactory.obtenerDefensa("voladora"));
    }

    @Test
    public void test02EnemigosFactoryDevuelveExcepcionSiLePidoUnEnemigoInexistente() {
        assertThrows(EnemigoNoIdentificadaException.class, () -> EnemigoFactory.obtener("superman"));
    }

    @Test
    public void test03ParcelaFactoryDevuelveExcepcionSiLePidoUnEnemigoInexistente() {
        assertThrows(ParcelaNoIdentificadaException.class, () ->
                ParcelaFactory.obtenerParcela("rock", new Posicion(0,0)));
    }
}
