package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoBajoTierra;
import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoMuerto;
import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoVivo;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.MovimientoBajoTierra;
import edu.fiuba.algo3.modelo.Excepciones.FueraDeRangoException;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.List;

public class Topo extends Enemigo {

    public Topo(Posicion unaPosicion) {
        super(new EstadoEnemigoBajoTierra(5,1,0), new MovimientoBajoTierra(),  unaPosicion);
    }

    public Topo(int unaVida, int unDanio, int unaVelocidad) {
        super(new EstadoEnemigoBajoTierra(unaVida,unDanio,unaVelocidad), new MovimientoBajoTierra());
    }

    @Override
    public void morir() {

    }
    
    @Override
    public void moverse(List<Parcela> parcelas) {
        estado.moverse(movimiento, parcelas, this, posicion);
    }

    @Override
    public String toString() {
        return ("Topo en " +  posicion.toString());
    }

    @Override
    public void daniarAlJugador() {
        estado.daniarAlJugador(this.toString());
        this.estado = new EstadoEnemigoMuerto();
    }
}
