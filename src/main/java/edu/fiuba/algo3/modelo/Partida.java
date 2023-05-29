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
    public void terminarTurno(){}
    public void construir(){}
    public boolean terminarPartida(){
        return jugador.muerto();
    }
    public boolean jugadorTieneTodaLaVidaYMaximosCreditos(){
        return jugador.estaIntacto();
    }

}
