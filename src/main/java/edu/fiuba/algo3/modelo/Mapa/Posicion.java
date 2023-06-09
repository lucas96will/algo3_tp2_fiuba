package edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Direccion.*;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Posicion {
    private int fila;
    private int columna;

    public Posicion(int posFila, int posCol){
        fila = posFila;
        columna = posCol;
    }

    public Posicion(Posicion unaPosicion) {
        fila = unaPosicion.fila;
        columna = unaPosicion.columna;
    }

    public boolean estaEnRango(int rango,Posicion posicion){
        int dist_vertical = Math.abs(fila - posicion.fila);
        int dist_horizontal = Math.abs(columna - posicion.columna);
        int dist = Math.max(dist_horizontal, dist_vertical);

        return dist <= rango;
    }

    public boolean esIgual(Posicion ... unasPosiciones) {
        List<Posicion> posiciones = Arrays.asList(unasPosiciones);
        return posiciones.stream().anyMatch(p -> p.fila == this.fila && p.columna == this.columna);
    }

    @Override
    public String toString() {

        return ("( " + fila + ", " + columna + " )");
    }

    public void mover(Direccion direccion) {
        this.fila = direccion.moverFila(fila);
        this.columna = direccion.moverColumna(columna);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Posicion)) return false;
        Posicion posicion = (Posicion) o;
        return fila == posicion.fila && columna == posicion.columna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }

    public boolean estaEnLaMismaColumna(Posicion destino){
        return destino.columna == columna;
    }

    public int obtenerFila() {
        return fila;
    }

    public int obtenerColumna() {
        return columna;
    }

    public Direccion obtenerDireccionHorizontalHacia(Posicion destino) {
        if (columna < destino.columna) {
            return new Derecha();
        } else {
            return new Izquierda();
        }
    }

    public Direccion obtenerDireccionVerticalHacia(Posicion destino) {
        if(fila < destino.fila) {
            return new Abajo();
        } else {
            return new Arriba();
        }
    }

    public int distanciasHorizontalA(Posicion destino) {
        return Math.abs(destino.columna - columna);
    }

    public int distanciasVerticalA(Posicion destino) {
        return Math.abs(destino.fila - fila);
    }
}
