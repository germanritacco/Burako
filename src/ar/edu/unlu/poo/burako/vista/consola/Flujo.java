package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

public abstract class Flujo {

    protected final VistaConsola vista;
    protected final Controlador controlador;

    public Flujo(VistaConsola vista, Controlador controlador) {
        this.vista = vista;
        this.controlador = controlador;
    }

    public abstract Flujo procesarEntrada(String string);

    public abstract void mostrarSiguienteTexto();

}
