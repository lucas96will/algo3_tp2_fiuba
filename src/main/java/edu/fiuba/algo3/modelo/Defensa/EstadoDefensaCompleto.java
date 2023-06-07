package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import java.util.Iterator;
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
    public boolean puedeAtacar() {
        return noAtacoEnEsteTurno;
    }

    @Override
    public EstadoDefensa reconstruir() {return this;}

    @Override
    public void registrarAtaque() {noAtacoEnEsteTurno = false;}
}
