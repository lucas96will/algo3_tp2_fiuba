package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.view.BotonVolverAEmpezar;
import edu.fiuba.algo3.view.PantallaInicio;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorPantallaFinal implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Text resultado;
    private Button boton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarBotonEmpezar();
    }

    public EventHandler volverAEmpezar () {
        return event -> {
            new PantallaInicio(App.getInstance(), App.obtenerStage());
        };
    }

    public void configurarMensajeFinal(String mensaje) {
        resultado.setText(mensaje);
    }

    private void configurarBotonEmpezar() {
        boton = BotonVolverAEmpezar.fijarBotonVolverAEmpezar(this, "Volver a empezar");
        anchorPane.getChildren().add(boton);
    }
}
