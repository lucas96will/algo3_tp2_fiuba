/*
package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Excepciones.NoSePudoComprarException;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;

import java.util.List;


public class NullTorre extends Defensa {


    public NullTorre() {
        super(0, 0, 0, new EstadoDefensaIncompleto(4000), new Posicion(-1, -1), "Null");
    }

    public void comprate(Recurso recursos) {
        throw new NoSePudoComprarException("No se puede comprar torre inexistente");
    }

    public void reembolsarCreditos(Recurso recurso) {
        return;
    }

    public void establecerEstado(EstadoDefensa nuevoEstado) {
        return;
    }

    public void siguienteEstado() {
        return;
    }

    public void atacar(List<Enemigo> enemigos) {
        return;
    }


    public boolean tieneLaMismaPosicion(Posicion posicion) {
        return false;
    }

    public void establecerPosicion(Posicion posicion) {
        return;
    }

    @Override
    public Posicion obtenerPosicion() {
        return new Posicion(-1,-1);
    }
}
*/
