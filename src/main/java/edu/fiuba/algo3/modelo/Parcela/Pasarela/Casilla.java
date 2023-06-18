package edu.fiuba.algo3.modelo.Parcela.Pasarela;
import edu.fiuba.algo3.modelo.Direccion.Direccion;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import java.util.List;

public class Casilla implements EstadoPasarela {

    private Direccion direccion;

    public boolean moverEnemigo(Enemigo enemigo, Posicion unaPosicion) {
        direccion.moverDireccion(unaPosicion);
        return true;
    }

    @Override
    public void establecerDireccion(Direccion unaDireccion) {
        direccion = unaDireccion;
    }

    @Override
    public void insertarEnemigo(Enemigo unEnemigo, Posicion posicion){
        
    }
}
