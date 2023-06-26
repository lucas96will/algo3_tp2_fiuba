package edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Enemigo.*;
import java.util.HashMap;


public class Contador {
    private final HashMap<String, Integer> registroMuertes;

    public Contador() {
        this.registroMuertes = new HashMap<>();
    }

    public void incrementarContador(Enemigo unEnemigo) {
        int numeroMuertes = obtenerMuertes(unEnemigo);
        registroMuertes.put(unEnemigo.nombre(), numeroMuertes + 1);
    }

    public int obtenerMuertes(Enemigo unEnemigo){
        registroMuertes.putIfAbsent(unEnemigo.nombre(), 0);
        return registroMuertes.get(unEnemigo.nombre());
    }


    public void obtenerRecompensa(Enemigo unEnemigo, Recurso recursosJugador){
        int cantidadMuertesEnemigo = obtenerMuertes(unEnemigo);
        unEnemigo.obtenerRecompensa(recursosJugador, cantidadMuertesEnemigo);
    }

}
