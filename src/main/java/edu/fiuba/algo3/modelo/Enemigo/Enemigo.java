package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Posicion;

import java.util.List;

public abstract class Enemigo {
    private int danio;
    private int vida;
    private int velocidad;
    private int energia;
    protected int recompensa;
    protected boolean muerto;
    private Posicion posicion;
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
        boolean seMovio = false;
        int k;
        Parcela unaParcela;
        for(int i = 0; i < velocidad; i++){
            k = 0;
            while(k < parcelas.size() && !seMovio){
                unaParcela = parcelas.get(k);
                if(this.posicion.estaEnRangoLaterales(unaParcela.getPosicion())
                                    && (posicionAnterior == null || !unaParcela.getPosicion().esIgual(this.posicion, this.posicionAnterior))){
                    seMovio = unaParcela.moveElEnemigo(this);
                }
                k++;
            }
            if(!seMovio){
                //lógica meta
            }
        }



    }
}
