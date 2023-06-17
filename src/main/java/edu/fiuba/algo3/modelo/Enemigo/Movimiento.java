package edu.fiuba.algo3.modelo.Enemigo;

public interface Movimiento {
    void mover(Posicion posActual, Posicion posAnterior, Posicion posSiguiente);

    public void moverse(List<Parcela> parcelas, Enemigo enemigo, int velocidad);
}
