package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;

public class Recurso {
    private int creditos;

    public Recurso(int unosCreditos){
        creditos = unosCreditos;
    }

    public void gastar(int costeEnCreditos) throws RecursosInsuficientesException {
        if(creditos < costeEnCreditos){
            throw new RecursosInsuficientesException();
        }
        creditos = creditos - costeEnCreditos;
    }

    public int valorMonetario() {
        return creditos;
    }

    public void sumarMonedas(int recompensa) {
        creditos += recompensa;
    }
    
    
}
