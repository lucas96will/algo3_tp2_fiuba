package edu.fiuba.algo3.modelo.Parcela.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

public class Construible implements Parcela {
    private Defensa defensa;

    public Construible() {
        this.defensa = null;
    }

    public boolean construirDefensa(Defensa unaDefensa){
        if (defensa == null){
            defensa = unaDefensa;
            return true;
        }
        return false;
    }

    public boolean construccionTerminada() {
        return defensa.estaTerminada();
    }

    public boolean tieneConstruccion() {
        return (this.defensa != null);
    }
}
