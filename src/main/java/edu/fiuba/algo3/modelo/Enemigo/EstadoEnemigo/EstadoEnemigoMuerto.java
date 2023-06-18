package edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Lechuza;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.Movimiento;
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
    public void recibirAtaque(Enemigo enemigo, int unDanio, Posicion posicionAtacante) {
        
    }

    @Override
    public void recibirAtaqueYEvolucionar(Lechuza lechuza, int unDanio, Posicion posicionAtacante) {
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
