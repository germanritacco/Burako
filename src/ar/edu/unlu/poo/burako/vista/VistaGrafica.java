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
     * @param nombre
     */
    @Override
    public void abandonarPartida(String nombre) {

    }

    /**
     *
     */
    @Override
    public void disableComponents() {

    }

    /**
     *
     */
    @Override
    public void enableComponents() {

    }

    /**
     * @param jugador
     */
    @Override
    public void mostrarTurno(String jugador) {

    }

    /**
     * @param atril
     * @param pozo
     * @param nombreJugador
     */
    @Override
    public void iniciarPartida(ArrayList<String> atril, ArrayList<String> pozo, String nombreJugador) {

    }

    /**
     * @param atril
     */
    @Override
    public void mostrarAtril(ArrayList<String> atril) {

    }

    /**
     * @param atril
     */
    @Override
    public void mostrarJuegosMesa(ArrayList<ArrayList<String>> atril) {

    }

    /**
     * @param pozo
     */
    @Override
    public void mostrarPozo(ArrayList<String> pozo) {

    }

}
