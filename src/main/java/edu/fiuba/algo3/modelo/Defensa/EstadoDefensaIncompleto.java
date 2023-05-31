package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;

public class EstadoDefensaIncompleto implements EstadoDefensa{

    private int tiempoConstruccion;

    public EstadoDefensaIncompleto(int tiempoConstruccion){
        this.tiempoConstruccion = tiempoConstruccion;
    }
    @Override
    public int atacarPasarela(Pasarela pasarela, Posicion posicion, int rango, int danio) {
        return 0;
    }

    @Override
    public void siguienteEstado(Defensa defensa) {
        int nuevoTiempoConstruccion = tiempoConstruccion - 1;
        if(nuevoTiempoConstruccion  == 0){
            defensa.establecerEstado(new EstadoDefensaCompleto());
        } else {
            defensa.establecerEstado(new EstadoDefensaIncompleto(nuevoTiempoConstruccion));
        }
    }
}
