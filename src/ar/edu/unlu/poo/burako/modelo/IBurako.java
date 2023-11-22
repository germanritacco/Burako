package ar.edu.unlu.poo.burako.modelo;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IBurako extends IObservableRemoto {

    void setJugador(String nombre) throws RemoteException;

    Jugador getJugador(String nombre) throws RemoteException;

    void desconectarUsuario(Jugador jugador) throws RemoteException;

    void cerrar(IObservadorRemoto controlador, String jugador) throws RemoteException;

    String getJugadores() throws RemoteException;

    void enviarMensajeDelSistema(String mensaje) throws RemoteException;

    String getMensajeSistema() throws RemoteException;

    void repartirFichas() throws RemoteException;

    ArrayList<String> getFichas(String nombre) throws RemoteException;

    void mostrarJugadores() throws RemoteException;
}
