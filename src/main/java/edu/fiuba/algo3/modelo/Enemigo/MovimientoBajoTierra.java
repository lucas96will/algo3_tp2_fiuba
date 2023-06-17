package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public class MovimientoBajoTierra implements Movimiento{
            @Override
            public void mover(Posicion posActual, Posicion posAnterior, Posicion posSiguiente){
                posAnterior.setPosicion(posActual);
                posActual.setPosicion(posSiguiente);
            }
            
            @Override
            public void moverse(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual, Posicion posAnterior) {}
}
