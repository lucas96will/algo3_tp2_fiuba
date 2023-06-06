package edu.fiuba.algo3.unitarias;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.EstadoDefensaIncompleto;
import edu.fiuba.algo3.modelo.Defensa.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Casilla;
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
        Defensa torreBlanca = new TorreBlanca(10, 1, 3, new EstadoDefensaIncompleto(1), new Posicion(1, 0));
        Largada largada = new Largada( new Posicion(0,0));
        Pasarela pasarela = new Casilla(new Posicion(0,1));
        Meta meta = new Meta(new Posicion(0,2));

        Enemigo hormiga = new Hormiga(1,1,1,1,1, new Posicion(0,0)); //Le habían puesto recompensa 10
        // Ojo con pasar recompenas por parametro.

        assertEquals(0, torreBlanca.atacar(hormiga)); 
        torreBlanca.siguienteEstado();
        assertEquals(1, torreBlanca.atacar(hormiga));
    }


}
