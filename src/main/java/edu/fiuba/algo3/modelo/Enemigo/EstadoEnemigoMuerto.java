package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;

import java.util.List;

public class EstadoEnemigoMuerto implements EstadoEnemigo {

    @Override
    public void moverse(Movimiento movimiento, List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {
        
    }

    @Override
    public void daniarAlJugador(String s) {
        
    }

    @Override
    public void recibirAtaque(Enemigo enemigo, int unDanio) {
        
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
