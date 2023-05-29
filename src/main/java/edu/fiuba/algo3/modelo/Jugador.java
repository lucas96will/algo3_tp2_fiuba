package edu.fiuba.algo3.modelo;

public class Jugador {
    private int creditos;
    private int vida;
    private String nombre;
    private boolean jugadorIntacto;
    public Jugador(int unosCreditos, int unaVida, String unNombre) {
        creditos = unosCreditos;
        vida = unaVida;
        nombre = unNombre;
        jugadorIntacto = true;
    }
    private void jugadorInteractuo(){
        jugadorIntacto = false;
    }
    public static Jugador crearJugadorBase(String unNombre){
        return new Jugador(100, 10, unNombre);
    }

    public void recibirDanio(int danio){
        vida = vida - danio;
        jugadorInteractuo();
    }
    public boolean gastarCreditos(int gasto){
        if(creditos < gasto){
            return false;
        }
        creditos = creditos - gasto;
        jugadorInteractuo();
        return true;
    }
    public boolean muerto(){
        return vida <= 0;
    }
    public boolean estaIntacto(){
        return jugadorIntacto;
    }
}
