package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaIncompletaNoPuedeAtacar;

public class  EstadoDefensaIncompleto implements EstadoDefensa{

    private int tiempoConstruccion;

    public EstadoDefensaIncompleto(int tiempoConstruccion){
        this.tiempoConstruccion = tiempoConstruccion;
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
    public void atacar(Enemigo enemigo, int danio) throws DefensaIncompletaNoPuedeAtacar {
        throw new DefensaIncompletaNoPuedeAtacar("Defensa incompleta no puede atacar");
    }
}
