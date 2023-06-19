package edu.fiuba.algo3.modelo.Jugador;

import java.util.Random;

public class Contador {

    private int contadorAraniasMuertas;
    private int contadorHormigasMuertas;
    
    public Contador() {
        this.contadorAraniasMuertas = 0;
        this.contadorHormigasMuertas = 0;
    }
  
    
    public void incrementarContadorAranias(){
        this.contadorAraniasMuertas++;
    }
    
    public void incrementarContadorHormigas(){
        this.contadorHormigasMuertas++;
    }

    public int obtenerMuertesHormigas() {
        return contadorHormigasMuertas;
    }

    public int obtenerMuertesAranias() {
        return contadorAraniasMuertas;
    }


    public int obtenerRecompensaHormiga() {
        if(contadorHormigasMuertas > 10) {
            return 2;
        }
        return 1;
    }

    public int obtenerRecompensaArania() {
        return new Random().nextInt(10) + 1;
    }

    public int obtenerRecompensaLechuza() {
        return 10;
    }
}
