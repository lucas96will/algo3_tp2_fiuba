package edu.fiuba.algo3.modelo.Parcela.Pasarela;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public abstract class Pasarela implements Parcela {

    public Posicion posicion;
    public Pasarela(Posicion unaPosicion){
        posicion = unaPosicion;
    }

    public Pasarela() {}

    public void insertarDefensa(Defensa defensa) throws Exception {
        throw new Exception("No se puede construir una defensa en una pasarela");
    }

    public boolean moveElEnemigo(Enemigo enemigo){
        enemigo.mover(posicion);
        return true;
    }
    
    @Override
    public void establecerPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public boolean tieneLaMismaPosicion(Posicion... posiciones) {
        return this.posicion.esIgual(posiciones);
    }

    @Override
    public boolean estaEnRangoLateralesA(Posicion posicion) {
        return this.posicion.estaEnRangoLaterales(posicion);
    }
}
