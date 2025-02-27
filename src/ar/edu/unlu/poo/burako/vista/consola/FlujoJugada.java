package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.vista.ColorRGB;

import java.awt.*;

public class FlujoJugada extends Flujo {

    public FlujoJugada(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    @Override
    public Flujo procesarEntrada(String string) {
        switch (string) {
            case "1" -> {
                return new FlujoSeleccionarFichas(vista, controlador, false);
            }
            case "2" -> {
                if (controlador.cantidadFichasAtril().equals(1) && !controlador.isCanasta()) {
                    vista.appendColor("ERROR: No posee canasta en mesa,\nNo puede cerrar partida aun!", ColorRGB.RED);
                } else {
                    return agregarfichasJuegoMesa();
                }
            }
            case "3" -> {
                return new FlujoPozo(vista, controlador);
            }
            case "0" -> {
                String nombreJugador = controlador.abandonarPartida();
                return new FlujoAbandonarPartida(vista, controlador, nombreJugador);
            }
            default -> vista.appendColor("Opcion incorrecta", ColorRGB.RED);
        }
        return this;
    }

    private Flujo agregarfichasJuegoMesa() {
        if (controlador.hayJuegosMesa()) {
            return new FlujoSeleccionarFichas(vista, controlador, true);
        } else {
            vista.appendColor("ERROR: No hay jugadas existentes en mesa", ColorRGB.RED);
        }
        return this;
    }

    @Override
    public void mostrarSiguienteTexto() {

        vista.mostrarAtril(controlador.mostrarAtril());
        vista.mostrarJuegosMesa(controlador.mostrarJuegosEnMesa());
        vista.appendColor("\n ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        vista.appendColor("  1   Bajar juego a la mesa\n", ColorRGB.CYAN);
        vista.appendColor("  2   Agregar ficha a juego existente en  la mesa\n", ColorRGB.CYAN);
        vista.appendColor("  3   Devolver ficha a pozo\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  0   Abandonar partida\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  Por favor seleccione una opción: ", ColorRGB.CYAN);
    }

}
