package edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import java.util.List;

public class NullPosicion extends Posicion {
    public NullPosicion() {
        super(-1, -1);
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
