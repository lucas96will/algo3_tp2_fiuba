package edu.fiuba.algo3.modelo.Enemigo.Movimiento;

import edu.fiuba.algo3.modelo.Direccion.*;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public class MovimientoVolador implements Movimiento {

    protected Posicion obtenerPosicionMeta(List<Parcela> parcelas) {
        int i = 0;
        Parcela parcela = parcelas.get(i);
        Posicion destino = parcela.obtenerPosicionFinal();//TODO: Add eliminar getter(Encontrar mejor solucion)

        while (destino == null && i < parcelas.size() - 1) {
            i++;
            parcela = parcelas.get(i);
            destino = parcela.obtenerPosicionFinal();
        }
        return destino;
    }

    @Override
    public void moverse(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {
        //Moverse en L de Lechuza *badummtss*

        Posicion destino = obtenerPosicionMeta(parcelas);

        if (posActual.equals(destino) && !enemigo.muerto()) {
            enemigo.daniarAlJugador();
        }

        if (posActual.estaEnLaMismaColumna(destino)) {
            acercarseVerticalmente(posActual, destino);
        } else {
            acercarseHorizontalmente(posActual, destino);
        }

    }


    private void acercarseVerticalmente(Posicion actual, Posicion destino) {
        Direccion direccion = actual.obtenerDireccionVerticalHacia(destino);
        actual.mover(direccion);
    }

    private void acercarseHorizontalmente(Posicion actual, Posicion destino) {
        Direccion direccion = actual.obtenerDireccionHorizontalHacia(destino);
        actual.mover(direccion);
    }


}