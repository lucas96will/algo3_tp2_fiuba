package edu.fiuba.algo3.modelo;

public class Recurso {
    private int creditos;

    public Recurso(int unosCreditos){
        creditos = unosCreditos;
    }

    public boolean gastar(int costeEnCreditos) {
        if(creditos < costeEnCreditos){
            return false;
        }
        creditos = creditos - costeEnCreditos;
        return true;
    }

    public int valorMonetario() {
        return creditos;
    }

    public void sumarMonedas(int recompensa) {
        creditos += recompensa;
    }
}
