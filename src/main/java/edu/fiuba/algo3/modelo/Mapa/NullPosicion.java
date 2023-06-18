package edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import java.util.Arrays;
import java.util.List;

public final class NullPosicion extends Posicion {
    private static NullPosicion pos = new NullPosicion();

    private NullPosicion() {
        super(-1, -1);
    }

    public static NullPosicion obtenerNullPosicion() {
        return pos;
    }

    public boolean estaEnRango(int rango, Posicion posicion) {
        return false;
    }

    public boolean estaEnRangoLaterales(Posicion posicion) {
        return false;
    }

    public boolean esIgual(Posicion... unasPosiciones) {
        return false;
    }

    public boolean esLateral(int cantColumnas, int cantFilas) {
        return false;
    }

    public int cantidadDePasarelasAlrededor(List<Parcela> pasarelas) {
        return 0;
    }
}
