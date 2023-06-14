package edu.fiuba.algo3.modelo.Defensa;

import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;

import java.util.List;


public final class NullTorre extends Defensa {

    private static NullTorre torre = new NullTorre();

    private NullTorre() {
        super(0, 0, 0, new EstadoDefensaIncompleto(4000), new Posicion(-1, -1), "Null");
    }

    ;

    public static NullTorre obtenerNullTorre() {
        return torre;
    }

    public boolean comprate(Recurso recursos) {
        return false;
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

    public int atacar(List<Enemigo> enemigos) {
        return 0;
    }


    public boolean tieneLaMismaPosicion(Posicion posicion) {
        return false;
    }

    public void establecerPosicion(Posicion posicion) {
        return;
    }
}
