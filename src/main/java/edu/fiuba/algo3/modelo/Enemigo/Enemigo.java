package edu.fiuba.algo3.modelo.Enemigo;

public class Enemigo {
    private int danio;
    private int vida;
    private int velocidad;
    private int energia;
    private int recompensa;
    public Enemigo(int unvida, int undanio, int unvelocidad, int unenergia,int unrecompensa){
        vida = unvida;
        danio = undanio;
        velocidad = unvelocidad;
        energia = unenergia;
        recompensa = unrecompensa;
    }
    public static Enemigo crearHormiga(){
        return new Enemigo(1,1,1,1,1);
    }
    public static Enemigo crearArania(){
        return new Enemigo(2,2,2,2,1);
    }
}
