package edu.fiuba.algo3.modelo.Enemigo;

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

    private int cantidadMovimientos;

    public Topo(Posicion unaPosicion) {
        super(new EstadoEnemigoVivo(5,1,1), new MovimientoBajoTierra(),  unaPosicion);
        cantidadMovimientos = 0;
    }

    public Topo(int unaVida, int unDanio, int unaVelocidad) {
        super(new EstadoEnemigoVivo(unaVida,unDanio,unaVelocidad), new MovimientoBajoTierra());
        cantidadMovimientos = 0;
    }

    @Override
    public void recibirAtaque(int unDanio,int rangoAtacante, Posicion posicionAtacante) throws FueraDeRangoException {

    }

    @Override
    public void morir() {

    }
    
    @Override
    public void moverse(List<Parcela> parcelas) {
        estado.moverse(movimiento, parcelas, this, posicion);
        cantidadMovimientos++;
        if(cantidadMovimientos > 4 && cantidadMovimientos < 11){
            estado = new EstadoEnemigoVivo(5,1,2);
        }
        else if (cantidadMovimientos >= 11) {
            estado = new EstadoEnemigoVivo(5,1,3);
        }
    }

    @Override
    public String toString() {
        return ("Topo en " +  posicion.toString());
    }

    @Override
    public void daniarAlJugador() {
        int turnoActual = ContadorTurnos.obtenerContador().obtenerTurnoActual();
        estado.daniarAlJugador(this.toString(), turnoActual);
        this.estado = new EstadoEnemigoMuerto();
    }
}
