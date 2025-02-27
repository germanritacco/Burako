package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.vista.ColorRGB;

import java.awt.*;

public class FlujoPozo extends Flujo {

    public FlujoPozo(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    @Override
    public Flujo procesarEntrada(String string) {
        try {
            if (string.equals("0")) {
                return new FlujoJugada(vista, controlador);
            }
            int opcion = Integer.parseInt(string);
            if (opcion >= 1 && opcion <= controlador.cantidadFichasAtril()) {
                controlador.agregarFichaPozo(opcion - 1);
                if (controlador.atrilVacio() && !controlador.isMuertoTomado()) {
                    controlador.tomarMuerto();
                    vista.appendColor(" Atril Vacío. Se ha tomado el muerto.", ColorRGB.GREEN);
                }
                if (controlador.isCanasta() && controlador.isMuertoTomado() && controlador.atrilVacio()) {
                    return new FlujoPartidaTerminada(vista, controlador);
                }
                return new FlujoEsperarTurno(vista, controlador);
            }
        } catch (NumberFormatException e) {
            vista.appendColor("ERROR: Numero de ficha no valido.", ColorRGB.RED);
        }
        return this;
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.mostrarAtril(controlador.mostrarAtril());
        vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  0  Volver al menu anterior\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  Por favor seleccione la ficha que desea dejar en el pozo: ", ColorRGB.CYAN);
    }

}
