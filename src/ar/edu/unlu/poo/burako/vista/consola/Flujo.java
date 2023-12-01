package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

public abstract class Flujo {

    protected final VistaConsola vista;
    protected final Controlador controlador;

    public Flujo(VistaConsola vista, Controlador controlador) {
        this.vista = vista;
        this.controlador = controlador;
    }

    /**
     * Procesa el dato ingresado por el usuario
     *
     * @param string Dato ingresado por el usuario
     * @return Instancia del siguiente flujo.
     */
    public abstract Flujo procesarEntrada(String string);

    /**
     * Texto a mostrar en la vista actual.
     */
    public abstract void mostrarSiguienteTexto();

}
