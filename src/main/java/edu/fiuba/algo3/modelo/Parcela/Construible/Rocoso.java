package edu.fiuba.algo3.modelo.Parcela.Construible;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa.Posicion;

public class Rocoso extends Construible{

    public Rocoso(Posicion unaPosicion) {
        super(unaPosicion);
    }

    @Override
    public void insertarDefensa(Defensa defensa) throws Exception {
        throw new Exception("Solo la tierra puede contener una defensa");
    }

    @Override
    public boolean moveElEnemigo(Enemigo enemigo) {
        return false;
    }

    @Override
    public void establecerPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

}
