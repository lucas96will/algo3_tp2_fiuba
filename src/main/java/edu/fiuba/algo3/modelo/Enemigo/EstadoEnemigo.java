package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Enemigo.Movimiento;

public interface EstadoEnemigo {

    public boolean vivo();
    public boolean muerto();
    public void moverse(Movimiento movimiento);
}
