package edu.fiuba.algo3.modelo;

public class Contador {

    private int contadorAraniasMuertas;
    private int contadorHormigasMuertas;
    private static Contador contador;
    
    
    private Contador() {
        this.contadorAraniasMuertas = 0;
        this.contadorHormigasMuertas = 0;
    }
    
     public static Contador getInstance(){
        if(Contador.contador == null){
            return new Contador();
        }
        return Contador.contador;
    }
    
    public void incrementarContadorArania(){
        this.contadorAraniasMuertas++;
    }
    
    public void incrementarContadorHormigas(){
        this.contadorHormigasMuertas++;
    }

    public int getMuertesHormiga() {
        return contadorHormigasMuertas;
    }
}
