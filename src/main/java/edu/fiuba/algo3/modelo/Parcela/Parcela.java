package edu.fiuba.algo3.modelo.Parcela;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;

import java.util.List;

public interface Parcela {
    void insertarEnemigo(Enemigo unEnemigo) throws Exception;
    void insertarDefensa(Defensa defensa) throws Exception;
    boolean moveElEnemigo(Enemigo enemigo, Posicion actual);
    boolean tieneLaMismaPosicion(Posicion ... posiciones);
    boolean estaEnRangoLateralesA(Posicion posicion);
    void establecerPosicion(Posicion posicion);
    Posicion orientacionCosmica(); //te juro que no es un sosMeta

    void construir(TrampaDeArena trampa, Posicion posicion) throws Exception;
}
