package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Partida;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefensaTest {
    @Test
    public void defensaTardaEnConstruirseLoQueDice() {
        //Verificarquecadadefensatardeenconstruirse lo que dice que tarda y que recién están
        // “operativas” cuando ya se terminaron de construir.
        Partida partida = new Partida();
        partida.crearPartidaGenerica("Joaquin");

        partida.comenzar();




    }
}
