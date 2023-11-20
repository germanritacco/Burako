package ar.edu.unlu.poo.burako.modelo;

import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Burako extends ObservableRemoto implements IBurako {

    private Mazo mazo;

    private final ArrayList<Jugador> jugadores;

    private String mensajeSistema;

    public Burako() {
        jugadores = new ArrayList<>();
    }

    @Override
    public String getMensajeSistema() throws RemoteException {
        return mensajeSistema;
    }

    public void cerrar(IObservadorRemoto controlador, String jugador) throws RemoteException {
        this.removerObservador(controlador);
        this.desconectarUsuario(getJugador(jugador));
    }

    @Override
    public void desconectarUsuario(Jugador jugador) throws RemoteException {
        this.enviarMensajeDelSistema("El jugador " + jugador.getNombre() + " se ha retirado de la partida");
        jugadores.remove(jugador);
    }

    public String getJugadores() throws RemoteException {
        String texto = "";
        for (Jugador jugador : this.jugadores) {
            texto += " • " + jugador.getNombre() + "\n";
        }
        return texto;
    }

    public void mostrarJugadores() throws RemoteException {
        this.notificarObservadores(Eventos.MOSTRAR_JUGADORES);
    }

    public ArrayList<String> getFichas(String nombre) throws RemoteException {
        ArrayList<String> fichas = new ArrayList<>();
        for (Jugador jugador : this.jugadores) {
            if (jugador.getNombre().equals(nombre)) {
                for (Ficha ficha : jugador.getAtril()) {
                    if (ficha instanceof Comodin) {
                        fichas.add("COMODIN");
                    } else {
                        fichas.add(ficha.toString());
                    }
                }
            }
        }
        return fichas;
    }

    public Jugador getJugador(String nombre) throws RemoteException {
        Jugador jugadorEncontrado = null;
        for (Jugador jugador : this.jugadores) {
            if (jugador.getNombre().equals(nombre)) {
                jugadorEncontrado = jugador;
            }
        }
        return jugadorEncontrado;
    }

    public void setJugador(String nombre) throws RemoteException {
        Jugador nuegoJugador = new Jugador(nombre);
        this.jugadores.add(nuegoJugador);
        //this.notificarObservadores(Eventos.JUGADOR);
        this.enviarMensajeDelSistema("El jugador " + nombre + " se ha unido a la partida");
    }

    @Override
    public void enviarMensajeDelSistema(String mensaje) throws RemoteException {
        this.mensajeSistema = mensaje;
        this.notificarObservadores(Eventos.NUEVO_MENSAJE);
    }

    public void repartirFichas() throws RemoteException {
        switch (jugadores.size()) {
            case 2 -> this.mazo = new Mazo(jugadores.get(0), jugadores.get(1));
            case 4 -> this.mazo = new Mazo(jugadores.get(0), jugadores.get(1), jugadores.get(2), jugadores.get(3));
        }
        this.notificarObservadores(Eventos.MOSTRAR_ATRIL);
    }

    private void tomarMuerto(Jugador jugador) {
        if (!mazo.getMuerto().isEmpty()) {
            jugador.setAtril(mazo.sacarMuerto());
        }
    }

}
