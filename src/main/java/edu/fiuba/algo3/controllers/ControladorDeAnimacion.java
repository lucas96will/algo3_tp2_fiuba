package edu.fiuba.algo3.controllers;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ControladorDeAnimacion {

    private StackPane display;
    private int cantidadDeFrames;
    private int duracionFrame;
    private Duration duracionTotal;

    private ImageView sprite;
    private int frameActual = 0;
    private long tiempoUltimoFrame = 0;
    private AnimationTimer animador;
    private Image frames[];
    private long tiempoDeInicio;

    public ControladorDeAnimacion(int cantidadDeFrames, int duracionFrame, int duracion, ImageView sprite, String directorio, StackPane container){
        display = container;
        this.cantidadDeFrames = cantidadDeFrames;
        this.duracionTotal = Duration.seconds(duracion);
        this.duracionFrame = duracionFrame;
        this.sprite = sprite;
        display.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        display.getChildren().add(sprite);
        frames = new Image[cantidadDeFrames];

        for(int i = 0; i< cantidadDeFrames; i++){
            frames[i] = new Image(getClass().getResource("/Animations/"+ directorio +"/tile" + i + ".png").toString());
        }

        animador = new AnimationTimer() {

            @Override
            public void handle(long ahora) {
                if(tiempoDeInicio == 0){
                    tiempoDeInicio = ahora;
                } else{
                    long tiempoTranscurrido = ahora - tiempoDeInicio;
                    if(tiempoTranscurrido >= duracionTotal.toMillis() * 1000000){
                        display.getChildren().clear();
                        display.setVisible(false);
                        stop();
                    }
                }
                if(ahora - (tiempoUltimoFrame) >= duracionFrame * 1000000){
                    sprite.setImage(frames[frameActual]);

                    frameActual = (frameActual + 1) % cantidadDeFrames;
                    tiempoUltimoFrame = ahora;
                }
            }
        };
    }

    public void start(){
        display.setMouseTransparent(false);
        display.setVisible(true);
        animador.start();
    }

}
