package edu.fiuba.algo3.modelo.Cargador;

import edu.fiuba.algo3.modelo.Direccion.*;
import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.EnemigosJsonParseException;
import edu.fiuba.algo3.modelo.Excepciones.RutaInvalidaException;
import edu.fiuba.algo3.modelo.Factory.EnemigoFactory;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Factory.ParcelaFactory;
import edu.fiuba.algo3.modelo.Mapa.Posicion;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;
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
    
    private void cargarPasarelas(String[][] mapeo, List<Parcela> parcelas){
        int cantidadColumnas = mapeo[0].length;
        
        int i = 0;
        int j = 0;
        int adyascentes = 0;
        int filaActual = 0;
        int columnaActual = 0;
        int filaSiguiente = 0;
        int columnaSiguiente = 0;
        Direccion direccion = null;
        while(i < mapeo.length && adyascentes != 1) {
            j = 0;
            while (j < cantidadColumnas && adyascentes != 1) {
                if (mapeo[i][j].equals("Pasarela")) {
                    filaActual = i;
                    columnaActual = j;
                    filaSiguiente = i;
                    columnaSiguiente = j;
                    adyascentes = 0;
                    if ((i + 1 < mapeo.length) && mapeo[i + 1][j].equals("Pasarela")) {
                        direccion = new Abajo();
                        filaSiguiente++;
                        adyascentes++;
                    }
                    if ((j + 1 < cantidadColumnas) && mapeo[i][j + 1].equals("Pasarela")) {
                        direccion = new Derecha();
                        columnaSiguiente++;
                        adyascentes++;
                    }
                    if ((i - 1 >= 0) && mapeo[i - 1][j].equals("Pasarela")) {
                        direccion = new Arriba();
                        filaSiguiente--;
                        adyascentes++;

                    }
                    if ((j - 1 >= 0) && mapeo[i][j - 1].equals("Pasarela")) {
                        direccion = new Izquierda();
                        columnaSiguiente--;
                        adyascentes++;
                    }
                }
                j++;
            }
            i++;
        }
        Parcela pasarela = ParcelaFactory.obtenerParcela("Largada", new Posicion(filaActual + 1, columnaActual + 1));
        ((Pasarela) pasarela).establecerDireccion(direccion);
        parcelas.add(pasarela);
        cargarPasarelasRestantes(mapeo, parcelas, filaSiguiente, columnaSiguiente, filaActual, columnaActual);
    }

    private void cargarPasarelasRestantes(String[][] mapeo, List<Parcela> parcelas, int filaSiguiente, int columnaSiguiente, int filaActual, int columnaActual) {
        Parcela meta = null;
        Parcela pasarela;
        Direccion direccion;
        
        while (meta == null) {
            if ((filaSiguiente + 1 < mapeo.length) && ((filaSiguiente + 1) != filaActual) && mapeo[filaSiguiente + 1][columnaSiguiente].equals("Pasarela")) {
                direccion = new Abajo();
                filaActual = filaSiguiente;
                pasarela = ParcelaFactory.obtenerParcela("Pasarela", new Posicion(filaSiguiente + 1, columnaSiguiente + 1));
                ((Pasarela) pasarela).establecerDireccion(direccion);
                parcelas.add(pasarela);
                filaSiguiente++;
            } else if ((filaSiguiente - 1 >= 0) && ((filaSiguiente - 1) != filaActual) && mapeo[filaSiguiente - 1][columnaSiguiente].equals("Pasarela")) {
                direccion = new Arriba();
                filaActual = filaSiguiente;
                pasarela = ParcelaFactory.obtenerParcela("Pasarela", new Posicion(filaSiguiente + 1, columnaSiguiente + 1));
                ((Pasarela) pasarela).establecerDireccion(direccion);
                parcelas.add(pasarela);
                filaSiguiente--;
            } else if ((columnaSiguiente - 1 >= 0) && ((columnaSiguiente - 1) != columnaActual) && mapeo[filaSiguiente][columnaSiguiente - 1].equals("Pasarela")) {
                direccion = new Izquierda();
                columnaActual = columnaSiguiente;
                pasarela = ParcelaFactory.obtenerParcela("Pasarela", new Posicion(filaSiguiente + 1, columnaSiguiente + 1));
                ((Pasarela) pasarela).establecerDireccion(direccion);
                parcelas.add(pasarela);
                columnaSiguiente--;
            } else if ((columnaSiguiente + 1 < mapeo[0].length) && ((columnaSiguiente + 1) != columnaActual) && mapeo[filaSiguiente][columnaSiguiente + 1].equals("Pasarela")) {
                direccion = new Derecha();
                columnaActual = columnaSiguiente;
                pasarela = ParcelaFactory.obtenerParcela("Pasarela", new Posicion(filaSiguiente + 1, columnaSiguiente + 1));
                ((Pasarela) pasarela).establecerDireccion(direccion);
                parcelas.add(pasarela);
                columnaSiguiente++;
            } else {
                meta = ParcelaFactory.obtenerParcela("Meta", new Posicion(filaSiguiente + 1, columnaSiguiente + 1));
                parcelas.add(meta);
            }
        }
    }

    private void procesarParcelas(String[][] mapeo, List<Parcela> parcelas) {
        cargarPasarelas(mapeo, parcelas);

        for(int i = 0; i < mapeo.length; i++) {
            for(int j = 0; j < mapeo[0].length; j++) {
                if(!mapeo[i][j].equals("Pasarela")) {
                    Posicion pos = new Posicion(i + 1, j + 1);
                    parcelas.add(ParcelaFactory.obtenerParcela(mapeo[i][j], pos));
                }
            }
        }
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
            int contadorColumna;
            int cantidadColumnas = ((JSONArray)mapaJson.get(filas[1])).size();
            String mapeo [][] = new String[filas.length][cantidadColumnas];

            for(int i = 0; i < filas.length; i++) {
                contadorColumna = 0;
                JSONArray columna = (JSONArray) mapaJson.get(filas[i]);
                for (Object parcela : columna) {
                    mapeo[Integer.parseInt((String) filas[i])-1][contadorColumna] = (String) parcela;
                    contadorColumna++;
                }
            }

            this.procesarParcelas(mapeo, parcelas);


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
