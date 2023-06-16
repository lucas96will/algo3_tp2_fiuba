package edu.fiuba.algo3.modelo.Parcela.Construible;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Defensa.NullTorre;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import java.util.List;

public abstract class Construible implements Parcela {
    protected Posicion posicion;
    protected Defensa defensa;

    public Construible() {
        this.defensa = NullTorre.obtenerNullTorre();
    }

    public Construible(Posicion unaPosicion) {
        this.defensa = NullTorre.obtenerNullTorre();
        this.posicion = unaPosicion;
    }



    abstract public void insertarDefensa(Defensa defensa) throws Exception;


    @Override
    public boolean tieneLaMismaPosicion(Posicion... posiciones) {
        return this.posicion.esIgual(posiciones);
    }

    @Override
    public boolean estaEnRangoLateralesA(Posicion posicion) {
        return this.posicion.estaEnRangoLaterales(posicion);
    }
    
    /*@Override
    public boolean esLateral(int cantColumnas, int cantFilas){
        return false;
    }*/

    @Override
    public boolean esExtremo(List<Parcela> pasarelas) {
        return false;
    }
    
}
