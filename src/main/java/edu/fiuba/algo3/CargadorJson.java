package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Enemigo.Arania;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Excepciones.EnemigosJsonParseException;
import edu.fiuba.algo3.modelo.Excepciones.RutaInvalidaException;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Parcela.ParcelaFactory;
import edu.fiuba.algo3.modelo.Posicion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargadorJson {
    public boolean archivoEsCorrecto(String rutaJsonEnemigos, String rutaJsonMapa) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(rutaJsonEnemigos));
            Object objeto2 = parser.parse(new FileReader(rutaJsonMapa)); //todavia no hace nada, solo los lee a ver q onda

            return true;
        } catch (ParseException e) {
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List< List<Enemigo> > procesarEnemigos(String rutaJsonEnemigos){
        //recibe un json y procesa los enemigos para devolverlos en una lista.
        List< List<Enemigo> > listaDeEnemigosPorTurno = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            FileReader lector = new FileReader(rutaJsonEnemigos);
            // Leo archivo JSON
            JSONArray listaDeEnemigosJson = (JSONArray) parser.parse(lector);

            //Itero sobre el archivo
            listaDeEnemigosJson.forEach(enemigosJson -> guardarEnemigoEnLista(listaDeEnemigosPorTurno, (JSONObject) enemigosJson));
            return listaDeEnemigosPorTurno;

        } catch (IOException e) {
            throw new RutaInvalidaException();
        } catch (ParseException p) {
            p.printStackTrace();
            throw new EnemigosJsonParseException();
        }
    }

    private void guardarEnemigoEnLista(List< List<Enemigo> > listaDeEnemigosPorTurno, JSONObject jsonEnemigos) {
        // Leo numero de turno
        long numeroDeTurno = (long) jsonEnemigos.get("turno");

        // Obtengo el objeto que contiene "enemigos:"
        JSONObject cantidadEnemigos = (JSONObject) jsonEnemigos.get("enemigos");

        // Creo los enemigos
        List<Enemigo> enemigosTurnoActual = new ArrayList<>();
        long cantidadHormigas = (long) cantidadEnemigos.get("hormiga");
        long cantidadAranias = (long) cantidadEnemigos.get("arana");


        // Guardo los enemigos
        for(long i = 0; i < cantidadHormigas; i++) {
            enemigosTurnoActual.add(new Hormiga(1,1,1,1,1));
        }

        for(long i = 0; i < cantidadAranias; i++) {
            enemigosTurnoActual.add(new Arania(2,2,2,2,2));
        }

        listaDeEnemigosPorTurno.add(enemigosTurnoActual);
    }

    public Mapa procesarMapa(String rutaJsonMapa) {

        try {
            JSONParser parser = new JSONParser();
            FileReader lector = new FileReader(rutaJsonMapa);
            // Leo archivo JSON
            JSONObject json = (JSONObject) parser.parse(lector);
            JSONObject mapaJson = (JSONObject) json.get("Mapa");


            Object[] filas = mapaJson.keySet().toArray();
            Mapa mapa = new Mapa(filas.length);
            int i;
            int contadorColumna;
            for(i = 0; i < filas.length; i++) {
                JSONArray columna = (JSONArray) mapaJson.get(filas[i]);
                contadorColumna = 1;
                for(Object parcela : columna) {
                    String nombreParcela = (String) parcela;
                    mapa.agregarParcelaEnPosicion(ParcelaFactory.obtenerParcela(nombreParcela), new Posicion(Integer.parseInt((String) filas[i])/*fila*/, contadorColumna/*columna*/));
                    contadorColumna++;
                }
            }
            return mapa;
        } catch (IOException e) {
            throw new RutaInvalidaException();
        } catch (ParseException p) {
            p.printStackTrace();
        }

        return null;
    }
}
