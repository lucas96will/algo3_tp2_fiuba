package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public interface Movimiento {
    public void moverse(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual, Posicion posAnterior);
}
