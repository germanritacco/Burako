package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;

public class FlujoNuevoJugador extends Flujo {

    public FlujoNuevoJugador(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    /**
     * @param string
     * @return
     */
    @Override
    public Flujo procesarEntrada(String string) {
        controlador.nuevoJugador(string);
        return new FlujoMenuPrincipal(vista, controlador);
    }

    /**
     *
     */
    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  ============================================================================\n", Color.CYAN);
        vista.appendColor(" |                                   BURAKO                                   |\n", Color.CYAN);
        vista.appendColor("  ============================================================================\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  Ingrese nombre de jugador: ", Color.CYAN);
    }
}
