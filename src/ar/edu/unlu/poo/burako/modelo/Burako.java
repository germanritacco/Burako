package ar.edu.unlu.poo.burako.modelo;

import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Burako implements IBurako {

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

    @Override
    public String getTxt() throws RemoteException {
        return txt;
    }

    @Override
    public void setTxt(String txt) throws RemoteException {
        this.txt = txt;
        notificarObservadores();
    }

    @Override
    public void agregarObservador(IObservadorRemoto iObservadorRemoto) throws RemoteException {

    }

    @Override
    public void removerObservador(IObservadorRemoto iObservadorRemoto) throws RemoteException {

    }

    @Override
    public void notificarObservadores(Object o) throws RemoteException {

    }

    @Override
    public void notificarObservadores() throws RemoteException {

    }

    /* public void notificarObservadores() {
        for (Observer observador : misObservadores)
            observador.notificarCambio(getTxt());
    }*/

    public void addObserver(Observer observador) {
        misObservadores.add(observador);
    }

}
