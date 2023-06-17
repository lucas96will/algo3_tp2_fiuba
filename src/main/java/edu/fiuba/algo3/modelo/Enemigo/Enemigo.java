package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Excepciones.FueraDeRangoException;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Mapa.NullPosicion;

import java.util.List;

public abstract class Enemigo {
    private final int danio;
    private int vida;
    protected final int velocidad;
    protected int recompensa;
    protected EstadoEnemigo estado;
    protected Posicion posicion;
    protected Movimiento movimiento;
    private Posicion posicionAnterior;

    public Enemigo(int unaVida, int unDanio, int unaVelocidad, int unaRecompensa, Posicion unaPosicion) {
        vida = unaVida;
        danio = unDanio;
        velocidad = unaVelocidad;
        recompensa = unaRecompensa;
        posicion = unaPosicion;
        this.posicionAnterior = NullPosicion.obtenerNullPosicion();
        estado = new EstadoEnemigoVivo();
    }

    public Enemigo(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa) {
        vida = unaVida;
        danio = unDanio;
        velocidad = unaVelocidad;
        recompensa = unaRecompensa;
        posicion = NullPosicion.obtenerNullPosicion();
        this.posicionAnterior = NullPosicion.obtenerNullPosicion();
        estado = new EstadoEnemigoVivo();
    }

    public void recibirAtaque(int danio,int rangoAtacante, Posicion posicionAtacante) throws FueraDeRangoException {
        if(posicion.estaEnRango(rangoAtacante, posicionAtacante)) {
            vida -= danio;
            Logger.getInstance().logExitoso(this + " recibio ataque de Torre en " + posicionAtacante );
            if(vida <= 0) {
                morir();
            }

        } else {
            throw new FueraDeRangoException();
        }
    }

    abstract protected void morir();

    public boolean muerto() {
        return estado.getClass().equals(EstadoEnemigoMuerto.class); //Mal y pronto
    }

    public void mover(Posicion posicion) {
        this.posicionAnterior = this.posicion;
        this.posicion = posicion;
    }
    

    public int sumarDanio(int unDanio) {
        return danio + unDanio;
    }

    public void moverse(List<Parcela> parcelas) {

        estado.moverse(movimiento, parcelas, this, posicion,  posicionAnterior);
    }
    
    
    public void daniarAlJugador() {
        //lógica meta (podria ir un return danio)
        Jugador jugador = Jugador.getInstance();
        jugador.reducirVidaJugador(this.danio);
        this.estado = new EstadoEnemigoMuerto();
        Logger.getInstance().logError(this + " hizo " + danio + " de daño al jugador");
    }

    public boolean estaEnRango(int rango, Posicion posicion) {
        return this.posicion.estaEnRango(rango, posicion);
    }

}
