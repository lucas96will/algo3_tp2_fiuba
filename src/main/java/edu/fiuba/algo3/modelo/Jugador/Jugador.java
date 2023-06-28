package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Defensa.Defensa;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Cobrable.Cobrable;
import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private static Jugador jugador = new Jugador();
    private Recurso recurso;
    private int vida;
    private String nombre;
    private int vidaMaxima;
    private Contador contador;
    private List<Defensa> defensas;

    private Jugador() {
        recurso = new Recurso(100);
        vida = 100;
        vidaMaxima = 100;
        nombre = "Mario";
        contador = new Contador();
        defensas = new ArrayList<>();
    }

    static public Jugador getInstance() {
        return jugador;
    }

    public String obtenerNombreJugador(){
        return nombre;
    }

    public void actualizarEstado(int vida, Recurso recursos, String nombre) {
        vidaMaxima = vida;
        this.vida = vida;
        this.recurso = recursos;
        this.nombre = nombre;
    }

    public void actualizarContador(Contador unContador) {
        this.contador = unContador;
    }

    public void incrementarContador(Enemigo unEnemigo) {
        contador.incrementarContador(unEnemigo);
    }


    public int obtenerMuertes(Enemigo unEnemigo) {
        return contador.obtenerMuertes(unEnemigo);
    }

    public int obtenerVidaJugador() {
        return vida;
    }

    public void reducirVidaJugador(int danio) {
        vida -= danio;
    }


    public void comprar(Cobrable cobrable) throws RecursosInsuficientesException {
        cobrable.comprate(recurso);
    }

    public void actualizarDefensas() {
        defensas.forEach(Defensa::siguienteEstado);
    }

    public void defensasAtacar(List<Enemigo> enemigos) {
        for (Defensa defensa : defensas) {
            defensa.atacar(enemigos);
            enemigos.removeIf(Enemigo::muerto);
        }
    }

    public void resetearDefensas() {
        defensas = new ArrayList<>();
    }

    public List<Defensa> obtenerDefensas() {
        return defensas;
    }


    public void obtenerReembolso(Cobrable cobrable) {
        cobrable.reembolsarCreditos(recurso);
    }

    public boolean muerto() {
        Jugador jugador = Jugador.getInstance();
        return jugador.obtenerVidaJugador() <= 0;
    }

    public int valorCreditos() {
        return recurso.valorMonetario();
    }


    public void obtenerRecompensa(Enemigo unEnemigo){
        contador.obtenerRecompensa(unEnemigo, recurso);
    }

    public void eliminarPrimeraTorre() {
        if(!defensas.isEmpty()) {
            defensas.remove(0);
        }
    }

}
