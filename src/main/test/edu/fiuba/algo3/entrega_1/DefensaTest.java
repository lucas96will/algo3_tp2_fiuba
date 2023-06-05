package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Defensa.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DefensaTest {
    @Test
    public void defensaTardaEnConstruirseLoQueDice() {
        //Verificarquecadadefensatardeenconstruirse lo que dice que tarda y que recién están
        // “operativas” cuando ya se terminaron de construir.
        Defensa torreBlanca = new TorreBlanca(new Posicion(1,1), new EstadoDefensaIncompleto(1));
        Largada largada = new Largada(null, new Posicion(0,0));
        Pasarela pasarela = new Pasarela(largada,new Posicion(0,1));
        Meta meta = new Meta(pasarela, new Posicion(0,2));

        pasarela.insertarEnemigo(new Hormiga(1,1,1,1,10, new Posicion(0,0)));
        torreBlanca.establecerPasarelasEnRango(meta);
        assertEquals(0, torreBlanca.atacar());
        assertEquals(10, torreBlanca.atacar());
    }


}
