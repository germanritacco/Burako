package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;

public class FlujoAbandonarPartida extends Flujo {

    private String jugador;

    public FlujoAbandonarPartida(VistaConsola vista, Controlador controlador, String jugador) {
        super(vista, controlador);
        this.jugador = jugador;
    }

    /**
     * @param string
     * @return
     */
    @Override
    public Flujo procesarEntrada(String string) {
        return null;
    }

    /**
     *
     */
    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor(" ------------------------------------------------------------------------------\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  ¡PARTIDA TERMINADA!", Color.RED);
        vista.appendColor("  El " + jugador + " ha terminado la partida.", Color.RED);
        vista.appendColor("  Volveras al menu principal", Color.ORANGE);
        vista.mostrarMenuPrincipal();
        vista.enableComponents();
    }
}
