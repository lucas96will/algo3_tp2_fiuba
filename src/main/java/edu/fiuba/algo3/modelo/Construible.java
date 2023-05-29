package edu.fiuba.algo3.modelo;

public class Construible {
    private Defensa defensa;

    public void construir(Defensa defensa){
        this.defensa = defensa;
    }

    public boolean construccionTerminada() {
        return defensa.estaTerminada();
    }
}
