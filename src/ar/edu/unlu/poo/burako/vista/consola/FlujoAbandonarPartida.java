package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.vista.ColorRGB;

import java.awt.*;

public class FlujoAbandonarPartida extends Flujo {

    private final String jugador;

    public FlujoAbandonarPartida(VistaConsola vista, Controlador controlador, String jugador) {
        super(vista, controlador);
        this.jugador = jugador;
    }

    @Override
    public Flujo procesarEntrada(String string) {
        return null;
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  ¡PARTIDA TERMINADA!\n", ColorRGB.RED);
        if (!controlador.isJugadorTurno()) {
            vista.appendColor("  El jugador " + jugador + " ha terminado la partida.\n", ColorRGB.RED);
        }
        vista.appendColor("  Volveras al menu principal", ColorRGB.ORANGE);
        vista.mostrarMenuPrincipal();
        vista.enableComponents(false);
    }

}
