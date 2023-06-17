package edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Excepciones.DefensaNoSePudoConstruir;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Enemigo.*;
import edu.fiuba.algo3.modelo.Defensa.*;
import edu.fiuba.algo3.modelo.Partida.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapa {
    private final int cantColumnas;
    private final int cantFilas;
    private final List<Parcela> parcelas;
    private final List<Defensa> defensas;
    private final List<Enemigo> enemigos;

    private DetectorExtremos detectorExtremos;

    public Mapa() {
        this.parcelas = new ArrayList<>();
        this.defensas = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        this.detectorExtremos = new DetectorExtremos();
        cantColumnas = 0;
        cantFilas = 0;
    }

    public Mapa(int tamanio) {
        this.parcelas = new ArrayList<>();
        this.defensas = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        this.cantColumnas = tamanio;
        this.cantFilas = tamanio;
        this.detectorExtremos = new DetectorExtremos();
    }

    public void insertarEnemigo(Enemigo unEnemigo){
        for(Parcela parcela : parcelas) {
            try {
                parcela.insertarEnemigo(unEnemigo);
            } catch (Exception e) {

            }
        }
        enemigos.add(unEnemigo);
    }

    public void construir(Defensa defensa, Posicion posicion) {
        if(defensas.stream().anyMatch(d -> d.tieneLaMismaPosicion(posicion))){
            return;
        }
        Parcela parcelaElegida = parcelas.stream()
                .filter(p -> p.tieneLaMismaPosicion(posicion))
                .findFirst().orElse(null);

        try {
            if(parcelaElegida != null){
                parcelaElegida.insertarDefensa(defensa);
                if(defensa.tieneLaMismaPosicion(posicion)){
                    this.defensas.add(defensa);
                }
            }
        } catch (Exception e) {
            throw new DefensaNoSePudoConstruir();
        }

    }

    public void actualizarEstadoDefensas() {
        defensas.forEach(Defensa::siguienteEstado);
    }

    public void defensasAtacar() {
        for (Defensa defensa : defensas){

            defensa.atacar(enemigos);
            enemigos.removeIf(Enemigo::muerto);
        }
    }

    public void moverEnemigos(){
        if(enemigos.isEmpty()){
            return;
        }
        enemigos.forEach(e -> e.moverse(parcelas));
        enemigos.removeIf(Enemigo::muerto);
     
    }
    
    public boolean sinEnemigos() {
        return enemigos.size() == 0;
    }

    public void agregarParcelaEnPosicion(Parcela parcela, Posicion posicion) {
        parcela.establecerPosicion(posicion);
        parcelas.add(parcela);
    }

    public void establecerTerreno(List<Parcela> parcelas) {
        this.parcelas.addAll(parcelas);
    }
}
