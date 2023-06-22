package edu.fiuba.algo3.modelo.Parcela.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoPuedeContenerEnemigo;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Tierra extends Construible {
    public Tierra(Posicion unaPosicion) {
        super(unaPosicion);
    }

    public Tierra() {
        super();
    }

    @Override
    public void insertarDefensa(Defensa defensa) {

        defensa.establecerPosicion(posicion);
        Logger.getInstance().logExitoso(defensa + " construida en " + posicion);

    }
/*
    @Override
    public boolean moveElEnemigo(Enemigo enemigo) {
        return false;
    }
*/

    @Override
    public void insertarEnemigo(Enemigo unEnemigo) throws Exception {
        throw new ParcelaNoPuedeContenerEnemigo("Solo la tierra puede contener una defensa");
    }


    @Override
    public void moveElEnemigo(Enemigo enemigo, Posicion actual) {
    }

    @Override
    public void establecerPosicion(Posicion posicion) {
        this.posicion = posicion;
    }


}
