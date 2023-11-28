package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;

public class FlujoTomarFicha extends Flujo {
    public FlujoTomarFicha(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    /**
     * @param string
     * @return
     */
    @Override
    public Flujo procesarEntrada(String string) {
        switch (string) {
            case "1" -> {
                controlador.tomarFichaMazo();
                return new FlujoJugada(vista, controlador);
            }
            case "2" -> {
                if (!controlador.isPozoVacio()) {
                    controlador.recogerPozo();
                    return new FlujoJugada(vista, controlador);
                } else {
                    vista.appendColor(" ERROR: El pozo esta vacio.", Color.RED);
                }
            }
            case "0" -> {
                String nombreJugador = controlador.abandonarPartida();
                return new FlujoAbandonarPartida(vista, controlador, nombreJugador);
            }
            default -> vista.appendColor("Opcion incorrecta", Color.RED);
        }
        return this;
    }

    /**
     *
     */
    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor(" ------------------------------------------------------------------------------\n", Color.CYAN);
        vista.appendColor("  1   Tomar una ficha de la pila\n", Color.CYAN);
        vista.appendColor("  2   Tomar fichas del pozo\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  0   Abandonar partida\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor(" ------------------------------------------------------------------------------\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  Por favor seleccione una opción: ", Color.CYAN);
    }

}
