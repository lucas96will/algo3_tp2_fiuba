package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;

import java.util.List;

public class Turno {

    private long numeroDeTurno;
    private List<Enemigo> enemigos;

    public Turno(long numeroDeTurno, List<Enemigo> enemigosTurnoActual) {
        this.numeroDeTurno = numeroDeTurno;
        this.enemigos = enemigosTurnoActual;
    }

    public boolean cantidadDeMonstruosEsIgualA(int cantidad) {
        return this.enemigos.size() == cantidad;
    }
}
