package edu.fiuba.algo3.controllers;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ControladorDeSonido {

    private static ControladorDeSonido instancia;
    private final HashMap<String, MediaPlayer> efectosDeSonido;
    private final HashMap<String, MediaPlayer> musica;


    private final double volumenDefault = 0.5;
    private MediaPlayer reproduccionActual;

    private MediaPlayer efecto;

    private ControladorDeSonido(){
        efectosDeSonido = new HashMap<>();
        musica = new HashMap<>();
        cargar("efectos", efectosDeSonido);
        cargar("musica", musica);
    }

    public static ControladorDeSonido getInstance() {
        if(instancia != null) {
            return instancia;
        }
        instancia = new ControladorDeSonido();
        return instancia;
    }

    public void reproducirEfecto(String identificador) {
        if(!efectosDeSonido.containsKey(identificador)){
            throw new RuntimeException("No se puede identificar el efecto de sonido");
        }

        if(efecto != null){
            efecto.stop();
        }

        efecto = efectosDeSonido.get(identificador);
        efecto.play();
    }

    public void reproducirMusica(String identificador) {
        if(!musica.containsKey(identificador)){
            throw new RuntimeException("No se puede identificar el efecto de sonido");
        }

        if(reproduccionActual != null){
            reproduccionActual.stop();
        }

        reproduccionActual = musica.get(identificador);
        reproduccionActual.setOnEndOfMedia(() -> reproduccionActual.seek(Duration.ZERO));
        reproduccionActual.play();
    }

    private void cargar(String carpeta, HashMap<String, MediaPlayer> mediaPlayerList){
        File directorio = new File("src/main/resources/sonidos/" + carpeta);
        if(!directorio.exists() || !directorio.isDirectory()){
            throw new RuntimeException("Error: No se encontro la carpeta de sonidos!");
        }
        File[] archivos = directorio.listFiles();
        if(archivos == null){
            throw new RuntimeException("Error: No se encontraron efectos de sonidos dentro de la carpeta de sonidos/efectos/");
        }
        for(File archivo : archivos){
            URL mediaUrl = Objects.requireNonNull(getClass().getResource("/sonidos/" + carpeta + "/" + archivo.getName()));
            Media media = new Media(mediaUrl.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(volumenDefault);
            mediaPlayerList.put(archivo.getName(), mediaPlayer);
        }
    }

    public void modificarVolumenMusica(double unVolumen) {
        if(unVolumen >= 0 && unVolumen <= 1){
            for(MediaPlayer mediaPlayer : musica.values()){
                mediaPlayer.setVolume(unVolumen);
            }
        }
    }

    public void modificarVolumenEfecto(double unVolumen) {
        if(unVolumen >= 0 && unVolumen <= 1) {
            for (MediaPlayer mediaPlayer : efectosDeSonido.values()) {
                mediaPlayer.setVolume(unVolumen);
            }
        }
    }

    public void play() {
        if(reproduccionActual.getStatus() == MediaPlayer.Status.PLAYING){
            reproduccionActual.pause();
        } else {
            reproduccionActual.play();
        }
    }

    public void siguienteCancion() {
        reproduccionActual.stop();
        List<MediaPlayer> canciones = new ArrayList<>(musica.values());

        reproduccionActual = canciones.get((canciones.indexOf(reproduccionActual) + 1 ) % canciones.size());

        reproduccionActual.play();

    }

    public void cancionAnterior() {
        reproduccionActual.stop();

        List<MediaPlayer> canciones = new ArrayList<>(musica.values());

        int posicionCancionActual = canciones.indexOf(reproduccionActual);
        int posicionCancionAnterior = posicionCancionActual - 1 < 0 ? canciones.size() - 1 : posicionCancionActual - 1;

        reproduccionActual = canciones.get(posicionCancionAnterior);
        reproduccionActual.play();

    }
    public void detenerEfecto(){
        if(efecto != null){
            efecto.stop();
        }
    }

    public double obtenerVolumen() {
        return reproduccionActual.volumeProperty().doubleValue();
    }


}
