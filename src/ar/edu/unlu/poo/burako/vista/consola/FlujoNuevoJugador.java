package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.vista.ColorRGB;

import java.awt.*;

public class FlujoNuevoJugador extends Flujo {

    public FlujoNuevoJugador(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    @Override
    public Flujo procesarEntrada(String string) {
        controlador.nuevoJugador(string);
        return new FlujoMenuPrincipal(vista, controlador);
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  ============================================================================\n", ColorRGB.CYAN);
        vista.appendColor(" |                                   BURAKO                                   |\n", ColorRGB.CYAN);
        vista.appendColor("  ============================================================================\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  Ingrese nombre de jugador: ", ColorRGB.CYAN);
    }

}
