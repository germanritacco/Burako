package ar.edu.unlu.poo.burako.modelo;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IBurako extends IObservableRemoto {

    public void setJugador(String nombre) throws RemoteException;

    public Jugador getJugador(String nombre) throws RemoteException;

    public void desconectarUsuario(Jugador jugador) throws RemoteException;

    public void cerrar(IObservadorRemoto controlador, String jugador) throws RemoteException;

    public String getJugadores() throws RemoteException;

    public void enviarMensajeDelSistema(String mensaje) throws RemoteException;

    public String getMensajeSistema() throws RemoteException;

    public void repartirFichas() throws RemoteException;

    public ArrayList<String> getFichas(String nombre) throws RemoteException;

    public void mostrarJugadores() throws RemoteException;
}
