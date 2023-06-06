package edu.fiuba.algo3.modelo.Parcela.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Posicion;

public class Tierra extends Construible{
    public Tierra(Posicion unaPosicion) {
        super(unaPosicion);
    }

    @Override
    public void insertarDefensa(Defensa defensa) throws Exception {
        if (this.defensa == null){
            this.defensa = defensa;
        } else {
            throw new Exception("Defensa ya construida en ese posicion.");
        }
    }

    @Override
    public boolean construirDefensa(Defensa defensa){
        if(!this.posicion.esIgual(defensa.getPosicion())){
            return false;
        }
        this.defensa = defensa;
        return true;
    }

    @Override
    public boolean moveElEnemigo(Enemigo enemigo) {
        return false;
    }

    @Override
    public Posicion getPosicion() {
        return this.posicion;
    }
}
