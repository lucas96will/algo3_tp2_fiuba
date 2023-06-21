package edu.fiuba.algo3.modelo.Mapa;

import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Jugador.Recurso;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Enemigo.*;
import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.TrampaDeArena;
import edu.fiuba.algo3.modelo.Partida.Logger;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void construir(Defensa defensa, Posicion posicion) {
        Jugador jugador = Jugador.getInstance();
        List<Defensa> defensasJugador = jugador.obtenerDefensas();

        if (defensasJugador.stream().anyMatch(d -> d.tieneLaMismaPosicion(posicion))) {
            return;
        }
        Parcela parcelaElegida = parcelas.stream()
                .filter(p -> p.tieneLaMismaPosicion(posicion))
                .findFirst().orElse(null);

        try {
            if (parcelaElegida != null) {
                parcelaElegida.insertarDefensa(defensa);
                if (defensa.tieneLaMismaPosicion(posicion)) {
                    defensasJugador.add(defensa);
                }
            }
        } catch (Exception e) {
            throw new DefensaNoSePudoConstruir();
        }

    }

    public void actualizarEstadoDefensas() {
        Jugador jugador = Jugador.getInstance();
        jugador.actualizarDefensas();
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
        return enemigos.size() == 0 ;
    }

    public void agregarParcelaEnPosicion(Parcela parcela, Posicion posicion) {
        parcela.establecerPosicion(posicion);
        parcelas.add(parcela);
    }

    public void establecerTerreno(List<Parcela> parcelas) {
        this.parcelas.addAll(parcelas);
    }

    public void construirTrampa(TrampaDeArena trampa, Posicion posicion){

        for(Parcela parcela: parcelas){
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
