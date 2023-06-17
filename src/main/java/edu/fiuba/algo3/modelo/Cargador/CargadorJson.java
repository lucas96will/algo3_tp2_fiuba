package edu.fiuba.algo3.modelo.Cargador;

import edu.fiuba.algo3.modelo.Direccion.*;
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
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
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
            int cantidadColumnas = ((JSONArray)mapaJson.get(filas[1])).size();
            String mapeo [][] = new String[filas.length][((JSONArray)mapaJson.get(filas[1])).size()];
            for(i = 0; i < filas.length; i++) {
                contadorColumna = 0;
                JSONArray columna = (JSONArray) mapaJson.get(filas[i]);
                for(Object parcela : columna) {
                    //String nombreParcela = (String) parcela;
                   mapeo[i][contadorColumna] = (String) parcela;
                    //Posicion pos = new Posicion((Integer.parseInt((String) filas[i]))/*fila*/, contadorColumna/*columna*/);
                    //parcelas.add(ParcelaFactory.obtenerParcela(nombreParcela, pos));
                    contadorColumna++;
                }
            }
            //Encontrar Largada
            int j = 0;
            int adyascentes = 0;
            int fila_actual = 0;
            int columna_actual = 0;
            int fila_siguiente = 0;
            int columna_siguiente = 0;
            Direccion direccion = null;
            while(i < filas.length && adyascentes != 1) {
                while(j < cantidadColumnas && adyascentes != 1) {
                    if(mapeo[i][j].equals("Pasarela")){
                        fila_actual = i;
                        columna_actual = j;
                        fila_siguiente = i;
                        columna_siguiente = j;
                        adyascentes = 0;
                        if((i + 1 < filas.length) && mapeo[i + 1][j].equals("Pasarela")){
                            direccion = new Abajo();
                            fila_siguiente ++;
                            adyascentes ++;
                        }
                        if((j + 1 < cantidadColumnas) && mapeo[i][j + 1].equals("Pasarela")){
                            direccion = new Derecha();
                            columna_siguiente ++;
                            adyascentes ++;
                        }
                        if((i - 1 > 0) && mapeo[i - 1][j].equals("Pasarela")){
                            direccion = new Arriba();
                            fila_siguiente --;
                            adyascentes ++;

                        }
                        if((j - 1 > 0) && mapeo[i][j - 1].equals("Pasarela")){
                            direccion = new Izquierda();
                            columna_siguiente --;
                            adyascentes ++;
                        }
                        j++;
                    }
                    i++;
                }
                Parcela pasarela = ParcelaFactory.obtenerParcela("Largada", new Posicion(fila_actual, columna_actual));
                ((Pasarela) pasarela).establecerDireccion(direccion);
                parcelas.add(pasarela);

                Parcela meta = null;
                while(/*EncontreSiguiente*/) {
                    if((fila_siguiente + 1 < filas.length) && ((fila_siguiente + 1) != fila_actual) && mapeo[fila_siguiente + 1][columna_siguiente].equals("Pasarela"));
                }
                //nueva pasarela con posicion fil_larrgada, col_largada y estado Largada y agregarlo
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
