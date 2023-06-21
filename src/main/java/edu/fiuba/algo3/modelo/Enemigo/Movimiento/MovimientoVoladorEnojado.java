package edu.fiuba.algo3.modelo.Enemigo.Movimiento;

import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public class MovimientoVoladorEnojado extends MovimientoVolador{
    @Override
    public void moverse(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {
        Posicion posLoggerActual = new Posicion(posActual);

        Posicion destino = obtenerPosicionMeta(parcelas);

        if (posActual.equals(destino) && !enemigo.muerto()) {
            enemigo.daniarAlJugador();
        }

        acercarseDiagonalmente(posActual, destino); //Algoritmo de Bresenham
    }


    private void acercarseDiagonalmente(Posicion actual, Posicion destino) {
        int distanciaVertical = actual.distanciasVerticalA(destino);
        int distanciaHorizontal = actual.distanciasHorizontalA(destino);
        Direccion direccionVertical = actual.obtenerDireccionVerticalHacia(destino);
        Direccion direccionHorizontal = actual.obtenerDireccionHorizontalHacia(destino);
        int error = (distanciaHorizontal - distanciaVertical) * 2;

        if(error > -distanciaVertical) {
            actual.mover(direccionHorizontal);
        }
        if(error < distanciaHorizontal) {
            actual.mover(direccionVertical);
        }
    }
}
