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
        if (pasarelasEnRango.size() == 0){
            return 0;
        }

        Iterator<Pasarela> iterador = pasarelasEnRango.iterator();
        int creditos = 0;

        while(noAtacoEnEsteTurno && iterador.hasNext()) {
            creditos = creditos + iterador.next().atacarConEstado(this, danio);
        }

        noAtacoEnEsteTurno = true;
        //En pasarela
        /*if(posicion.estaEnRango(rango, pasarela.posicion) && noAtacoEnEsteTurno){
            int creditos = pasarela.atacarAlPrimerEnemigo(danio);
            if(creditos != -1){
                noAtacoEnEsteTurno = false;
                return creditos;
            }
        }*/
        return creditos;
    }

    public int atacarEnemigos(Pasarela pasarela, List<Enemigo> enemigos, int danio) {
        if (enemigos.size() == 0) {
            return 0;
        }
        int creditos = 0;
        Iterator<Enemigo> iterador = enemigos.iterator();
        while (noAtacoEnEsteTurno && iterador.hasNext()) {
            creditos = creditos + iterador.next().recibirDanio(danio, pasarela);
            noAtacoEnEsteTurno = false;
        }

        return creditos;
    }

    @Override
    public void siguienteEstado(Defensa defensa) {
        defensa.establecerEstado(new EstadoDefensaCompleto());
    }

    @Override
    public boolean puedeAtacar() {
        return true;
    }

    @Override
    public EstadoDefensa reconstruir() {return this;}
}
