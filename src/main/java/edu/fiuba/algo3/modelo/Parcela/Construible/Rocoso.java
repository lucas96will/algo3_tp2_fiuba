package edu.fiuba.algo3.modelo.Parcela.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;

public class Rocoso extends Construible{
    @Override
    public boolean construirDefensa(Defensa defensa){
        return false;
    }

    @Override
    public void insertarDefensa(Defensa defensa) throws Exception {
        throw new Exception("Solo la tierra puede contener una defensa");
    }
}
