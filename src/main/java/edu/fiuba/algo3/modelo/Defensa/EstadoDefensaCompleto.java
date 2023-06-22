package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaIncompletaNoPuedeAtacar;

public class EstadoDefensaCompleto implements EstadoDefensa{



    public EstadoDefensaCompleto(){
    }


    @Override
    public void siguienteEstado(Defensa defensa) {
        defensa.establecerEstado(new EstadoDefensaCompleto());
    }


    @Override
    public void atacar(Enemigo enemigo, int danio) throws DefensaIncompletaNoPuedeAtacar {
        enemigo.recibirAtaque(danio);
    }

}
