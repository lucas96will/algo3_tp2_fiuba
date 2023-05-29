package edu.fiuba.algo3.modelo;

public class Partida {
    private Jugador jugador;
    private Mapa mapa;
    private Turno turno;

    public Partida(){}
    public void crearPartidaGenerica(String nombre){
        jugador = Jugador.crearJugadorBase(nombre);
        mapa = new Mapa(7);
        mapa.crearMapaGenerico();
        turno = new Turno();
    }
    public void comenzar(){}
    public void terminarTurno() {
        mapa.actualizarEstadoDefensas();
    }
    public void construir(Defensa defensa, int posicionFila, int posicionColumna){
        if(jugador.comprarDefensa(defensa)) {
            mapa.construir(defensa, posicionFila, posicionColumna);
        }
    }

    public boolean construccionTerminadaEn(int posicionFila, int posicionColumna) {
        return mapa.construccionTerminadaEn(posicionFila, posicionColumna);
    }

    public boolean terminarPartida(){
        return jugador.muerto();
    }
    public boolean jugadorTieneTodaLaVidaYMaximosCreditos(){
        return jugador.estaIntacto();
    }

}
