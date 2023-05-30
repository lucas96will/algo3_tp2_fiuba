package edu.fiuba.algo3.modelo;

public class Recursos {
    private int creditos;

    public Recursos(int unosCreditos){
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
