package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

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
                controlador.agregarFichaPozo(opcion);
                return new FlujoEsperarTurno(vista, controlador);
            }
        } catch (NumberFormatException e) {
            vista.appendColor("ERROR: Numero de ficha no valido.", Color.RED);
        }
        return this;
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.mostrarAtril(controlador.mostrarAtril());
        vista.appendColor(" ------------------------------------------------------------------------------\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  0  Volver al menu anterior\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  Por favor seleccione la ficha que desea dejar en el pozo: ", Color.CYAN);
    }

}
