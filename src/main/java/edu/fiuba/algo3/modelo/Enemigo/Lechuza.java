package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class Lechuza extends Enemigo{
    public Lechuza(Posicion unaPosicion) {
        super(new EstadoEnemigoVivo(5,1,5), unaPosicion);
        movimiento = new MovimientoVolador();
    }

    @Override
    protected void morir() {
        Jugador jugador = Jugador.getInstance();

        jugador.obtenerRecompensa(this);
        this.estado = new EstadoEnemigoMuerto();
        Logger.getInstance().logExitoso(this + " murio.");
    }

    @Override
    public void moverse(List<Parcela> parcelas){
        estado.moverse(movimiento, parcelas, this, posicion);
    }

    public void moverseEnojado(){
        //Cuando tiene 50% menos de la vida
    }
}
