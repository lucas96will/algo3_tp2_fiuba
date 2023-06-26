package edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Enemigo.*;
import java.util.HashMap;


public class Contador {
    private final HashMap<String, Integer> registroMuertes;
    private final HashMap<String, Integer> recompensas;
    
    public Contador() {
        this.registroMuertes = new HashMap<>();
        this.recompensas = new HashMap<>();
    }

    public void incrementarContador(Enemigo unEnemigo) {
        int numeroMuertes = obtenerMuertes(unEnemigo);
        registroMuertes.put(unEnemigo.nombre(), numeroMuertes + 1);
    }

    public int obtenerMuertes(Enemigo unEnemigo){
        registroMuertes.putIfAbsent(unEnemigo.nombre(), 0);
        return registroMuertes.get(unEnemigo.nombre());
    }


    public void obtenerRecompensa(Enemigo unEnemigo, Recurso recurso){
        int cantidadMuertesEnemigo = obtenerMuertes(unEnemigo);
        unEnemigo.obtenerRecompensa(recurso, cantidadMuertesEnemigo);
    }

}
