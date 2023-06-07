package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.*;
import edu.fiuba.algo3.modelo.Enemigo.*;
import edu.fiuba.algo3.modelo.Defensa.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapa {
    private final int cantColumnas;
    private final int cantFilas;
    private List<Parcela> parcelas;
    private List<Defensa> defensas;
    private List<Enemigo> enemigos;
    private List<Enemigo> enemigosMuertos;
    private Parcela largada;

    public Mapa() {
        this.parcelas = new ArrayList<>();
        this.defensas = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        this.enemigosMuertos = new ArrayList<>();
        cantColumnas = 0;
        cantFilas = 0;
    }

    public Mapa(int tamanio) {
        this.parcelas = new ArrayList<>();
        this.defensas = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        this.enemigosMuertos = new ArrayList<>();
        this.cantColumnas = tamanio;
        this.cantFilas = tamanio;
    }
    
    public static Mapa generico(){
        Mapa mapa = new Mapa(7);
        mapa.crearMapaGenerico();
        return mapa;
    }
    

    public void insertarEnemigo(Enemigo unEnemigo){
        this.largada.moveElEnemigo(unEnemigo);
            enemigos.add(unEnemigo);
    }

    public void crearMapaGenerico(){
        //Mapa generico es una matriz 7x7, con un cuadro de 5x5 tierra, y la ultima linea rocoso
        crearPasarelasGenericas();
        crearTierraGenerica();
        iniciarLargada();
    }

    private void crearPasarelasGenericas(){
        /* 1 2 3 4 5 6 7 
        *1 P P P P P P P 
        *2 T T T T T T T 
        * . . . . . . . .
        * . . . . . . . .
        *8 R R R R R R R */
        parcelas.add(new Casilla(new Posicion(1,1)));
        
        for(int i = 2; i < 8; i++){
            parcelas.add(new Casilla(new Posicion(1,i)));
        }
        parcelas.add(new Casilla(new Posicion(1,7)));
    }

    private void crearTierraGenerica(){
        /* 1 2 3 4 5 6 7 
         *1 P P P P P P P 
         *2 T T T T T T T 
         * . . . . . . . .
         * . . . . . . . .
         *8 R R R R R R R */
        for(int j = 2; j < 8; j++) {
            for(int k = 1; k < 8; k++) {
                parcelas.add(new Tierra(new Posicion(j, k)));
            }
        }
        for(int h = 1; h < 8; h++) {
            parcelas.add(new Rocoso(new Posicion(7, h)));
        }
    }


    public void construir(Defensa defensa, Posicion posicion) {
        // que no haya una defensa en la misma posicion
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
            throw new RuntimeException("No se puede construir");
        }

    }

    public void actualizarEstadoDefensas() {
        defensas.forEach(Defensa::siguienteEstado);
    }

    public int defensasAtacar() {
        int recompensa = 0;
        for (Defensa defensa : defensas){
            for(Enemigo enemigo : enemigos){
                recompensa = recompensa + defensa.atacar(enemigo);
            }
            enemigosMuertos.addAll(enemigos.stream().filter(Enemigo::muerto).collect(Collectors.toList()));
            enemigos.removeIf(Enemigo::muerto);
        }
        return recompensa;
    }

    public void moverEnemigos(){
        if(enemigos.isEmpty()){
            return;
        }
        enemigos.forEach(e -> e.moverse(parcelas));
        enemigosMuertos.addAll(enemigos.stream().filter(Enemigo::muerto).collect(Collectors.toList()));
        enemigos.removeIf(Enemigo::muerto);
     
    }
    
    public boolean sinEnemigos() {
        return enemigos.size() == 0;
    }

    public void agregarParcelaEnPosicion(Parcela parcela, Posicion posicion) {
        parcela.establecerPosicion(posicion);
        parcelas.add(parcela);
    }

    public void iniciarLargada() {
        // aca va la logica para encontrar la largada
        List<Parcela> pasarelasLaterales = parcelas.stream()
                .filter(p -> p.esLateral(cantColumnas, cantFilas)).collect(Collectors.toList());
        
        this.largada = pasarelasLaterales.stream()
                .filter(p-> p.puedeSerLargada(pasarelasLaterales))
                .findFirst()
                .orElse(null);
        
        if(largada == null) {
            throw new RuntimeException("No se pudo inicializar la largada");
        }
        //this.largada = new Largada(new Posicion(1,2)); // hardcodeada la del json mapa catedra
    }
}
