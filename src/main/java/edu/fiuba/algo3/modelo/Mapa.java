package edu.fiuba.algo3.modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mapa {
    private int tamanio_mapa;
    private Object[][] matriz;
    private Pasarela largada;
    private List<Defensa> defensas;

    public Mapa(int tamanioMapa) {
        tamanio_mapa = tamanioMapa;
        matriz = new Object[tamanioMapa][tamanioMapa];
        this.defensas = new ArrayList<>();
    }
    public void crearMapaGenerico(){
        crearPasarelasGenericas();
        crearTierraGenerica();
    }

    private void crearPasarelasGenericas(){
        Pasarela anterior = new Largada(null);
        matriz[0][0] = anterior;
        largada = anterior;
        for(int i = 1; i < 6; i++){
            anterior = new Pasarela(anterior);
            matriz[0][i] = anterior;
        }
        matriz[0][6] = new Meta(anterior);
    }

    private void crearTierraGenerica(){
        for(int i = 0; i < 7; i++){
            matriz[1][i] = new Construible();
        }
    }

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
            int direccion = random.nextInt(3);

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

    public void insertarEnemigo(Enemigo enemigo){
        largada.insertarEnemigo(enemigo);
    }

    public void construir(Defensa defensa, int posicionFila, int posicionColumna) {
        ((Construible)matriz[posicionFila][posicionColumna]).construir(defensa);
        this.defensas.add(defensa);
    }

    public void actualizarEstadoDefensas() {
        defensas.forEach(Defensa::reducirCosteEnTurnos);
    }

    public boolean construccionTerminadaEn(int posicionFila, int posicionColumna) {
        return ((Construible) this.matriz[posicionFila][posicionColumna]).construccionTerminada();
    }
}
