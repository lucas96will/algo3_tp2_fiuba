package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaIncompletaNoPuedeAtacar;

public interface EstadoDefensa {

    void siguienteEstado(Defensa defensa);

    void atacar(Enemigo enemigo, int danio) throws DefensaIncompletaNoPuedeAtacar;
}
