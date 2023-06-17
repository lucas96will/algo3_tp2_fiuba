package edu.fiuba.algo3.modelo.Mapa;

import edu.fiuba.algo3.modelo.Excepciones.NoSePuedeIdentificarLaMetaDelMapaException;
import edu.fiuba.algo3.modelo.Parcela.Parcela;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcela.Pasarela.Pasarela;

import java.util.List;
import java.util.stream.Collectors;

public class DetectorExtremos{

    public void configurarCamino(List<Parcela> parcelas) throws Exception{
        List<Parcela> extremos = parcelas.stream()
                .filter(p-> p.esExtremo(parcelas))
                .collect(Collectors.toList());
        if(extremos.size() == 2) {
            Pasarela pasarelaObtenida = (Pasarela) extremos.get(0);
            pasarelaObtenida.establecerEstado(new Largada());
            pasarelaObtenida = (Pasarela) extremos.get(1);
            pasarelaObtenida.establecerEstado(new Meta());
        }
        else {
            throw new NoSePuedeIdentificarLaMetaDelMapaException("No se pudo identificar la meta de los enemigos, el mapa recibido es inválido");
        }
    }
}
