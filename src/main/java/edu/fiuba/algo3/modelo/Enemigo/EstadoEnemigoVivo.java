package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public class EstadoEnemigoVivo implements EstadoEnemigo {

    public EstadoEnemigoVivo(){}
    public void moverse(Movimiento movimiento, List<Parcela> parcelas, Enemigo enemigo, int velocidad, Posicion posActual, Posicion posAnterior){
        movimiento.moverse(parcelas, enemigo, posActual, posAnterior);
    }
}
