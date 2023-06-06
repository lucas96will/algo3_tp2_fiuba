package edu.fiuba.algo3.modelo.Parcela;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Posicion;

public interface Parcela {
    public boolean construirDefensa(Defensa unaDefensa);
    public void insertarEnemigo(Enemigo unEnemigo) throws Exception;
    public void insertarDefensa(Defensa defensa) throws Exception;
    public boolean moveElEnemigo(Enemigo enemigo);
    Posicion getPosicion();
}
