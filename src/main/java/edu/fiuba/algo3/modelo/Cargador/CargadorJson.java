package edu.fiuba.algo3.modelo.Cargador;

import edu.fiuba.algo3.modelo.Enemigo.Arania;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Enemigo.Hormiga;
import edu.fiuba.algo3.modelo.Excepciones.EnemigosJsonParseException;
import edu.fiuba.algo3.modelo.Excepciones.NoSePuedeIdentificarLaMetaDelMapaException;
import edu.fiuba.algo3.modelo.Excepciones.RutaInvalidaException;
import edu.fiuba.algo3.modelo.Factory.EnemigoFactory;
import edu.fiuba.algo3.modelo.Mapa.DetectorExtremos;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Factory.ParcelaFactory;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Partida.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargadorJson implements Cargador {
    public void archivoEsCorrecto(String rutaJsonEnemigos, String rutaJsonMapa) {
        try {
            JSONParser parser = new JSONParser();
            parser.parse(new FileReader(rutaJsonEnemigos));
            parser.parse(new FileReader(rutaJsonMapa));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List< List<Enemigo> > procesarEnemigos(String rutaJsonEnemigos){
        List< List<Enemigo> > listaDeEnemigosPorTurno = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            FileReader lector = new FileReader(rutaJsonEnemigos);
            JSONArray listaDeEnemigosJson = (JSONArray) parser.parse(lector);

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
        long numeroDeTurno = (long) jsonEnemigos.get("turno");

        JSONObject cantidadEnemigos = (JSONObject) jsonEnemigos.get("enemigos");

        List<Enemigo> enemigosTurnoActual = new ArrayList<>();
        long cantidadHormigas = (long) cantidadEnemigos.get("hormiga");
        long cantidadAranias = (long) cantidadEnemigos.get("arana");

        for(long i = 0; i < cantidadHormigas; i++) {
            enemigosTurnoActual.add(EnemigoFactory.obtener("Hormiga"));
        }

        for(long i = 0; i < cantidadAranias; i++) {
            enemigosTurnoActual.add(EnemigoFactory.obtener("Arania"));
        }

        listaDeEnemigosPorTurno.add(enemigosTurnoActual);
    }

    public Mapa procesarMapa(String rutaJsonMapa) {

        try {
            List<Parcela> parcelas = new ArrayList<Parcela>();
            JSONParser parser = new JSONParser();
            FileReader lector = new FileReader(rutaJsonMapa);
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
                    Posicion pos = new Posicion((Integer.parseInt((String) filas[i]))/*fila*/, contadorColumna/*columna*/);
                    parcelas.add(ParcelaFactory.obtenerParcela(nombreParcela, pos));
                    contadorColumna++;
                }
                
            }
            DetectorExtremos detector = new DetectorExtremos();
            try {
                detector.configurarCamino(parcelas);
            } catch (Exception e) {
                Logger logger = Logger.getInstance();
                logger.logError(e.getMessage());
            } 
            mapa.establecerTerreno(parcelas);
                
            return mapa;
        } catch (IOException e) {
            throw new RutaInvalidaException();
        } catch (ParseException p) {
            p.printStackTrace();
        }

        return null;
    }
}
