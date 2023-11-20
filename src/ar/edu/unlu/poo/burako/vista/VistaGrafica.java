package ar.edu.unlu.poo.burako.vista;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import javax.swing.*;
import java.util.ArrayList;

public class VistaGrafica implements IVista {
    private JPanel frmPrincipal;
    private final JFrame frame;

    private Controlador controlador;

    public VistaGrafica() {
        frame = new JFrame("Burako Grafico");
        frame.setContentPane(frmPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    @Override
    public void iniciar() {
        frame.setVisible(true);
    }

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void mostrarTexto(String txt) {

    }

    /**
     *
     */
    @Override
    public void nuevoJugador() {

    }

    /**
     * @param fichas
     */
    @Override
    public void mostrarAtril(ArrayList<String> fichas) {

    }

}
