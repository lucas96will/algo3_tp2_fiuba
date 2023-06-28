package edu.fiuba.algo3.view;

import edu.fiuba.algo3.modelo.Cargador.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Partida.ContadorTurnos;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.stream.Collectors;

public class DatosJugadorObservable {
    StringProperty nombre;
    StringProperty vidaJugador;

    StringProperty credito;

    StringProperty turno;

    StringProperty cantDefensas;

    StringProperty cantEnemigos;

    StringProperty mensaje;

    StringProperty cantHormiga;
    StringProperty cantArania;
    StringProperty cantTopo;
    StringProperty cantLechuza;


    public DatosJugadorObservable() {
        nombre = new SimpleStringProperty();
        vidaJugador = new SimpleStringProperty();
        credito = new SimpleStringProperty();
        turno = new SimpleStringProperty();
        cantDefensas = new SimpleStringProperty();
        cantEnemigos = new SimpleStringProperty();
        mensaje = new SimpleStringProperty();
        cantHormiga = new SimpleStringProperty();
        cantArania = new SimpleStringProperty();
        cantTopo = new SimpleStringProperty();
        cantLechuza = new SimpleStringProperty();
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getVidaJugador() {
        return vidaJugador.get();
    }

    public StringProperty vidaJugadorProperty() {
        return vidaJugador;
    }

    public String getCredito() {
        return credito.get();
    }

    public StringProperty creditoProperty() {
        return credito;
    }

    public String getTurno() {
        return turno.get();
    }

    public StringProperty turnoProperty() {
        return turno;
    }

    public String getCantDefensas() {
        return cantDefensas.get();
    }

    public StringProperty cantDefensasProperty() {
        return cantDefensas;
    }

    public String getCantEnemigos() {
        return cantEnemigos.get();
    }

    public StringProperty cantEnemigosProperty() {
        return cantEnemigos;
    }

    public String getMensaje() {
        return mensaje.get();
    }

    public StringProperty mensajeProperty() {
        return mensaje;
    }

    public void setVidaJugador(String vidaJugador) {
        this.vidaJugador.set(vidaJugador);
    }

    public void setCredito(String credito) {
        this.credito.set(credito);
    }

    public void setTurno(String turno) {
        this.turno.set(turno);
    }

    public void setCantDefensas(String cantDefensas) {
        this.cantDefensas.set(cantDefensas);
    }

    public void setCantEnemigos(String cantEnemigos) {this.cantEnemigos.set(cantEnemigos);}

    public void setMensaje(String mensaje) {
        this.mensaje.set(mensaje);
    }
    public String getHormiga() {
        return cantHormiga.get();
    }
    public StringProperty cantHormigaProperty() {
        return cantHormiga;
    }
    public void setCantHormiga(String cantHormiga) {this.cantHormiga.set(cantHormiga);}
    public String getArania() {
        return cantArania.get();
    }
    public StringProperty cantAraniaProperty() {
        return cantArania;
    }
    public void setCantArania(String cantArania) {this.cantArania.set(cantArania);}
    public String getLechuza() {
        return cantLechuza.get();
    }
    public StringProperty cantLechuzaProperty() {
        return cantLechuza;
    }
    public void setCantLechuza(String cantLechuza) {this.cantLechuza.set(cantLechuza);}
    public String getTopo() {
        return cantTopo.get();
    }
    public StringProperty cantTopoProperty() {
        return cantTopo;
    }
    public void setCantTopo(String cantTopo) {this.cantTopo.set(cantTopo);}

    public void actualizar() {
        setVidaJugador(String.valueOf(Jugador.getInstance().obtenerVidaJugador()));
        setCredito(String.valueOf(Jugador.getInstance().valorCreditos()));
        setCantDefensas(String.valueOf(Jugador.getInstance().obtenerDefensas().size()));
        setTurno(String.valueOf(ContadorTurnos.obtenerContador().obtenerTurnoActual()));
        setCantEnemigos(String.valueOf(Juego.getInstance().obtenerEnemigos().size()));
        setCantHormiga(String.valueOf(Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Hormiga")).collect(Collectors.toList()).size()));
        setCantArania(String.valueOf(Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Arania")).collect(Collectors.toList()).size()));
        setCantTopo(String.valueOf(Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Topo")).collect(Collectors.toList()).size()));
        setCantLechuza(String.valueOf(Juego.getInstance().obtenerEnemigos().stream().filter(enemigo -> enemigo.getClass().getSimpleName().equals("Lechuza")).collect(Collectors.toList()).size()));
    }
}
