package edu.fiuba.algo3.modelo.Factory;

import edu.fiuba.algo3.modelo.Enemigo.Arania;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Excepciones.EnemigoNoIdentificadaException;

import java.util.HashMap;

public class EnemigoFactory {

    private final HashMap<String, Enemigo> tipoDeEnemigos = new HashMap<>();

    public static Enemigo obtener(String nombreEnemigo){

        EnemigoFactory factory = new EnemigoFactory();
        Enemigo enemigo = factory.tipoDeEnemigos.get(nombreEnemigo);

        if(enemigo == null) {
            throw new EnemigoNoIdentificadaException();
        }
        return enemigo;
    }


    private EnemigoFactory (){
        this.tipoDeEnemigos.put("Hormiga", new Hormiga(1,1,1,1,1));
        this.tipoDeEnemigos.put("Arania", new Arania(2,2,2,2,2));
    }
}
