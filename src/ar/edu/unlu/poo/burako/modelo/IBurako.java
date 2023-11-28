package ar.edu.unlu.poo.burako.modelo;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IBurako extends IObservableRemoto {

    Jugador setJugador(String nombre) throws RemoteException;

    void desconectarUsuario(Jugador jugador) throws RemoteException;

    void cerrar(IObservadorRemoto controlador, int jugadorId) throws RemoteException;

    String getJugadores() throws RemoteException;

    void enviarMensajeDelSistema(String mensaje) throws RemoteException;

    String getMensajeSistema() throws RemoteException;

    void repartirFichas() throws RemoteException;

    ArrayList<String> getFichas(int jugadorId) throws RemoteException;

    ArrayList<String> mostrarPozo() throws RemoteException;

    void recogerFichaMazo(int jugadorId) throws RemoteException;

    Integer cantidadFichasAtril(int jugadorId) throws RemoteException;

    void abandonarPartida() throws RemoteException;

    Boolean hayJuegosMesa(int jugadorId) throws RemoteException;

    boolean agregarNuevaJugada(int jugadorId, String[] seleccion) throws RemoteException;

    int cantidadJuegosMesa(int jugadorId) throws RemoteException;

    boolean agregarFichaJugadaExistente(int jugadorId, String[] seleccion, int posicion) throws RemoteException;

    void agregarFichaPozo(int jugadorId, int posicion) throws RemoteException;

    boolean isJugadorActual(int jugadorId) throws RemoteException;

    void cambiarTurno(int jugadorId) throws RemoteException;

    String mostrarTurno(int jugadorId) throws RemoteException;

    void comenzarPartida() throws RemoteException;

    boolean isPozoVacio() throws RemoteException;

    void recogerPozo(int jugadorId) throws RemoteException;

    String nombreJugadorTurno() throws RemoteException;

    ArrayList<ArrayList<String>> mostrarJuegosEnMesa(int jugadorId) throws RemoteException;
}
