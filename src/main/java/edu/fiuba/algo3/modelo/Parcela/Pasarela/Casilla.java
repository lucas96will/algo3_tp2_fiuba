package edu.fiuba.algo3.modelo.Parcela.Pasarela;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import java.util.List;

public class Casilla implements EstadoPasarela {

    public boolean moverEnemigo(Enemigo enemigo, Posicion posicion) {
            enemigo.mover(posicion);
            return true;
    }
    @Override
    public void insertarEnemigo(Enemigo unEnemigo, Posicion posicion){}
}
