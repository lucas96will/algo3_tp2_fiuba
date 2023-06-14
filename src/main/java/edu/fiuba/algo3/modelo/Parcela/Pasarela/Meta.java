package edu.fiuba.algo3.modelo.Parcela.Pasarela;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import java.util.List;

public class Meta extends Pasarela {

    public Meta(Posicion unaPosicion) {
        super(unaPosicion);
    }

    @Override
    public void insertarEnemigo(Enemigo unEnemigo) throws Exception {
        
    }

    @Override
    public boolean esLateral(int cantColumnas, int cantFilas) {
        return false;
    }

    @Override
    public boolean puedeSerLargada(List<Parcela> pasarelas) {
        return false;
    }

}
