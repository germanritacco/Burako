package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;

public class FlujoTurno extends Flujo {
    public FlujoTurno(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    /**
     * @param string
     * @return
     */
    @Override
    public Flujo procesarEntrada(String string) {
        switch (string){
            case "1"-> {
                    controlador.tomarFichaMazo();
                    return new FlujoJugada(vista, controlador);
            }
        }
        return this;
    }

    /**
     *
     */
    @Override
    public void mostrarSiguienteTexto() {
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
