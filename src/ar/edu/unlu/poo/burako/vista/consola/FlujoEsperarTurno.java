package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;

public class FlujoEsperarTurno extends Flujo {
    public FlujoEsperarTurno(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    /**
     * @param string
     * @return
     */
    @Override
    public Flujo procesarEntrada(String string) {
        return null;
    }

    /**
     *
     */
    @Override
    public void mostrarSiguienteTexto() {
        vista.disableComponents();
        vista.appendColor("ESPERE SU TURNO!", Color.RED);

    }
}
