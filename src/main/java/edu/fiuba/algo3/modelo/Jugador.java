package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Partida.DatosPartida;

public class Jugador {
    private Recurso recurso;
    private int vida;
    private String nombre;
    private int vidaMaxima;
    private boolean jugadorIntacto;
    public Jugador(Recurso unRecurso, int unaVida, String unNombre) {
        recurso = unRecurso;
        vida = unaVida;
        vidaMaxima = unaVida;
        nombre = unNombre;
        jugadorIntacto = true;
        DatosPartida datosPartida = DatosPartida.getInstance();
        Contador contador = new Contador();
        datosPartida.actualizarEstado(this.vida, this.recurso, contador);
    }
    public static Jugador crearJugadorBase(String unNombre){
        return new Jugador(new Recurso(100), 10, unNombre);
    }


    public boolean comprarDefensa(Defensa defensa){
        return defensa.comprate(recurso);
    }
    public boolean muerto(){
        //refactorizar
        DatosPartida datosPartida = DatosPartida.getInstance();
        return datosPartida.obtenerVidaJugador() <= 0;
    }
    public boolean estaIntacto(){
        return vida == vidaMaxima;
    }

    public int valorCreditos() {
        return recurso.valorMonetario();
    }

    public void sumarMonedas(int recompensa) {
        recurso.sumarMonedas(recompensa);
    }
}
