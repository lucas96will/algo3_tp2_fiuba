package edu.fiuba.algo3.modelo;

public class Construible {
    private Defensa defensa;

    public Construible() {
        this.defensa = null;
    }

    public void construir(Defensa defensa){
        this.defensa = defensa;
    }

    public boolean construccionTerminada() {
        return defensa.estaTerminada();
    }

    public boolean tieneConstruccion() {
        return (this.defensa != null);
    }
}
