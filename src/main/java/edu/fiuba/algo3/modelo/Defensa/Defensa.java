package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Recursos;

public class Defensa {
    protected int costeEnCreditos;
    protected int costeEnTurnos;
    protected int posicion_fila;
    protected int posicion_columna;

    public Defensa(int una_posicion_fila, int una_posicion_columna) {
        posicion_columna = una_posicion_columna;
        posicion_fila = una_posicion_fila;
    }

    public boolean comprate(Recursos recursos) {
        return recursos.gastar(costeEnCreditos);
    }

    public boolean estaTerminada() {
        return this.costeEnTurnos == 0;
    }

    public void reducirCosteEnTurnos() {
        if(this.costeEnTurnos > 0){
            costeEnTurnos -= 1;
        }
    }
}
