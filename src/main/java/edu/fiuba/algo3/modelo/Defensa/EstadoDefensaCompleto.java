package edu.fiuba.algo3.modelo.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.DefensaIncompletaNoPuedeAtacar;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import java.util.List;

public class EstadoDefensaCompleto implements EstadoDefensa{

    private boolean noAtacoEnEsteTurno;

    public EstadoDefensaCompleto(){
        noAtacoEnEsteTurno = true;
    }
    @Override
    public int atacar(List<Pasarela> pasarelasEnRango, int danio) {
        return 0;
    }

    public int atacarEnemigos(Pasarela pasarela, List<Enemigo> enemigos, int danio) {
        return 0;
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
