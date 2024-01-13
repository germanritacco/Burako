package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.vista.ColorRGB;

import java.awt.*;

public class FlujoEsperarTurno extends Flujo {

    public FlujoEsperarTurno(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    @Override
    public Flujo procesarEntrada(String string) {
        return null;
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.disableComponents();
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        vista.mostrarTurno(controlador.nombreJugadorTurno());
        vista.appendColor("\n                               ¡AGUARDE SU TURNO!", ColorRGB.RED);
    }

}
