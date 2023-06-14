package edu.fiuba.algo3.modelo.Enemigo;

public class Muerto implements EstadoEnemigo {
    public boolean vivo() {
        return false;
    }

    public boolean muerto() {
        return true;
    }
}
