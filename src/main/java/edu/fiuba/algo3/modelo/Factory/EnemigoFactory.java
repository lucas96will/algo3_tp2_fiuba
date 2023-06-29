package edu.fiuba.algo3.modelo.Factory;

import edu.fiuba.algo3.modelo.Enemigo.*;
import edu.fiuba.algo3.modelo.Excepciones.EnemigoNoIdentificadoException;

import java.util.HashMap;
import java.util.Locale;

public class EnemigoFactory {

    private final HashMap<String, Enemigo> tipoDeEnemigos = new HashMap<>();

    public static Enemigo obtener(String nombreEnemigo){

        EnemigoFactory factory = new EnemigoFactory();
        Enemigo enemigo = factory.tipoDeEnemigos.get(nombreEnemigo.toLowerCase(Locale.ROOT));

        if(enemigo == null) {
            throw new EnemigoNoIdentificadoException();
        }
        return enemigo;
    }


    private EnemigoFactory (){
        this.tipoDeEnemigos.put("hormiga", new Hormiga(1,1,1));
        this.tipoDeEnemigos.put("arania", new Arania(2,2,2));
        this.tipoDeEnemigos.put("lechuza", new Lechuza());
        this.tipoDeEnemigos.put("topo", new Topo(5,1,0));
        this.tipoDeEnemigos.put("arana", new Arania(2,2,2));
    }
}
