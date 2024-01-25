package ar.edu.unlu.poo.burako.vista.grafica;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.modelo.FichaComodin;
import ar.edu.unlu.poo.burako.modelo.IFicha;
import ar.edu.unlu.poo.burako.vista.ColorRGB;
import ar.edu.unlu.poo.burako.vista.IVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class VistaGrafica implements IVista, Serializable {
    private JPanel pnlMain;
    private JPanel pnlCardPartida;
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
    private JMenuItem mniPanelTexto;
    private JMenuItem mniReglas;
    private JMenuItem mniAcercaDe;
    private JTextPane txpRegistro;
    private JScrollPane scpRegistro;
    private JMenu mnuFondo;
    private JMenuItem mniFondoAzul;
    private JMenuItem mniFondoRojo;
    private JMenuItem mniFondoVerde;
    private JPanel pnlMenu;
    private JPanel pnlCardMenuPrincipal;
    private JLabelFondo lblBurako;
    private JPanel pnlBotonesMenu;
    private JButton btnIniciarPartida;
    private JButton btnMostarJugadores;
    private JButton btnMejoresJugadores;
    private JButton btnSalir;
    private JTextField txtNombreJugador;
    private JButton btnAceptarJugador;
    private JPanel pnlJugadorCenter;
    private JPanel pnlCardJugador;
    private JLabel lblBienvenida;
    private JLabel lblNombreJugador;
    private JLabel lblMensajes;
    private JPanel burako;
    private JPanelFondo pnlFelt;
    private JFrame frame;

    private Image imgTablero;
    private Controlador controlador;

    private DefaultListModel<ImageIcon> listaModeloIzquierda;
    private DefaultListModel<ImageIcon> listaModeloAbajo;
    private DefaultListModel<ImageIcon> listaModeloDerecha;
    private DefaultListModel<ImageIcon> listaModeloArriba;

    public VistaGrafica(int x, int y) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame("Burako Grafico");
                frame.setContentPane(pnlMain);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setMinimumSize(new Dimension(500, 500));
                frame.setPreferredSize(new Dimension(600, 600));
                frame.setLocation(x, y);
                frame.setVisible(true);

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
                        // Revalidar y repintar el panel
                        pnlMain.revalidate();
                        pnlMain.repaint();
                    }
                });

                mniFondoAzul.addActionListener(new ActionListener() {
                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pnlFelt.setImagenFondo("/ar/edu/unlu/poo/burako/texture/blueFeltTexture.png");
                        // Revalidar y repintar el panel
                        frame.revalidate();
                        frame.repaint();
                    }
                });

                mniFondoRojo.addActionListener(new ActionListener() {
                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pnlFelt.setImagenFondo("/ar/edu/unlu/poo/burako/texture/redFeltTexture.png");
                        // Revalidar y repintar el panel
                        frame.revalidate();
                        frame.repaint();
                    }
                });

                mniFondoVerde.addActionListener(new ActionListener() {
                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pnlFelt.setImagenFondo("/ar/edu/unlu/poo/burako/texture/greenFeltTexture.png");
                        // Revalidar y repintar el panel
                        frame.revalidate();
                        frame.repaint();
                    }
                });

                frame.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        int ancho = e.getComponent().getWidth();
                        int alto = e.getComponent().getHeight();
                        ajustarTamanioTitulo(ancho, alto);
                        // TODO testear bien
                        Dimension newSize = frame.getSize();
                        int minWidth = 400;
                        int minHeight = 400;
                        // Limitar el tamaño mínimo
                        if (newSize.width < minWidth || newSize.height < minHeight) {
                            frame.setSize(
                                    Math.max(newSize.width, minWidth),
                                    Math.max(newSize.height, minHeight)
                            );
                        }
                    }
                });

                btnIniciarPartida.addActionListener(new ActionListener() {
                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controlador.iniciarPartida();
                    }
                });
                btnAceptarJugador.addActionListener(new ActionListener() {
                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!txtNombreJugador.getText().isEmpty()) {
                            controlador.nuevoJugador(txtNombreJugador.getText());
                            colocarTituloFondo(pnlCardMenuPrincipal);
                            ajustarTamanioTitulo(frame.getWidth(), frame.getHeight());
                            cambiarVista(pnlCardMenuPrincipal);
                        } else {
                            JOptionPane.showMessageDialog(pnlJugadorCenter, "Por favor ingrese un nombre de jugador",
                                    "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

            }
        });

    }

    private void colocarTituloFondo(JPanel panel) {
        lblBurako = new JLabelFondo("/ar/edu/unlu/poo/burako/texture/titulo.png");
        lblBurako.setPreferredSize(new Dimension(600, 300));
        lblBurako.setMinimumSize(new Dimension(0, 0));
        lblBurako.setMaximumSize(new Dimension(800, 400));
        panel.add(lblBurako, BorderLayout.NORTH);
        // Revalidar y repintar el panel
        panel.revalidate();
        panel.repaint();
    }

    private void ajustarTamanioTitulo(int ancho, int alto) {
        int nuevoAncho = frame.getWidth();
        int nuevoAlto = frame.getHeight();
        // Ajustar el tamaño preferido del JLabelFondo
        lblBurako.setPreferredSize(new Dimension(nuevoAncho, nuevoAlto / 4));
        // Revalidar y repintar el panel
        lblBurako.revalidate();
        lblBurako.repaint();
    }

    private void cambiarVista(JPanel panelCard) {
        pnlMenu.removeAll();
        pnlMenu.add(panelCard);
        // Revalidar y repintar el panel
        pnlMenu.revalidate();
        pnlMenu.repaint();
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
        lblMensajes.setForeground(ColorRGB.RED);
        lblMensajes.setText(txt);
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblMensajes.setText(" "); // Borra el texto después de 3000 milisegundos (3 segundos)
            }
        });
        timer.setRepeats(false); // No se repite, ejecuta la tarea una vez
        timer.start();
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
        colocarTituloFondo(pnlCardJugador);
        cambiarVista(pnlCardJugador);
        // frame.setVisible(true);
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
        // Coloca la textura de fondo
        pnlFelt = new JPanelFondo("/ar/edu/unlu/poo/burako/texture/greenFeltTexture.png");
        pnlFelt.setOpaque(false); // Hace que sea visible los componentes sobre él
        pnlFelt.setLayout(new BorderLayout());
        pnlCardPartida.remove(pnlCenter);
        pnlFelt.add(pnlCenter, BorderLayout.CENTER);  // Agregar pnlCenter al pnlFelt
        pnlCardPartida.add(pnlFelt, BorderLayout.CENTER); // Agregar pnlFelt al pnlCardPartida
        // Revalidar y repintar el panel
        pnlCardPartida.revalidate();
        pnlCardPartida.repaint();
        cambiarVista(pnlCardPartida);

        RecortarMosaico cut = new RecortarMosaico();
        for (IFicha ficha : atril) {
            int color;
            int numero;
            if (ficha instanceof FichaComodin) {
                color = 4;
                numero = ficha.getNumeroFicha();
            } else {
                color = ficha.getColor().ordinal();
                numero = ficha.getNumeroFicha() - 1;

            }
            System.out.println("color " + color);
            System.out.println("numero " + numero);
            ImageIcon imagen = cut.getImagenRecortadaIcon(color, numero, 100, 100, 0);
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
        // Revalidar y repintar el panel
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
