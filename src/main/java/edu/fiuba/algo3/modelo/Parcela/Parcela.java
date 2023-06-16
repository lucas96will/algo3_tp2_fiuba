package edu.fiuba.algo3.modelo.Parcela;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;

import java.util.List;

public interface Parcela {
    void insertarEnemigo(Enemigo unEnemigo) throws Exception;
    void insertarDefensa(Defensa defensa) throws Exception;
    boolean moveElEnemigo(Enemigo enemigo, Posicion actual, Posicion anterior);
    boolean tieneLaMismaPosicion(Posicion ... posiciones);
    boolean estaEnRangoLateralesA(Posicion posicion);
    void establecerPosicion(Posicion posicion);
    boolean esExtremo(List<Parcela> pasarelas);
}
