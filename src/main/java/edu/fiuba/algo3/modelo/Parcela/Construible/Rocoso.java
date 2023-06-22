package edu.fiuba.algo3.modelo.Parcela.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoPuedeContenerEnemigo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Rocoso extends Construible {
    public Rocoso() {
        super();
    }

    public Rocoso(Posicion unaPosicion) {
        super(unaPosicion);
    }

    @Override
    public void insertarDefensa(Defensa defensa) throws Exception {
        Jugador.getInstance().obtenerReembolso(defensa);
        throw new Exception("Solo la tierra puede contener una defensa");
    }


    @Override
    public void insertarEnemigo(Enemigo unEnemigo) throws Exception {
        throw new ParcelaNoPuedeContenerEnemigo("No se puede insertar un enemigo en Rocoso");
    }

    @Override
    public void moveElEnemigo(Enemigo enemigo, Posicion actual) {
    }

    @Override
    public void establecerPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

}
