package edu.fiuba.algo3.view;

import edu.fiuba.algo3.modelo.Mapa.Posicion;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.util.HashMap;


public class ElementosMapaObservable {
    HashMap<ImageView, Integer> trampasConstruidas;
    HashMap<Posicion, ImageView> torresConstruidas;
    private HashMap<ImageView, Button> botonesConstruccion;

    public ElementosMapaObservable() {
        trampasConstruidas = new HashMap<>();
        torresConstruidas = new HashMap<>();
        botonesConstruccion = new HashMap<>();
    }

    public void agregarImagenTrampa(ImageView imagenTrampa, Button botonConstruir) {
        trampasConstruidas.put(imagenTrampa, 3);
        botonesConstruccion.put(imagenTrampa, botonConstruir);
    }

    public void agregarImagenTorre(ImageView imagenTorre, Button botonConstruir, Posicion unaPosicion) {
        torresConstruidas.put(unaPosicion, imagenTorre);
        botonesConstruccion.put(imagenTorre, botonConstruir);
    }

    public void actualizarTrampas(GridPane mapaGrid) {
        if (trampasConstruidas.size() == 0) {
            return;
        }
        for (ImageView unImage : trampasConstruidas.keySet()) {
            int tiempoDeConstruccion = trampasConstruidas.get(unImage);
            if (tiempoDeConstruccion == 0) {
                botonesConstruccion.get(unImage).setMouseTransparent(false);
                botonesConstruccion.remove(unImage);
                mapaGrid.getChildren().remove(unImage);
                trampasConstruidas.remove(unImage);
            } else {
                tiempoDeConstruccion--;
                trampasConstruidas.put(unImage, tiempoDeConstruccion);
            }
        }
    }

    public void eliminarTorre(Posicion unaPosicion, GridPane mapaGrid) {
        ImageView imagen = torresConstruidas.get(unaPosicion);
        mapaGrid.getChildren().remove(imagen);
        botonesConstruccion.get(imagen).setMouseTransparent(false);
        botonesConstruccion.remove(imagen);
        torresConstruidas.remove(unaPosicion);
    }

}
