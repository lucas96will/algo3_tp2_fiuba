package edu.fiuba.algo3.view;

import edu.fiuba.algo3.modelo.Parcela.Parcela;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ElementosMapaObservable {
    HashMap<ObjectProperty<Image>, Integer> trampasConstruidas;

    public ElementosMapaObservable() {
        trampasConstruidas = new HashMap<>();
    }

    public void agregarTrampaProperty(ObjectProperty<Image> imgTrampa){
        trampasConstruidas.put(imgTrampa, 3);
    }

    public void actualizarTrampas(){
        if(trampasConstruidas.size() == 0){
            return;
        }
        for(ObjectProperty<Image> unImage : trampasConstruidas.keySet()){
            int tiempoDeConstruccion = trampasConstruidas.get(unImage);
            if(tiempoDeConstruccion == 0){
                unImage.set(null);
            } else {
                tiempoDeConstruccion--;
                trampasConstruidas.put(unImage, tiempoDeConstruccion);
            }
        }
    }
}
