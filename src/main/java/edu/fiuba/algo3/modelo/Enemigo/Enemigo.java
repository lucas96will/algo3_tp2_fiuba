package edu.fiuba.algo3.modelo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.DatosJugador;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import java.util.List;

public abstract class Enemigo {
    private final int danio;
    private int vida;
    private final int velocidad;
    private final int energia;
    protected int recompensa;
    protected boolean muerto;
    protected Posicion posicion;
    private Posicion posicionAnterior;

    public Enemigo(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa, Posicion unaPosicion) {
        vida = unaVida;
        danio = unDanio;
        velocidad = unaVelocidad;
        energia = unaEnergia;
        recompensa = unaRecompensa;
        posicion = unaPosicion;
        this.posicionAnterior = null;
        muerto = false;
    }
    
    public Enemigo(int unaVida, int unDanio, int unaVelocidad, int unaEnergia, int unaRecompensa) {
            vida = unaVida;
            danio = unDanio;
            velocidad = unaVelocidad;
            energia = unaEnergia;
            recompensa = unaRecompensa;
            posicion = null;
            this.posicionAnterior = null;
            muerto = false;
        }

    public int recibirDanio(int danio){
        vida = vida - danio;
        return vida > 0 ? 0 : morir();
    }

    abstract protected int morir();

    public boolean muerto(){
        return muerto;
    }

    abstract protected int entregarRecompensa();


    public int hacerDanio(){
        return danio;
    }

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
        for(int i = 0; i < velocidad; i++){
            k = 0;
            seMovio = false;
            while(k < parcelas.size() && !seMovio){
                unaParcela = parcelas.get(k);
                if(this.mePuedoMoverAEstaParcela(unaParcela)){
                    seMovio = unaParcela.moveElEnemigo(this);
                }
                k++;
            }
            this.daniarAlJugador(!seMovio && i == velocidad - 1);
        }

    }

    private boolean mePuedoMoverAEstaParcela(Parcela parcela) {
        return parcela.estaEnRangoLateralesA(this.posicion)
                && (posicionAnterior == null || !parcela.tieneLaMismaPosicion(this.posicion, this.posicionAnterior));
    }

    private void daniarAlJugador(boolean llegueALaMeta){
        //lógica meta (podria ir un return danio)
        if(!llegueALaMeta){
            return;
        }
        DatosJugador datosJugador = DatosJugador.getInstance();
        datosJugador.reducirVidaJugador(this.danio);
        this.muerto = true;
        Logger.getInstance().logError(this + " hizo " + danio + " de daño al jugador");
    }

    public boolean estaEnRango(int rango, Posicion posicion) {
        return this.posicion.estaEnRango(rango, posicion);
    }
    
}
