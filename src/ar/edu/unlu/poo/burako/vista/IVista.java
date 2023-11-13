package ar.edu.unlu.poo.burako.vista;

import ar.edu.unlu.poo.burako.controlador.Controlador;

public interface IVista {

    void setControlador(Controlador controlador);

    void iniciar();

    void mostrarTexto(String txt);

}
