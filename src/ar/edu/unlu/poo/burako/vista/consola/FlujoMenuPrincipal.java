package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;

public class FlujoMenuPrincipal extends Flujo {

    public FlujoMenuPrincipal(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    @Override
    public Flujo procesarEntrada(String string) {
        switch (string) {
            case "1" -> test();
        }
        return this;
    }

    private void test() {
        controlador.escribirTexto();
        vista.appendColor(controlador.mostrarTexto(), Color.YELLOW);
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  ============================================================================\n", Color.CYAN);
        vista.appendColor(" |                                   BURAKO                                   |\n", Color.CYAN);
        vista.appendColor("  ============================================================================\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  2   Operaciones con colas\n", Color.CYAN);
        vista.appendColor("  3   Colas iguales\n", Color.CYAN);
        vista.appendColor("  4   Elementos no repetidos\n", Color.CYAN);
        vista.appendColor("  5   Divisores\n", Color.CYAN);
        vista.appendColor("  6   Lista con valores comunes\n", Color.CYAN);
        vista.appendColor("  7   Cola de clientes\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  0   Salir\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor(" ------------------------------------------------------------------------------\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  Por favor seleccione una opción: ", Color.CYAN);
    }

}
