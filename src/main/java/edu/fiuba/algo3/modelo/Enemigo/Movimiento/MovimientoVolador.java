package edu.fiuba.algo3.modelo.Enemigo.Movimiento;
         
         import edu.fiuba.algo3.modelo.Enemigo.Enemigo;
         import edu.fiuba.algo3.modelo.Mapa.NullPosicion;
         import edu.fiuba.algo3.modelo.Mapa.Posicion;
         import edu.fiuba.algo3.modelo.Parcela.Parcela;
         import edu.fiuba.algo3.modelo.Partida.Logger;

         import java.util.List;

         public class MovimientoVolador implements Movimiento{

                 @Override
                 public void moverse(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {
                         //Moverse en L de Lechuza *badummtss*
                         int i = 0;
                         Parcela parcela = parcelas.get(i);
                         Posicion destino = parcela.orientacionCosmica();

                         while(!destino.equals(NullPosicion.obtenerNullPosicion()) && i < parcelas.size()){
                                 i++;
                                 parcelas.get(i);
                                 destino = parcela.orientacionCosmica();
                         } //Obtener posicion meta

                         //Una vez obtenida la posicion muevo la lechuza

                         posActual.acercarseHorizontalmente(destino);
                         if(posActual.estaEnLaMismaColumna(destino)){
                                 posActual.acercarseVerticalmente(destino);
                         }
                
                         Logger.getInstance().logExitoso( enemigo + " se movio a " + posActual);
                 }

        @Override
        public void moverseEnojado(List<Parcela> parcelas, Enemigo enemigo, Posicion posActual) {

        }
}