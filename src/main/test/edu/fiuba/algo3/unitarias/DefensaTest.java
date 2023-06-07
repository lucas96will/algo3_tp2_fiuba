package edu.fiuba.algo3.unitarias;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Defensa.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Posicion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DefensaTest {
    @Test
    public void defensaTardaEnConstruirseLoQueDice() {
        Defensa torreBlanca = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(1), new Posicion(1, 0));

        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(0,0));

        assertEquals(0, torreBlanca.atacar(hormiga)); 
        torreBlanca.siguienteEstado();
        assertEquals(1, torreBlanca.atacar(hormiga));
    }


}
