package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;
import java.util.List;

public class Burako implements Observable {

    private Mazo mazo;

    private final ArrayList<Observer> misObservadores = new ArrayList<>();

    private List<Jugador> jugadores;

    private String txt = "";

    private void agregarJugador() {
        Jugador jugador1 = new Jugador("Test1");
        jugadores.add(jugador1);
        Jugador jugador2 = new Jugador("Test2");
        jugadores.add(jugador2);
        this.mazo = new Mazo(jugador1, jugador2);
    }

    public Burako() {
        jugadores = new ArrayList<Jugador>();
        agregarJugador();
    }

    private void tomarMuerto(Jugador jugador) {
        if (!mazo.getMuerto().isEmpty()) {
            jugador.setAtril(mazo.sacarMuerto());
        }
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
        notificarObservadores();
    }

    public void notificarObservadores() {
        for (Observer observador : misObservadores)
            observador.notificarCambio(getTxt());
    }

    public void addObserver(Observer observador) {
        misObservadores.add(observador);
    }

}
