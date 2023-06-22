package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaIncompletaNoPuedeAtacar;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import java.util.List;

public class EstadoDefensaCompleto implements EstadoDefensa{



    public EstadoDefensaCompleto(){
    }


    @Override
    public void siguienteEstado(Defensa defensa) {
        defensa.establecerEstado(new EstadoDefensaCompleto());
    }


    @Override
    public void atacar(Enemigo enemigo, int danio, int rango, Posicion posicion) throws DefensaIncompletaNoPuedeAtacar {
        enemigo.recibirAtaque(danio, rango, posicion);
    }

}
