package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.vista.ColorRGB;

import java.awt.*;

public class FlujoMenuPrincipal extends Flujo {

    public FlujoMenuPrincipal(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    @Override
    public Flujo procesarEntrada(String string) {
        switch (string) {
            case "0" -> controlador.cerrarApp();
            case "1" -> {
                return iniciarPartida();
            }
            case "2" -> mostrarJugadores();
        }
        return this;
    }

    private void mostrarJugadores() {
        vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        vista.appendColor(" Jugadores conectados:\n", ColorRGB.CYAN);
        vista.appendColor(controlador.mostrarJugadores(), ColorRGB.RED);
    }

    private Flujo iniciarPartida() {
        controlador.iniciarPartida();
        if (controlador.isJugadorTurno()) {
            return new FlujoTomarFicha(vista, controlador);
        } else {
            return new FlujoVacio(vista, controlador);
        }
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  ============================================================================\n", ColorRGB.CYAN);
        vista.appendColor(" |                                   BURAKO                                   |\n", ColorRGB.CYAN);
        vista.appendColor("  ============================================================================\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  1   Iniciar partida\n", ColorRGB.CYAN);
        vista.appendColor("  2   Mostrar jugadores\n", ColorRGB.CYAN);
        vista.appendColor("  3   Mostrar puntos\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  0   Salir\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  Por favor seleccione una opción: ", ColorRGB.CYAN);
    }

}
