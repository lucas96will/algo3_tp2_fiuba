package edu.fiuba.algo3.modelo.Enemigo;

import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;

public class Enemigo {
    private int danio;
    private int vida;
    private int velocidad;
    private int energia;
    private int recompensa;

    public Enemigo(int unaVida, int unDanio, int unaVelocidad, int unaEnergia,int unaRecompensa){
        vida = unaVida;
        danio = unDanio;
        velocidad = unaVelocidad;
        energia = unaEnergia;
        recompensa = unaRecompensa;
    }

    public int recibirDanio(int danio, Pasarela pasarela){
        if(vida > danio){
            vida = vida - danio;
            return 0;
        }
        else{
            return morir(pasarela);
        }
    }
    private int morir(Pasarela pasarela){
        pasarela.eliminarEnemigo(this);
        return entregarRecompensa();
    }

    private int entregarRecompensa(){
        return recompensa;
    }

    public int hacerDanio(){
        return danio;
    }

    public static Enemigo crearHormiga(){
        return new Enemigo(1,1,1,1,1);
    }
    public static Enemigo crearArania(){
        return new Enemigo(2,2,2,2,1);
    }

    public void mover(Pasarela pasarela) {
        Pasarela destino = pasarela;
        for (int i = 0; i < velocidad; i++){
            if (destino.getSiguiente() != null) {
                destino = destino.getSiguiente();
            }
        }
        destino.insertarEnemigo(this);
    }

    public int sumarDanio(int unDanio) {
        return danio + unDanio;
    }
}
