package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;

public class FlujoPozo extends Flujo {
    public FlujoPozo(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    /**
     * @param string
     * @return
     */
    @Override
    public Flujo procesarEntrada(String string) {
        try {
            int opcion = Integer.parseInt(string);
            if (opcion >= 1 && opcion <= controlador.cantidadFichasAtril()) {
              controlador.agregarFichaPozo(opcion);
              controlador.cambiarTurno();

            }
        }

        return this;
    }

    /**
     *
     */
    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor(" ------------------------------------------------------------------------------\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  Por favor seleccione la ficha que desea dejar en el pozo: ", Color.CYAN);

    }
}
