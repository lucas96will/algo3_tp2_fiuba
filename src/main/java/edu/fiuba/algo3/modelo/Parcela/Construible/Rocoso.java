package edu.fiuba.algo3.modelo.Parcela.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoPuedeContenerEnemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

import java.util.List;

public class Rocoso extends Construible {

    public Rocoso(Posicion unaPosicion) {
        super(unaPosicion);
    }

    @Override
    public void insertarDefensa(Defensa defensa, List<Defensa> defensasJugador) throws DefensaNoSePudoConstruir {
        if(defensa.tieneLaMismaPosicion(posicion)){
            throw new DefensaNoSePudoConstruir("Solo la tierra puede contener una defensa");
        }
    }


    @Override
    public void insertarEnemigo(Enemigo unEnemigo) throws Exception {
        throw new ParcelaNoPuedeContenerEnemigo("No se puede insertar un enemigo en Rocoso");
    }

    @Override
    public void moveElEnemigo(Enemigo enemigo, Posicion actual) {
    }

    @Override
    public void actualizarEstado() {

    }

}
