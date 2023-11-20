package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;

public class FlujoRepartirFichas extends Flujo {
    public FlujoRepartirFichas(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    /**
     * @param string
     * @return
     */
    @Override
    public Flujo procesarEntrada(String string) {

        return new FlujoMenuPrincipal(vista, controlador);
    }

    /**
     *
     */
    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor("PASO POR ACA", Color.RED);
    }
}
