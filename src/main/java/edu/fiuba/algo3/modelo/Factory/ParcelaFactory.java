package edu.fiuba.algo3.modelo.Factory;
import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoIdentificadaException;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.*;

import java.util.HashMap;

public class ParcelaFactory {
    private final HashMap<String, Parcela> tipoDeParcelas;

    public ParcelaFactory(Posicion unaPosicion){
        this.tipoDeParcelas = new HashMap<>();
        this.tipoDeParcelas.put("Rocoso", new Rocoso(unaPosicion));
        this.tipoDeParcelas.put("Tierra", new Tierra(unaPosicion));
        this.tipoDeParcelas.put("Pasarela", new Pasarela(unaPosicion, new Casilla()));
        this.tipoDeParcelas.put("Largada", new Pasarela(unaPosicion, new Largada()));
        this.tipoDeParcelas.put("Meta", new Pasarela(unaPosicion, new Meta()));
        this.tipoDeParcelas.put("TrampaDeArena", new Pasarela(unaPosicion, new TrampaDeArena()));
    }

    static public Parcela obtenerParcela(String nombreParcela, Posicion unaPosicion){
        ParcelaFactory factory = new ParcelaFactory(unaPosicion);
        Parcela parcela = factory.tipoDeParcelas.get(nombreParcela);

        if(parcela == null) {
            throw new ParcelaNoIdentificadaException();
        }
        return parcela;

    }
}
