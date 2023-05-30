package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Recursos;

public abstract class Defensa {
    protected int costeEnCreditos;
    protected int costeEnTurnos;
    protected int rango;
    public Posicion posicion;
    protected int danio;

    protected boolean noAtacoEnEsteTurno;


    public Defensa(int una_posicion_fila, int una_posicion_columna) {
        posicion = new Posicion(una_posicion_fila, una_posicion_columna);
        noAtacoEnEsteTurno = true;
    }

    public boolean comprate(Recursos recursos) {
        return recursos.gastar(costeEnCreditos);
    }

    public boolean estaTerminada() {
        return costeEnTurnos == 0;
    }

    public void reducirCosteEnTurnos() {
        if(costeEnTurnos > 0){
            costeEnTurnos -= 1;
        }
    }
    public int atacarPasarela(Pasarela pasarela){
        if(posicion.estaEnRango(rango, pasarela.posicion) && noAtacoEnEsteTurno){
            int creditos = pasarela.atacarAlPrimerEnemigo(danio);
            if(creditos != -1){
                noAtacoEnEsteTurno = false;
                return creditos;
            }
        }
        return 0;
    }
}
