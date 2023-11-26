package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;


public class FlujoBajarJuego extends Flujo {

    private String[] seleccion;

    public FlujoBajarJuego(VistaConsola vista, Controlador controlador, String[] seleccion) {
        super(vista, controlador);
        this.seleccion = seleccion;
    }

    /**
     * @param string
     * @return
     */
    @Override
    public Flujo procesarEntrada(String string) {
        try {
            if (controlador.agregarNuevaJugada(seleccion)) {
                vista.appendColor(" Jugada bajada a la mesa correctamente.", Color.GREEN);
            } else {
                vista.appendColor(" Jugada no válida.", Color.RED);
            }
        } catch (NumberFormatException e) {
            vista.appendColor("Error: La cadena no es un número válido.", Color.RED);
        }
        return new FlujoJugada(vista, controlador);
    }

    /**
     *
     */
    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor(" ------------------------------------------------------------------------------\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  Por favor seleccione las fichas que desea agregar como jugada,", Color.CYAN);
        vista.appendColor("  separado por ',' (Ej: '1,3,4,5'): ", Color.CYAN);
    }
}
