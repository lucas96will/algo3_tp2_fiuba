package edu.fiuba.algo3.modelo.Enemigo.Movimiento;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.NullPosicion;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class MovimientoVolador implements Movimiento {

    private Posicion obtenerPosicionMeta(List<Parcela> parcelas) {
        int i = 0;
        Parcela parcela = parcelas.get(i);
        Posicion destino = parcela.orientacionCosmica();//TODO: Add eliminar getter(Encontrar mejor solucion)

        while (destino.equals(NullPosicion.obtenerNullPosicion()) && i < parcelas.size() - 1) {
            i++;
            parcela = parcelas.get(i);
            destino = parcela.orientacionCosmica();
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
            posActual.acercarseVerticalmente(destino);
        } else {
            posActual.acercarseHorizontalmente(destino);
        }
    }

    @Override
    public void moverseEnojado(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {
        Posicion posLoggerActual = new Posicion(posActual);

        Posicion destino = obtenerPosicionMeta(parcelas);

        if (posActual.equals(destino) && !enemigo.muerto()) {
            enemigo.daniarAlJugador();
        }

        posActual.acercarseDiagonalmente(destino); //Algoritmo de Bresenham

    }
}