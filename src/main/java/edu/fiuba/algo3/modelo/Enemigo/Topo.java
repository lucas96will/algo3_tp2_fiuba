package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoBajoTierra;
import edu.fiuba.algo3.modelo.Enemigo.EstadoEnemigo.EstadoEnemigoMuerto;
import edu.fiuba.algo3.modelo.Enemigo.Movimiento.MovimientoTerrestre;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;


import java.util.List;

public class Topo extends Enemigo {

    public Topo(Posicion unaPosicion) {
        super(new EstadoEnemigoBajoTierra(5,1,0), new MovimientoTerrestre(),  unaPosicion);
    }

    public Topo(int unaVida, int unDanio, int unaVelocidad) {
        super(new EstadoEnemigoBajoTierra(unaVida,unDanio,unaVelocidad), new MovimientoTerrestre());
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

    @Override
    public void obtenerRecompensa(Recurso recursoJugador, int contadorMuertes) {
        throw new RuntimeException("El topo es invencible porque va bajo tierra");
    }

    @Override
    public String nombre() {
        return ("Topo");
    }

    @Override
    public void siguienteEstado(int vidaActual, int vidaInicial) {
        if(vidaActual <= 0){
            estado = new EstadoEnemigoMuerto();
        }
    }

    @Override
    public Posicion obtenerPosicion() {
        return posicion;
    }
}
