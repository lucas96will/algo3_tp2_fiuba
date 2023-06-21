package edu.fiuba.algo3.modelo.Jugador;

import java.util.Random;


public class Contador {
    private final int RECOMPENSA_HORMIGAS_MIN = 1;
    private final int RECOMPENSA_HORMIGAS_MAX = 2;
    private final int RECOMPENSA_ARANIAS_MIN = 1;
    private final int RECOMPENSA_ARANIAS_MAX = 10;
    private final int RECOMPENSA_LECHUZA = 10;
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
            return RECOMPENSA_HORMIGAS_MAX;
        }
        return RECOMPENSA_HORMIGAS_MIN;
    }

    public int obtenerRecompensaArania() {
        return new Random().nextInt(RECOMPENSA_ARANIAS_MAX) + RECOMPENSA_ARANIAS_MIN;
    }

    public int obtenerRecompensaLechuza() {
        return RECOMPENSA_LECHUZA;
    }
}
