package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

public class JsonCargador {
    public boolean archivoEsCorrecto(String jsonEnemigos, String jsonMapa) {
        try {
            JSONParser parser = new JSONParser();
            Object objeto = parser.parse(jsonEnemigos);
            Object objeto2 = parser.parse(jsonMapa); //todavia no hace nada, solo los lee a ver q onda

            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public List<Enemigo> procesarEnemigos(String jsonEnemigos) {
        //recibe un json y procesa los enemigos para devolverlos en una lista.
        JSONParser parser = new JSONParser();
        return null;
    }

    public Parcela[][] procesarMapa(String jsonMapa) {
        return null;
    }
}
