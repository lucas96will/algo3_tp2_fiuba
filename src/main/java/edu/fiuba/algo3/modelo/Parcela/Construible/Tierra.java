package edu.fiuba.algo3.modelo.Parcela.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Posicion;

public class Tierra extends Construible{
    public Tierra(Posicion unaPosicion) {
        super(unaPosicion);
    }

    @Override
    public void insertarDefensa(Defensa defensa){
        /*if (defensa.tieneLaMismaPosicion(this.posicion)){
            this.defensa = defensa;
            return true;
        } else {
            throw new Exception("Defensa no se puede construir.");
        }*/

        /*if(this.defensa == null && defensa.tieneLaMismaPosicion(this.posicion)){
            this.defensa = defensa;
            defensa.establecerPosicion(this.posicion);
            
        } else {
            throw new Exception("Defensa no se puede construir.");
        }*/
        defensa.establecerPosicion(posicion);
        Logger.getInstance().logExitoso(defensa + " construida en " + posicion);
        
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
