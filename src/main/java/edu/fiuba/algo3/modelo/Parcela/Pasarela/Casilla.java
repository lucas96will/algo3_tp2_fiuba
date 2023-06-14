package edu.fiuba.algo3.modelo.Parcela.Pasarela;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import java.util.List;

public class Casilla extends Pasarela {
    
    public Casilla(Posicion unaPosicion){
        super(unaPosicion);
    }

    public Casilla() {
        super();
    }


    @Override
    public void insertarEnemigo(Enemigo unEnemigo) throws Exception {
        
    }

    @Override
    public boolean esLateral(int cantColumnas, int cantFilas) {
        return this.posicion.esLateral(cantColumnas, cantFilas);
    }

    @Override
    public boolean puedeSerLargada(List<Parcela> pasarelas) {
        return this.posicion.cantidadDePasarelasAlrededor(pasarelas) <= 1;
    }

}
