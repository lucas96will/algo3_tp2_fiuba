package edu.fiuba.algo3.modelo;

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
}
