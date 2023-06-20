package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorDeJuego implements Initializable {

    @FXML private GridPane mapaGrid;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Parcela> terreno = Juego.getInstance().obtenerParcelas();
        terreno.forEach(parcela -> {
            URL url = getClass().getResource("/images/"+ parcela.getClass().getSimpleName() + ".png");
            ImageView parcelaBackground = new ImageView();
            Image image = new Image(url.toString());
            parcelaBackground.setImage(image);
            parcelaBackground.setFitHeight(50);
            parcelaBackground.setFitWidth(50);
            mapaGrid.add(parcelaBackground,parcela.obtenerPosicion().obtenerColumna(),parcela.obtenerPosicion().obtenerFila());
        });
    }


}
