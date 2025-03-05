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
                return iniciarPartidaDos();
            }
            case "2" -> {
                return iniciarPartidaCuatro();
            }
            case "3" -> cargarPartida();

            case "4" -> mostrarJugadores();
            case "5" -> mostrarTop();

        }
        return this;
    }

    private Flujo iniciarPartidaDos() {
        if (controlador.getCantidadJugadores() >= 2) {
            controlador.iniciarPartida();
            if (controlador.isJugadorTurno()) {
                return new FlujoTomarFicha(vista, controlador);
            } else {
                return new FlujoVacio(vista, controlador);
            }
        } else {
            vista.appendColor("\nLa partida no puede comenzar, ya que faltan jugadores en la partida\n", ColorRGB.RED);
            return new FlujoMenuPrincipal(vista, controlador);
        }
    }

    private Flujo iniciarPartidaCuatro() {
        if (controlador.getCantidadJugadores() >= 4) {
            controlador.iniciarPartida();
            if (controlador.isJugadorTurno()) {
                return new FlujoTomarFicha(vista, controlador);
            } else {
                return new FlujoVacio(vista, controlador);
            }
        } else {
            vista.appendColor("\nLa partida no puede comenzar, ya que faltan jugadores en la partida\n", ColorRGB.RED);
            return new FlujoMenuPrincipal(vista, controlador);
        }
    }

    private void cargarPartida() {
        if (!controlador.cargarPartida()) {
            vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
            vista.appendColor("No hay partida guardada para cargar", ColorRGB.RED);
        }
    }

    private void mostrarJugadores() {
        vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        vista.appendColor(" Jugadores conectados:\n", ColorRGB.CYAN);
        vista.appendColor(controlador.mostrarJugadores(), ColorRGB.RED);
    }

    private void mostrarTop() {
        vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        vista.appendColor(" Mejores Jugadores:\n", ColorRGB.CYAN);
        String topJugadores = controlador.deserializar();
        if (!topJugadores.isEmpty()) {
            vista.appendColor(topJugadores, ColorRGB.ORANGE);
        } else {
            vista.appendColor(" El top de jugadores se encuentra vacio.", ColorRGB.RED);
        }
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  ============================================================================\n", ColorRGB.CYAN);
        vista.appendColor(" |                                   BURAKO                                   |\n", ColorRGB.CYAN);
        vista.appendColor("  ============================================================================\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  1   Iniciar partida 2 jugadores\n", ColorRGB.CYAN);
        vista.appendColor("  2   Iniciar partida 4 jugadores\n", ColorRGB.CYAN);
        vista.appendColor("  3   Cargar partida\n", ColorRGB.CYAN);
        vista.appendColor("  4   Mostrar jugadores\n", ColorRGB.CYAN);
        vista.appendColor("  5   Mostrar puntos\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  0   Salir\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  Por favor seleccione una opción: ", ColorRGB.CYAN);
    }

}
