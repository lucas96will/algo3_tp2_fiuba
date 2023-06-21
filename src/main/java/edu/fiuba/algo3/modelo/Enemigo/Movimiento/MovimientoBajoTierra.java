package edu.fiuba.algo3.modelo.Enemigo.Movimiento;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.Movimiento;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public class MovimientoBajoTierra implements Movimiento {
    @Override
    public void moverse(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {
        boolean seMovio = false;
        int k = 0;
        Parcela unaParcela;
        while (k < parcelas.size() && !seMovio) {
            unaParcela = parcelas.get(k);
            seMovio = unaParcela.moveElEnemigo(enemigo, posActual);
            k++;
        }
    }

}
