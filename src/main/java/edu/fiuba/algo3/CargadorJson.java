package edu.fiuba.algo3;

import edu.fiuba.algo3.Excepciones.EnemigosJsonParseException;
import edu.fiuba.algo3.Excepciones.RutaInvalidaException;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.ParcelaFactory;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
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

    public List<Turno> procesarEnemigos(String rutaJsonEnemigos){
        //recibe un json y procesa los enemigos para devolverlos en una lista.
        List<Turno> turnos = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            FileReader lector = new FileReader(rutaJsonEnemigos);
            // Leo archivo JSON
            JSONArray listaDeEnemigosJson = (JSONArray) parser.parse(lector);

            //Itero sobre el archivo
            listaDeEnemigosJson.forEach(enemigosJson -> guardarEnemigoEnLista(turnos, (JSONObject) enemigosJson));
            return turnos;

        } catch (IOException e) {
            throw new RutaInvalidaException();
        } catch (ParseException p) {
            p.printStackTrace();
            throw new EnemigosJsonParseException();
        }
    }

    private void guardarEnemigoEnLista(List<Turno> turnos, JSONObject jsonEnemigos) {
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
            enemigosTurnoActual.add(Enemigo.crearHormiga());
        }

        for(long i = 0; i < cantidadAranias; i++) {
            enemigosTurnoActual.add(Enemigo.crearArania());
        }

        // Creo el turno con el numero de turno y la lista de enemigos.
        Turno turnoActual = new Turno(numeroDeTurno, enemigosTurnoActual);

        turnos.add(turnoActual);
    }

    public Mapa procesarMapa(String rutaJsonMapa) {
        JSONParser parser = new JSONParser();
        try {
            FileReader lector = new FileReader(rutaJsonMapa);
            // Leo archivo JSON
            JSONObject jsonMapa = (JSONObject) parser.parse(lector);
            JSONArray filas = (JSONArray) jsonMapa.get("Mapa");

            Mapa mapa = new Mapa(filas.size());
            boolean largadaEncontrada = false;
            for(int i = 0; i < filas.size(); i++){
                JSONArray columna = (JSONArray) filas.get(i);
                for(int j = 0; j < columna.size(); j++) {
                    String nombreParcela = (String) columna.get(j);
                    if(!largadaEncontrada && nombreParcela.equals("Pasarela")){
                        nombreParcela = "Largada";
                    }
                    mapa.agregarParcelaEnPosicion(ParcelaFactory.obtenerParcela(nombreParcela), i/*Fila*/, j/*Columna*/);
                }
            }

        } catch (IOException e) {
            throw new RutaInvalidaException();
        } catch (ParseException p) {
            p.printStackTrace();
        }
        return null;
    }
}
