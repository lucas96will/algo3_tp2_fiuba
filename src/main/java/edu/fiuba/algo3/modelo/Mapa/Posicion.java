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

    public boolean estaEnRangoLaterales(Posicion posicion){
        int distLateral = Math.abs(fila - posicion.fila) + Math.abs(columna - posicion.columna);
        return distLateral == 1;
     }

    public boolean esIgual(Posicion ... unasPosiciones) {
        List<Posicion> posiciones = Arrays.asList(unasPosiciones);
        return posiciones.stream().anyMatch(p -> p.fila == this.fila && p.columna == this.columna);
    }

    public boolean esNull() {
        return false;
    }

    public boolean esLateral(int cantColumnas, int cantFilas) {
        return (fila == 1 || fila == cantFilas || columna == 1 || columna == cantColumnas);
    }

    public int cantidadDePasarelasAlrededor(List<Parcela> pasarelas) {
        int contador = 0;

        for(Parcela pasarela : pasarelas){
            if(pasarela.estaEnRangoLateralesA(this)){
                contador++;
            }
        }
        return contador;
    }

    @Override
    public String toString() {

        return ("( " + fila + ", " + columna + " )");
    }

    public void moverArriba() {
        fila -= 1;
    }
    public void moverDerecha() {
        columna += 1;
    }
    public void moverIzquierda(){
        columna -= 1;
    }
    public void moverAbajo() {
        fila += 1;
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

    public void acercarseHorizontalmente(Posicion destino) {
        if(destino.columna > columna){
            moverDerecha();
        }if(destino.columna < columna){
            moverIzquierda();
        }
    }

    public boolean estaEnLaMismaColumna(Posicion destino){
        return destino.columna == columna;
    }

    public void acercarseVerticalmente(Posicion destino) {
        if(destino.fila > fila){
            moverAbajo();
        }if(destino.fila < fila){
            moverArriba();
        }
    }

    public void acercarseDiagonalmente(Posicion destino) {
        // Algoritmo de  Bresenham
        int distanciaHorizontal = Math.abs(destino.columna - columna);
        int distanciaVertical = Math.abs(destino.fila - fila);
        int direccionVertical = (columna < destino.columna) ? 1 : -1;
        int direccionHorizontal = (fila < destino.fila) ? 1 : -1;
        int error = (distanciaHorizontal- distanciaVertical) * 2;
        
        if(error > -distanciaVertical){
            columna += direccionVertical;
        }
        if(error < distanciaHorizontal){
            fila += direccionHorizontal;
        }

    }
}
