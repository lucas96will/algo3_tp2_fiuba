package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Defensa.Defensa;

public class Jugador {
    private Recursos recursos;
    private int vida;
    private String nombre;
    private int vidaMaxima;
    private boolean jugadorIntacto;
    public Jugador(int unosCreditos, int unaVida, String unNombre) {
        recursos = new Recursos(unosCreditos);
        vida = unaVida;
        vidaMaxima = unaVida;
        nombre = unNombre;
        jugadorIntacto = true;
    }
    public static Jugador crearJugadorBase(String unNombre){
        return new Jugador(100, 10, unNombre);
    }

    public void recibirDanio(int danio){
        vida = vida - danio;
    }
    public boolean comprarDefensa(Defensa defensa){
        return defensa.comprate(recursos);
    }
    public boolean muerto(){
        return vida <= 0;
    }
    public boolean estaIntacto(){
        return vida == vidaMaxima;
    }
}
