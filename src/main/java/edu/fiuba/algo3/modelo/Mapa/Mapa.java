package edu.fiuba.algo3.modelo.Mapa;

import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Enemigo.*;
import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private final List<Parcela> parcelas;
    private final List<Enemigo> enemigos;

    public Mapa() {
        this.parcelas = new ArrayList<>();
        this.enemigos = new ArrayList<>();
    }


    public void insertarEnemigo(Enemigo unEnemigo) {
        for (Parcela parcela : parcelas) {
            try {
                parcela.insertarEnemigo(unEnemigo);
            } catch (Exception e) {

            }
        }
        enemigos.add(unEnemigo);
    }

    public void construir(Defensa defensa) {
        Jugador jugador = Jugador.getInstance();
        List<Defensa> defensasJugador = jugador.obtenerDefensas();
        if (defensasJugador.stream().anyMatch(d -> d.mismaPosicionQueDefensa(defensa))) {
            return;
        }

        for (Parcela parcela : parcelas) {
            parcela.insertarDefensa(defensa, defensasJugador);
        }

        jugador.comprar(defensa);

    }

    public void actualizarEstadoDefensas() {
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarDefensas();

        for (Parcela unaParcela : parcelas) {
            unaParcela.actualizarEstado();
        }
    }

    public void defensasAtacar() {
        Jugador jugador = Jugador.getInstance();
        jugador.defensasAtacar(enemigos);
    }

    public void moverEnemigos() {
        if (enemigos.isEmpty()) {
            return;
        }
        enemigos.forEach(e -> e.moverse(parcelas));
        enemigos.removeIf(Enemigo::muerto);

    }

    public boolean sinEnemigos() {
        return enemigos.size() == 0;
    }


    public void agregarParcela(Parcela unaParcela) {
        parcelas.add(unaParcela);
    }

    public void establecerTerreno(List<Parcela> parcelas) {
        this.parcelas.addAll(parcelas);
    }

    public void construirTrampa(TrampaDeArena trampa, Posicion posicion) {

        for (Parcela parcela : parcelas) {
            parcela.construir(trampa, posicion);
        }
        Logger.getInstance().logExitoso(trampa + " construida en " + posicion);
    }

    public List<Enemigo> obtenerEnemigos() {
        return this.enemigos;
    }

    public List<Parcela> obtenerParcelas() {
        return this.parcelas;
    }
}
