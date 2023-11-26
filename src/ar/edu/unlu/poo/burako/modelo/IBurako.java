package ar.edu.unlu.poo.burako.modelo;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IBurako extends IObservableRemoto {

    Jugador setJugador(String nombre) throws RemoteException;

    Jugador getJugador(String nombre) throws RemoteException;

    void desconectarUsuario(Jugador jugador) throws RemoteException;

    void cerrar(IObservadorRemoto controlador, String jugador) throws RemoteException;

    String getJugadores() throws RemoteException;

    void enviarMensajeDelSistema(String mensaje) throws RemoteException;

    String getMensajeSistema() throws RemoteException;

    void repartirFichas() throws RemoteException;

    ArrayList<String> getFichas(Jugador jugador) throws RemoteException;

    void mostrarJugadores() throws RemoteException;

    ArrayList<String> mostrarPozo() throws RemoteException;

    void recogerFichaMazo(String nombre) throws RemoteException;

    Integer cantidadFichasAtril(String nombre) throws RemoteException;

    void abandonarPartida() throws RemoteException;

    Boolean hayJuegosMesa(Jugador jugador) throws RemoteException;

    boolean agregarNuevaJugada(Jugador jugador, String[] seleccion) throws RemoteException;

    int cantidadJuegosMesa(Jugador jugador);

    boolean agregarFichaJugadaExistente(Jugador jugador, String[] seleccion, int posicion) throws RemoteException;

    void agregarFichaPozo(Jugador jugador, int posicion)  throws RemoteException;
}
