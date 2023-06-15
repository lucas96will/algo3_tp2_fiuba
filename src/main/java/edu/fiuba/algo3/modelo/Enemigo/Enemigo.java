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
    private final int velocidad;
    private final int energia;
    protected int recompensa;
    protected EstadoEnemigo estado;
    protected Posicion posicion;
    private Posicion posicionAnterior;

    public Enemigo(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa, Posicion unaPosicion) {
        vida = unaVida;
        danio = unDanio;
        velocidad = unaVelocidad;
        energia = unaEnergia;
        recompensa = unaRecompensa;
        posicion = unaPosicion;
        this.posicionAnterior = NullPosicion.obtenerNullPosicion();
        estado = new Vivo();
    }

    public Enemigo(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa) {
        vida = unaVida;
        danio = unDanio;
        velocidad = unaVelocidad;
        energia = unaEnergia;
        recompensa = unaRecompensa;
        posicion = NullPosicion.obtenerNullPosicion();
        this.posicionAnterior = NullPosicion.obtenerNullPosicion();
        estado = new Vivo();
    }

    public void recibirAtaque(int danio,int rangoAtacante, Posicion posicionAtacante) throws FueraDeRangoException {
        if(posicion.estaEnRango(rangoAtacante, posicionAtacante)) {
            vida -= danio;

            if(vida <= 0) {
                morir();
            }

        } else {
            throw new FueraDeRangoException();
        }
    }

//    abstract protected int morir();
    abstract protected void morir();

    public boolean muerto() {
        return estado.muerto();
    }

    abstract protected int entregarRecompensa();

    public void mover(Posicion posicion) {
        this.posicionAnterior = this.posicion;
        this.posicion = posicion;
    }

    public int sumarDanio(int unDanio) {
        return danio + unDanio;
    }

    public void moverse(List<Parcela> parcelas) {
        //refactorizar este metodo
        boolean seMovio;
        int k;
        Parcela unaParcela;
        for (int i = 0; i < velocidad; i++) {
            k = 0;
            seMovio = false;
            while (k < parcelas.size() && !seMovio) {
                unaParcela = parcelas.get(k);

                seMovio = unaParcela.moveElEnemigo(this, posicion, posicionAnterior);

                k++;
            }
            this.daniarAlJugador(!seMovio && i == velocidad - 1);
        }

    }

    private boolean mePuedoMoverAEstaParcela(Parcela parcela) {
        return estado.vivo() && parcela.estaEnRangoLateralesA(this.posicion)
                && (posicionAnterior.esNull() || !parcela.tieneLaMismaPosicion(this.posicion, this.posicionAnterior));
    }

    private void daniarAlJugador(boolean llegueALaMeta) {
        //lógica meta (podria ir un return danio)
        if (!llegueALaMeta) {
            return;
        }
        Jugador jugador = Jugador.getInstance();
        jugador.reducirVidaJugador(this.danio);
        this.estado = new Muerto();
        Logger.getInstance().logError(this + " hizo " + danio + " de daño al jugador");
    }

    public boolean estaEnRango(int rango, Posicion posicion) {
        return this.posicion.estaEnRango(rango, posicion);
    }

}
