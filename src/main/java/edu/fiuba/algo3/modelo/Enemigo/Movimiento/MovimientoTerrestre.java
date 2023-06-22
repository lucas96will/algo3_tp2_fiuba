package edu.fiuba.algo3.modelo.Enemigo.Movimiento;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public class MovimientoTerrestre implements Movimiento {
    @Override
    public void moverse(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {
        int k = 0;
        Posicion posicionInicial = new Posicion(posActual);
        Parcela unaParcela;
        while (k < parcelas.size() && enemigo.estaEnRango(0, posicionInicial)) {
            unaParcela = parcelas.get(k);
            unaParcela.moveElEnemigo(enemigo, posActual);
            k++;
        }
    }

}

