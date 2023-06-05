package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import java.util.List;

public class  EstadoDefensaIncompleto implements EstadoDefensa{

    private int tiempoConstruccion;

    public EstadoDefensaIncompleto(int tiempoConstruccion){
        this.tiempoConstruccion = tiempoConstruccion;
    }
    @Override
    public int atacar(List<Pasarela> pasarelasEnRango, int danio) {
        return 0;
    }

    @Override
    public void siguienteEstado(Defensa defensa) {
        int nuevoTiempoConstruccion = tiempoConstruccion - 1;
        if(nuevoTiempoConstruccion  == 0){
            defensa.establecerEstado(new EstadoDefensaCompleto());
        } else {
            tiempoConstruccion = nuevoTiempoConstruccion;
            defensa.establecerEstado(this);
        }
    }

    @Override
    public boolean puedeAtacar() {return false;}

    @Override
    public EstadoDefensa reconstruir() {
        tiempoConstruccion --;
        return tiempoConstruccion <= 0 ? new EstadoDefensaCompleto() : this;
    }
}
