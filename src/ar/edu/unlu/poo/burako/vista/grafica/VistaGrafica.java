package ar.edu.unlu.poo.burako.vista.grafica;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.modelo.FichaComodin;
import ar.edu.unlu.poo.burako.modelo.IFicha;
import ar.edu.unlu.poo.burako.vista.IVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class VistaGrafica implements IVista, Serializable {
    private JPanel pnlMain;
    private JTabbedPane tabPartida;
    private JPanel pnlPartida;
    private JList lstNorth;
    private JList lstWest;
    private JList lstEast;
    private JList lstSouth;
    private JPanel pnlCenter;
    private JLabel lblJugadorNorth;
    private JLabel lblJugadorWest;
    private JLabel lblEast;
    private JLabel lblSouth;
    private JPanel test1;
    private JPanel pnlNorth;
    private JPanel pnlWest;
    private JPanel pnlEast;
    private JPanel pnlSouth;
    private JScrollPane scpNorth;
    private JScrollPane scpWest;
    private JScrollPane scpEast;
    private JScrollPane scpSouth;
    private JMenuBar mnbMenu;
    private JMenu mnuPartida;
    private JMenu mnuOpciones;
    private JMenu mnuAyuda;
    private JMenuItem mniAbandonarPartida;
    private JMenuItem mniSalir;
    private JMenuItem mniFondo;
    private JMenuItem mniPanelTexto;
    private JMenuItem mniReglas;
    private JMenuItem mniAcercaDe;
    private JTextPane txpRegistro;
    private JScrollPane scpRegistro;
    private JLabel lblFelt;
    private final JFrame frame;

    private Image imgTablero;
    private Controlador controlador;

    private DefaultListModel<ImageIcon> listaModeloIzquierda;
    private DefaultListModel<ImageIcon> listaModeloAbajo;
    private DefaultListModel<ImageIcon> listaModeloDerecha;
    private DefaultListModel<ImageIcon> listaModeloArriba;

    public VistaGrafica(int x, int y) {
        frame = new JFrame("Burako Grafico");
        frame.setContentPane(pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(x, y);
        frame.pack();

        // Cargar la imagen de fondo
        lblFelt = new JLabelFondo("/ar/edu/unlu/poo/burako/texture/greenFeltTexture.png");
        pnlPartida.add(lblFelt, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        listaModeloAbajo = new DefaultListModel<>();
        lstSouth.setModel(listaModeloAbajo);
        lstSouth.setVisibleRowCount(1);

        listaModeloIzquierda = new DefaultListModel<>();
        lstWest.setModel(listaModeloIzquierda);
        lstWest.setVisibleRowCount(0);

        listaModeloDerecha = new DefaultListModel<>();
        lstEast.setModel(listaModeloDerecha);
        lstEast.setVisibleRowCount(0);

        listaModeloArriba = new DefaultListModel<>();
        lstNorth.setModel(listaModeloArriba);
        lstNorth.setVisibleRowCount(1);

        mniPanelTexto.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scpRegistro.isVisible()) {
                    scpRegistro.setVisible(false);
                    mniPanelTexto.setText("Ocultar Panel Registro");
                } else {
                    scpRegistro.setVisible(true);
                    mniPanelTexto.setText("Mostrar Panel Registro");
                }
                pnlMain.revalidate();
                pnlMain.repaint();
            }
        });

    }

    /**
     * Asigna el controlador de la vista.
     *
     * @param controlador Instancia de controlador.
     */
    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    /**
     * Muestra por pantalla el texto recibido por parámetro.
     *
     * @param txt Texto a mostrar.
     */
    @Override
    public void mostrarTexto(String txt) {
        System.out.println(txt);
    }

    /**
     * Cambia el flujo cuando un jugador abandona la partida.
     *
     * @param nombre Nombre del jugador.
     */
    @Override
    public void abandonarPartida(String nombre) {

    }

    /**
     * Añade nuevo texto al final de la pantalla, en el color indicado.
     *
     * @param texto Texto a añadir.
     * @param color Color que se desea mostrar el texto.
     */
    @Override
    public void appendColor(String texto, Color color) {

    }

    /**
     * Añade nuevo texto en la posición indicada de la pantalla, en el color indicado.
     *
     * @param texto       Texto a añadir.
     * @param color       Color que se desea mostrar el texto.
     * @param numeroLinea Posición donde se desea agregar el texto.
     */
    @Override
    public void appendColorPosicion(String texto, Color color, int numeroLinea) {

    }

    /**
     * Punto de partida de ejecución, iniciación con el flujo de nuevo jugador.
     */
    @Override
    public void nuevoJugador() {
        frame.setVisible(true);

        controlador.nuevoJugador("string");
        controlador.iniciarPartida();
    }

    /**
     * Muestra por pantalla que jugador posee el turno.
     *
     * @param jugador Nombre del jugador.
     */
    @Override
    public void mostrarTurno(String jugador) {

    }

    /**
     * Muestra por pantalla lo necesario para iniciar la partida.
     *
     * @param atril Lista de fichas que posee el jugador en el atril.
     * @param pozo  Lista de fichas que posee el pozo.
     */
    @Override
    public void iniciarPartida(ArrayList<IFicha> atril, ArrayList<IFicha> pozo) {
        RecortarMosaico cut = new RecortarMosaico();
        int color;
        int numero;
        ImageIcon imagen;
        for (IFicha ficha : atril) {
            if (ficha instanceof FichaComodin) {
                color = 4;
                numero = ficha.getNumeroFicha();
            } else {
                color = ficha.getColor().ordinal();
                numero = ficha.getNumeroFicha() - 1;

            }
            System.out.println("color " + color);
            System.out.println("numero " + numero);
            imagen = cut.getImagenRecortadaIcon(color, numero, 100, 100, 0);
            listaModeloAbajo.addElement(imagen);
            // Ficha contrincante
            imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 90);
            listaModeloIzquierda.addElement(imagen);

            imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 90);
            listaModeloDerecha.addElement(imagen);

            imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 0);
            listaModeloArriba.addElement(imagen);

            imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 0);
            listaModeloArriba.addElement(imagen);


            scpEast.setPreferredSize(new Dimension(lstEast.getPreferredSize().width + 18, lstEast.getPreferredSize().height));
            scpWest.setPreferredSize(new Dimension(lstWest.getPreferredSize().width + 18, lstWest.getPreferredSize().height));
            scpNorth.setPreferredSize(new Dimension(lstNorth.getPreferredSize().width, lstNorth.getPreferredSize().height + 18));
            scpSouth.setPreferredSize(new Dimension(lstSouth.getPreferredSize().width, lstSouth.getPreferredSize().height + 18));

        }
        frame.pack();
        pnlNorth.revalidate();
        pnlNorth.repaint();
    }

    /**
     * Muestra por pantalla los juegos que posee un jugador.
     *
     * @param juegosMesa Lista de listas de fichas.
     */
    @Override
    public void mostrarJuegosMesa(ArrayList<ArrayList<IFicha>> juegosMesa) {

    }

    /**
     * Muestra por pantalla el pozo.
     *
     * @param pozo Lista de fichas que posee el pozo.
     */
    @Override
    public void mostrarPozo(ArrayList<IFicha> pozo) {

    }

    /**
     * Muestra por pantalla el atril del jugador.
     *
     * @param atril Lista de fichas que posee el atril.
     */
    @Override
    public void mostrarAtril(ArrayList<IFicha> atril) {

    }

    /**
     * Deshabilita los componentes que permiten la entrada de texto por parte del usuario.
     */
    @Override
    public void disableComponents() {

    }

    /**
     * Habilita los componentes que permiten la entrada de texto por parte del usuario y asigna el flujo correspondiente.
     */
    @Override
    public void enableComponents(boolean cambioTurno) {

    }

    /**
     * Cambia al flujo correspondiente de mostrar el menu principal.
     */
    @Override
    public void mostrarMenuPrincipal() {

    }

    /**
     * @param puntaje
     */
    @Override
    public void mostrarPuntos(String puntaje) {

    }

}
