package edu.fiuba.algo3.modelo.Parcela;

import edu.fiuba.algo3.modelo.Excepciones.ParcelaNoIdentificadaException;
import edu.fiuba.algo3.modelo.Parcela.Construible.Rocoso;
import edu.fiuba.algo3.modelo.Parcela.Construible.Tierra;

import java.util.HashMap;

public class ParcelaFactory {
    private final HashMap<String, Parcela> tipoDeParcelas;

    public ParcelaFactory(){
        this.tipoDeParcelas = new HashMap<String, Parcela>();
        /*this.tipoDeParcelas.put("Rocoso", new Rocoso());
        this.tipoDeParcelas.put("Tierra", new Tierra());*/
/*        this.tipoDeParcelas.put("Pasarela", new Pasarela());
        this.tipoDeParcelas.put("Largada", new Largada());
        this.tipoDeParcelas.put("Meta", new Meta());*/
    }


    static public Parcela obtenerParcela(String nombreParcela){
        ParcelaFactory factory = new ParcelaFactory();
        Parcela parcela = factory.tipoDeParcelas.get(nombreParcela);
        if(parcela == null) {
            throw new ParcelaNoIdentificadaException();
        }
        return parcela;

    }
}
