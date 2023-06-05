package edu.fiuba.algo3.modelo.Parcela.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;

public class Tierra extends Construible{
    @Override
    public void insertarDefensa(Defensa defensa) throws Exception {
        if (this.defensa == null){
            this.defensa = defensa;
        }
    }
}
