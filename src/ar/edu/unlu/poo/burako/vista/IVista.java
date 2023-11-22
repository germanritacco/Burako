package ar.edu.unlu.poo.burako.vista;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.util.ArrayList;

public interface IVista {

    void setControlador(Controlador controlador);

    void iniciar();

    void mostrarTexto(String txt);

    void nuevoJugador();

    void mostrarAtril(ArrayList<String> fichas);

}
