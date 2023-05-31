package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;

public class EstadoDefensaCompleto implements EstadoDefensa{
    
    private boolean noAtacoEnEsteTurno;
    
    public EstadoDefensaCompleto(){
        noAtacoEnEsteTurno = true;
    }
    @Override
    public int atacarPasarela(Pasarela pasarela, Posicion posicion, int rango, int danio) {
        if(posicion.estaEnRango(rango, pasarela.posicion) && noAtacoEnEsteTurno){
            int creditos = pasarela.atacarAlPrimerEnemigo(danio);
            if(creditos != -1){
                noAtacoEnEsteTurno = false;
                return creditos;
            }
        }
        return 0;
    }

    @Override
    public void siguienteEstado(Defensa defensa) {
        defensa.establecerEstado(new EstadoDefensaCompleto());
    }
}
