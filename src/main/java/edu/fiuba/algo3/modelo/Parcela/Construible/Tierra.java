package edu.fiuba.algo3.modelo.Parcela.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoPuedeContenerEnemigo;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

import java.util.List;

public class Tierra extends Construible {

    public Tierra(Posicion unaPosicion) {
        super(unaPosicion);
    }

    @Override
    public void insertarDefensa(Defensa defensa, List<Defensa> defensasJugador) {
        if(defensa.tieneLaMismaPosicion(posicion)){
            defensasJugador.add(defensa);
            Logger.getInstance().logExitoso(defensa + " construida en " + posicion);
        }
    }
    @Override
    public void insertarEnemigo(Enemigo unEnemigo) throws Exception {
        throw new ParcelaNoPuedeContenerEnemigo("Solo la tierra puede contener una defensa");
    }


    @Override
    public void moveElEnemigo(Enemigo enemigo, Posicion actual) {
    }

    @Override
    public void actualizarEstado() {

    }


}
