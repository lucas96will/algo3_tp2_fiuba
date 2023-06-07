package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Partida.DatosJugador;

public class Jugador {
    private final Recurso recurso;
    private int vida;
    private final String nombre;
    private int vidaMaxima;

    public Jugador(Recurso unRecurso, int unaVida, String unNombre) {
        recurso = unRecurso;
        vida = unaVida;
        vidaMaxima = unaVida;
        nombre = unNombre;
        DatosJugador datosJugador = DatosJugador.getInstance();
        Contador contador = new Contador();
        datosJugador.actualizarEstado(this.vida, this.recurso, contador);
    }
    /*public static Jugador crearJugadorBase(String unNombre){
        return new Jugador(new Recurso(100), 10, unNombre);
    }*/

    public boolean comprarDefensa(Defensa defensa){
        return defensa.comprate(recurso);
    }

    public void obtenerReembolso(Defensa defensa){
        defensa.reembolsarCreditos(recurso);
    }

    public boolean muerto(){
        //refactorizar
        DatosJugador datosJugador = DatosJugador.getInstance();
        return datosJugador.obtenerVidaJugador() <= 0;
    }

    public boolean estaIntacto(){
        DatosJugador datosJugador = DatosJugador.getInstance();
        return datosJugador.obtenerVidaJugador() == vidaMaxima;
    }

    public int valorCreditos() {
        return recurso.valorMonetario();
    }

    public void sumarMonedas(int recompensa) {
        recurso.sumarMonedas(recompensa);
    }

}
