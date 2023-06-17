package edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Arania;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Enemigo.Lechuza;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoComprarException;

import java.util.Random;

public class Jugador {

    private static Jugador jugador = new Jugador();
    private Recurso recurso;
    private int vida;
    private String nombre;
    private int vidaMaxima;
    private Contador contadorMuertes;
    
    private Jugador() {
        recurso = new Recurso(100);
        vida = 100;
        vidaMaxima = 100;
        nombre = "Mario";
        contadorMuertes = new Contador();
    }

    static public Jugador getInstance(){
        return jugador;
    }

    public void actualizarEstado(int vida, Recurso recursos, String nombre) {
        vidaMaxima= vida;
        this.vida = vida;
        this.recurso = recursos;
        this.nombre = nombre;
    }
    public void actualizarContador(Contador contador){
        contadorMuertes = contador;
    }

    public void incrementarContadorAranias(){
        contadorMuertes.incrementarContadorAranias();
    }

    public void incrementarContadorHormigas(){
        contadorMuertes.incrementarContadorHormigas();
    }

    public int obtenerMuertesHormigas() {
        return contadorMuertes.obtenerMuertesHormigas();
    }

    public int obtenerVidaJugador() {
        return vida;
    }

    public void reducirVidaJugador(int danio) {
        vida -= danio;
    }

    public int obtenerMuertesArania() {
        return contadorMuertes.obtenerMuertesAranias();
    }
    public void comprarDefensa(Defensa defensa) throws DefensaNoSePudoComprarException {
        defensa.comprate(recurso);

    }

    public void obtenerReembolso(Defensa defensa){
        defensa.reembolsarCreditos(recurso);
    }

    public boolean muerto(){
        //refactorizar
        Jugador jugador = Jugador.getInstance();
        return jugador.obtenerVidaJugador() <= 0;
    }

    public boolean estaIntacto(){
        Jugador jugador = Jugador.getInstance();
        return jugador.obtenerVidaJugador() == vidaMaxima;
    }

    public int valorCreditos() {
        return recurso.valorMonetario();
    }

    public void sumarMonedas(int recompensa) {
        recurso.sumarMonedas(recompensa);
    }

    public void obtenerRecompensa(Arania arania) {
        int recompensa = contadorMuertes.obtenerRecompensaArania();
        recurso.sumarMonedas(recompensa);
    };
    public void obtenerRecompensa(Hormiga hormiga) {
        int recompensa = contadorMuertes.obtenerRecompensaHormiga();
        recurso.sumarMonedas(recompensa);
    }

    public void obtenerRecompensa(Lechuza lechuza) {
        int recompensa = contadorMuertes.obtenerRecompensaLechuza();
        recurso.sumarMonedas(recompensa);
    }
}
