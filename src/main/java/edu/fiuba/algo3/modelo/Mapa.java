package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.Parcela.Construible.Construible;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.*;
import edu.fiuba.algo3.modelo.Enemigo.*;
import edu.fiuba.algo3.modelo.Defensa.*;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private final int tamanio_mapa;
    private Parcela[][] matriz;
    private Pasarela largada;
    private Meta meta;
    private List<Defensa> defensas;

    public Mapa(int tamanioMapa) {
        tamanio_mapa = tamanioMapa;
        matriz = new Parcela[tamanioMapa][tamanioMapa];
        this.defensas = new ArrayList<>();
    }
    public void crearMapaGenerico(){
        /*
        Mapa generico es una matriz 7x7, con un cuadro de 5x5 tierra, y la ultima linea rocoso
        */
        crearPasarelasGenericas();
        crearTierraGenerica();
    }

    private void crearPasarelasGenericas(){
        Pasarela anterior = new Largada(null, new Posicion(0,0));
        largada = anterior;
        matriz[0][0] = largada;

        for(int i = 1; i < 6; i++){
            anterior = new Casilla(anterior, new Posicion(0, i));
            matriz[0][i] = anterior;
        }
        meta = new Meta(anterior, new Posicion(0, 6));
        matriz[0][6] = meta;
    }

    private void crearTierraGenerica(){
        for(int j = 1; j < 6; j++) {
            for (int i = 0; i < 7; i++) {
                matriz[j][i] = new Tierra();
            }
        }
        for (int i = 0; i < 7; i++) {
            matriz[6][i] = new Rocoso();
        }
    }
/* Parte de código aleatorio que se usara proximamente (seguro en la etapa gráfica)
    private void crearPasarelasAleatorias(){

        int mediador = 0;
        int mediador_atras = 0;
        int medio = tamanio_mapa/2;

        int fila = medio;
        int columna = 1;

        int direccion_anterior =-1;

        Pasarela primer_pasarela = new Largada(null);
        largada = primer_pasarela;
        Pasarela pasarela_anterior = crearEnMatriz(fila, columna, primer_pasarela);

        // Generar dirección aleatoria
        Random random = new Random();


        while(columna <= tamanio_mapa) {
            int direccion = random.nextInt(4);

            switch (direccion) {
                case 0: // Arriba
                    if(direccion_anterior == 1){
                        break;
                    }
                    if (mediador >= medio) {
                        break;
                    }
                    fila--;
                    mediador++;
                    pasarela_anterior = crearEnMatriz(fila, columna, pasarela_anterior);
                    break;
                case 1: // Abajo
                    if(direccion_anterior == 0){
                        break;
                    }
                    if (mediador <= medio) {
                        break;
                    }
                    fila++;
                    mediador--;
                    pasarela_anterior = crearEnMatriz(fila, columna, pasarela_anterior);
                    break;
                case 2: // Izquierda, quiero que sea la menos probable
                    if(direccion_anterior == 3){
                        break;
                    }
                    if (mediador_atras == 1) {
                        break;
                    }
                    columna--;
                    mediador_atras++;
                    pasarela_anterior = crearEnMatriz(fila, columna, pasarela_anterior);
                    break;
                case 3: // Derecha
                    if(direccion_anterior == 2){
                        break;
                    }
                    columna++;
                    mediador_atras--;
                    pasarela_anterior = crearEnMatriz(fila, columna, pasarela_anterior);
                    break;
            }
            direccion_anterior = direccion;
        }

    }
    private Pasarela crearEnMatriz(int fila, int columna, Pasarela pasarela_anterior){
        Pasarela pasarela = new Pasarela(pasarela_anterior);
        matriz[fila][columna] = pasarela;
        return pasarela;
    }
    */

    public void insertarEnemigo(Enemigo enemigo){
        largada.insertarEnemigo(enemigo);
    }

    public boolean construir(Defensa defensa) {

        Posicion posicion = defensa.posicion;

        if (matriz[posicion.fila][posicion.columna].construirDefensa(defensa)) {
            defensas.add(defensa);
            return true;
        } else {
            return false;
        }
    }

    public void actualizarEstadoDefensas() {
        defensas.forEach(Defensa::reducirCosteEnTurnos);
    }

    public boolean construccionTerminadaEn(int posicionFila, int posicionColumna) {
        return ((Construible) matriz[posicionFila][posicionColumna]).construccionTerminada();
    }


    public int defensasAtacar() {
        int recompensa = 0;
        Pasarela recorrido = meta;
        while(recorrido.llegoAlaLargada()){
            if(defensas.size() > 0) {
                for (Defensa defensa : defensas) {
                    recompensa = recompensa + defensa.atacarPasarela(recorrido);
                }
            }
            recorrido = recorrido.anterior;
        }
        return recompensa;
    }

    public void moverEnemigos(){
        Pasarela recorrido = meta;
        do {
            recorrido = recorrido.anterior;
            recorrido.moverEnemigos();
        } while(recorrido.llegoAlaLargada());
    }

    public int danioDeEnemigos() {
        return meta.danioTotal();
    }
}
