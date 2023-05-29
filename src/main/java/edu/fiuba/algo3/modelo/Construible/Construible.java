package edu.fiuba.algo3.modelo.Construible;

import edu.fiuba.algo3.modelo.Defensa.Defensa;

public class Construible {
    private Defensa defensa;

    public Construible() {
        this.defensa = null;
    }

    public void construir(Defensa unaDefensa){
        defensa = unaDefensa;
    }

    public boolean construccionTerminada() {
        return defensa.estaTerminada();
    }

    public boolean tieneConstruccion() {
        return (this.defensa != null);
    }
}
