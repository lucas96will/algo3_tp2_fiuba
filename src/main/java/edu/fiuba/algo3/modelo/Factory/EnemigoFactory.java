package edu.fiuba.algo3.modelo.Factory;

import edu.fiuba.algo3.modelo.Enemigo.Arania;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Excepciones.EnemigoNoIdentificadaException;

import java.util.HashMap;

public class EnemigoFactory {

    private final HashMap<String, Enemigo> tipoDeEnemigos = new HashMap<>();

    public Enemigo EnemigoFactory(String nombreEnemigo){

        crearEnemigos();
        Enemigo enemigo = tipoDeEnemigos.get(nombreEnemigo);

        if(enemigo == null) {
            throw new EnemigoNoIdentificadaException();
        }
        return enemigo;
    }

    private void crearEnemigos (){
        this.tipoDeEnemigos.put("Hormiga", new Hormiga(1,1,1,1,1));
        this.tipoDeEnemigos.put("Arania", new Arania(2,2,2,2,2));
    }
}
