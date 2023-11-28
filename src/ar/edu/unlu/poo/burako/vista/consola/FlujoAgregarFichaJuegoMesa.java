package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;

public class FlujoAgregarFichaJuegoMesa extends Flujo {

    private String[] seleccion;

    public FlujoAgregarFichaJuegoMesa(VistaConsola vista, Controlador controlador, String[] seleccion) {
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
            int opcion = Integer.parseInt(string);
            if (opcion >= 1 && opcion <= controlador.cantidadJuegosMesa()) {
                if (controlador.agregarFichaJugadaExistente(opcion, seleccion)) {
                    vista.appendColor(" Fichas agregadas a juego en mesa correctamente.", Color.GREEN);
                } else {
                    vista.appendColor(" Fichas no válidas para agregar a juego en mesa.", Color.RED);
                }
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
        vista.mostrarJuegosMesa(controlador.mostrarJuegosEnMesa());
        vista.appendColor(" ------------------------------------------------------------------------------\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  Por favor seleccione la jugada en mesa a la cual desea agregar fichas: ", Color.CYAN);
    }
}
