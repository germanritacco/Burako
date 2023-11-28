package ar.edu.unlu.poo.burako.vista;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.util.ArrayList;

public interface IVista {

    void setControlador(Controlador controlador);

    void iniciar();

    void mostrarTexto(String txt);

    void nuevoJugador();

    void abandonarPartida(String nombre);

    void disableComponents();

    void enableComponents();

    void mostrarTurno(String jugador);

    void iniciarPartida(ArrayList<String> atril, ArrayList<String> pozo, String nombreJugador);

    void mostrarAtril(ArrayList<String> atril);

    void mostrarJuegosMesa(ArrayList<ArrayList<String>> atril);

    void mostrarPozo(ArrayList<String> pozo);
}
