package ar.edu.unlu.poo.burako.controlador;

import ar.edu.unlu.poo.burako.modelo.Burako;
import ar.edu.unlu.poo.burako.modelo.Observer;
import ar.edu.unlu.poo.burako.vista.IVista;

public class Controlador implements Observer {

    private final IVista vista;

    private final Burako modelo;

    public Controlador(IVista vista, Burako modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setControlador(this);
        this.modelo.addObserver(this);
    }

    public void escribirTexto() {
        modelo.setTxt("Logre que funciona MVC & Observer!!! ");
    }

    public void notificarCambio(String txt) {
        vista.mostrarTexto(txt);
    }

}
