package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;

public class FlujoPartidaTerminada extends Flujo {
    public FlujoPartidaTerminada(VistaConsola vista, Controlador controlador) {
        super(vista, controlador);
    }

    @Override
    public Flujo procesarEntrada(String string) {
        return null;
    }

    @Override
    public void mostrarSiguienteTexto() {
        controlador.finalizarPartida();
    }
}
