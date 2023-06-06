package edu.fiuba.algo3.modelo.Parcela;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Posicion;

public interface Parcela {
    void insertarEnemigo(Enemigo unEnemigo) throws Exception;
    void insertarDefensa(Defensa defensa) throws Exception;
    boolean moveElEnemigo(Enemigo enemigo);
    boolean tieneLaMismaPosicion(Posicion ... posiciones);
    boolean estaEnRangoLateralesA(Posicion posicion);
}
