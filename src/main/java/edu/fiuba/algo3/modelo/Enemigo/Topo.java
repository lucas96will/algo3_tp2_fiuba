package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class Topo extends Enemigo {

    private int cantidadMovimientos;

    public Topo(Posicion unaPosicion) {
        super(new EstadoEnemigoVivo(5,1,1), unaPosicion);
        movimiento = new MovimientoBajoTierra();
        cantidadMovimientos = 0;
    }

    public Topo(int unaVida, int unDanio, int unaVelocidad) {
        super(new EstadoEnemigoVivo(unaVida,unDanio,unaVelocidad));
        movimiento = new MovimientoBajoTierra();
        cantidadMovimientos = 0;
    }

    @Override
    protected void morir() {
        
    }
    
    @Override
    public void moverse(List<Parcela> parcelas) {
        estado.moverse(movimiento, parcelas, this, posicion);
        cantidadMovimientos++;
        if(cantidadMovimientos < 5){
            estado = new EstadoEnemigoVivo(5,1,2);
        }
        else {
            estado = new EstadoEnemigoVivo(5,1,3);
        }
    }
}
