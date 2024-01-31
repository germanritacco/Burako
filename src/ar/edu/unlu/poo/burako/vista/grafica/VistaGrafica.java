package ar.edu.unlu.poo.burako.vista.grafica;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.modelo.FichaComodin;
import ar.edu.unlu.poo.burako.modelo.IFicha;
import ar.edu.unlu.poo.burako.vista.ColorRGB;
import ar.edu.unlu.poo.burako.vista.IVista;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

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
    private JSplitPane splRegistro;
    private JPanel pnlJugadasOponente;
    private JPanel pnlPilaFichas;
    private JPanel pnlJugadas;
    private JLayeredPane lypPilaMuertos;
    private JLayeredPane lypMazo;
    private JLayeredPane lypPozo;
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
                            mniPanelTexto.setText("Mostrar Panel Registro");
                        } else {
                            scpRegistro.setVisible(true);
                            splRegistro.setDividerLocation(0.8);
                            mniPanelTexto.setText("Ocultar Panel Registro");
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

                mniAbandonarPartida.addActionListener(new ActionListener() {
                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO Revisar metodo para que retorne void
                        controlador.abandonarPartida();
                    }
                });

                mniSalir.addActionListener(new ActionListener() {
                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controlador.cerrarApp();
                    }
                });

                frame.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        int ancho = e.getComponent().getWidth();
                        int alto = e.getComponent().getHeight();
                        ajustarTamanioTitulo(ancho, alto);
                        // TODO Testear bien
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
                            mostrarPopUp("Por favor ingrese un nombre de jugador", ColorRGB.RED, pnlCardJugador);
                        }
                    }
                });

                pnlJugadas.addMouseListener(new MouseAdapter() {
                    /**
                     * {@inheritDoc}
                     *
                     * @param e
                     */
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        // TODO Borrar
                        mostrarTexto("Colocando jugada en mesa...\n", false);
                        int[] fichasSeleccionadas = lstSouth.getSelectedIndices();
                        // TODO Cambiar que el metodo reciba un Array de int
                        String[] stringFichas = new String[fichasSeleccionadas.length];
                        if (fichasSeleccionadas.length > 0) {
                            for (int i = 0; i < fichasSeleccionadas.length; i++) {
                                stringFichas[i] = String.valueOf(fichasSeleccionadas[i] + 1);
                            }
                        }
                        controlador.agregarNuevaJugada(stringFichas);
                        mostrarJuegosMesa(controlador.mostrarJuegosEnMesa());
                        mostrarAtril(controlador.mostrarAtril());
                    }
                });

                btnSalir.addActionListener(new ActionListener() {
                    /**
                     * Invoked when an action occurs.
                     *
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controlador.cerrarApp();
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
    public void mostrarTexto(String txt, boolean critico) {
        Color color;
        if (critico) {
            color = ColorRGB.RED;
        } else {
            color = ColorRGB.GREEN;
        }
        lblMensajes.setForeground(color);
        lblMensajes.setText((txt.toUpperCase()));
        mostrarRegistro(txt, color);
        mostrarPopUp(txt, color, frame);
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblMensajes.setText(" "); // Borra el texto después de 3000 milisegundos (3 segundos)
            }
        });
        timer.setRepeats(false); // No se repite, ejecuta la tarea una vez
        timer.start();
    }

    private void mostrarRegistro(String txt, Color color) {
        String texto = "";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:MM:ss: ");
        ColorRGB.appendColor(txpRegistro, LocalDateTime.now().format(format), ColorRGB.BLUE);
        ColorRGB.appendColor(txpRegistro, txt, color);
    }

    /**
     * Cambia el flujo cuando un jugador abandona la partida.
     *
     * @param nombre Nombre del jugador.
     */
    @Override
    public void abandonarPartida(String nombre) {
        if (!controlador.isJugadorTurno()) {
            mostrarTexto("¡PARTIDA TERMINADA! El jugador " + nombre + " ha terminado la partida. Volveras al menu principal.", true);
        } else {
            mostrarTexto("¡PARTIDA TERMINADA! Volveras al menu principal.", true);
        }
        cambiarVista(pnlCardMenuPrincipal);
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                colocarTituloFondo(pnlCardJugador);
                cambiarVista(pnlCardJugador);
                frame.setVisible(true);
            }
        });
    }

    /**
     * Muestra por pantalla que jugador posee el turno.
     *
     * @param jugador Nombre del jugador.
     */
    @Override
    public void mostrarTurno(String jugador) {
        mostrarPopUp("Es el turno de " + jugador.toUpperCase(), ColorRGB.GREEN, pnlCenter);
    }

    private void mostrarPopUp(String texto, Color color, Component centrarPopUpA) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crear el mensaje emergente como un JWindow
                JWindow popUp = new JWindow();
                popUp.setLayout(new BorderLayout());
                popUp.setSize(300, 100);
                popUp.setLocationRelativeTo(centrarPopUpA); // Centrar en la pantalla
                popUp.setBackground(new Color(33, 37, 43, 250)); // alfa aplica transparencia
                JLabel label = new JLabel(texto);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setForeground(color);
                popUp.add(label, BorderLayout.CENTER);
                // Configurar temporizador para cerrar el mensaje emergente después de 3 segundos
                Timer timer = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                popUp.dispose(); // Cerrar el mensaje emergente después de 3 segundos
                            }
                        });
                    }
                });
                timer.setRepeats(false); // El temporizador solo se ejecutará una vez
                timer.start();
                popUp.setVisible(true);
            }
        });
    }

    /**
     * Muestra por pantalla lo necesario para iniciar la partida.
     *
     * @param atril Lista de fichas que posee el jugador en el atril.
     * @param pozo  Lista de fichas que posee el pozo.
     */
    @Override
    public void iniciarPartida(ArrayList<IFicha> atril, ArrayList<IFicha> pozo) {
        pnlJugadasOponente.setBackground(new Color(224, 108, 117, 150));
        pnlJugadas.setBackground(new Color(97, 175, 239, 150));
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
        lblSouth.setForeground(ColorRGB.CYAN);
        lblSouth.setText(" " + controlador.getJugador().toUpperCase() + " ");
        RecortarMosaico cut = new RecortarMosaico();
        mostrarAtril(atril);
        // Ficha contrincante
        ImageIcon imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 90);
        listaModeloIzquierda.addElement(imagen);

        imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 90);
        listaModeloDerecha.addElement(imagen);

        imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 0);
        listaModeloArriba.addElement(imagen);

        scpEast.setPreferredSize(new Dimension(lstEast.getPreferredSize().width + 18, lstEast.getPreferredSize().height));
        scpWest.setPreferredSize(new Dimension(lstWest.getPreferredSize().width + 18, lstWest.getPreferredSize().height));
        scpNorth.setPreferredSize(new Dimension(lstNorth.getPreferredSize().width, lstNorth.getPreferredSize().height + 18));
        // Revalidar y repintar el panel
        pnlNorth.revalidate();
        pnlNorth.repaint();
        mostrarTurno(controlador.nombreJugadorTurno());
        setLypPilaMuertos();
        setLypMazo();
        setLypPozo();

    }

    private void setLypPilaMuertos() {
        lypPilaMuertos.setLayout(null);
        RecortarMosaico cut = new RecortarMosaico();

        ImageIcon imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 0);
        JLabel lblImgV1 = new JLabel(imagen);
        lblImgV1.setBounds(0, 0, 75, 75);

        imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 6);
        JLabel lblImgV2 = new JLabel(imagen);
        lblImgV2.setBounds(2, 2, 75, 75);

        imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 90);
        JLabel lblImgH1 = new JLabel(imagen);
        lblImgH1.setBounds(0, 0, 75, 75);

        imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 96);
        JLabel lblImgH2 = new JLabel(imagen);
        lblImgH2.setBounds(2, 2, 75, 75);

        lypPilaMuertos.add(lblImgV1, Integer.valueOf(0));
        lypPilaMuertos.add(lblImgV2, Integer.valueOf(1));
        lypPilaMuertos.add(lblImgH1, Integer.valueOf(2));
        lypPilaMuertos.add(lblImgH2, Integer.valueOf(3));
        lypPilaMuertos.setPreferredSize(new Dimension(75, 75));
        lypPilaMuertos.revalidate();
        lypPilaMuertos.repaint();
    }

    private void setLypMazo() {
        lypMazo.setLayout(null);
        RecortarMosaico cut = new RecortarMosaico();

        ImageIcon imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 0);
        JLabel lblImgV1 = new JLabel(imagen);
        lblImgV1.setBounds(0, -2, 75, 75);

        imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, -3);
        JLabel lblImgV2 = new JLabel(imagen);
        lblImgV2.setBounds(-2, 2, 75, 75);

        imagen = cut.getImagenRecortadaIcon(4, 1, 75, 75, 2);
        JLabel lblImgV3 = new JLabel(imagen);
        lblImgV3.setBounds(3, -1, 75, 75);

        lypMazo.add(lblImgV1, Integer.valueOf(0));
        lypMazo.add(lblImgV2, Integer.valueOf(1));
        lypMazo.add(lblImgV3, Integer.valueOf(2));

        JLabel lblPozoSize = new JLabel();
        lblPozoSize.setText("56"); // TODO implementar metodo que retorne cantidad de fichas en mazo
        lblPozoSize.setForeground(ColorRGB.YELLOW);
        Font font = lblPozoSize.getFont();
        lblPozoSize.setFont(new Font(font.getName(), font.getStyle(), 15)); // Tamaño de fuente deseado
        Dimension size = lblPozoSize.getPreferredSize(); // Obtener el tamaño preferido del JLabel lblPozoSize
        lblPozoSize.setBounds(0, 48, size.width, size.height);
        lypMazo.add(lblPozoSize, Integer.valueOf(3));

        lypMazo.setPreferredSize(new Dimension(75, 75));
        lypMazo.revalidate();
        lypMazo.repaint();
    }

    private void setLypPozo() {
        lypPozo.setLayout(null);
        RecortarMosaico cut = new RecortarMosaico();
      /*  // TODO Borrar
        int x = -16;
        int y = -8;
        ImageIcon imagen = cut.getImagenRecortadaIcon(3, 2, 75, 75, 0);
        JLabel lblImg = new JLabel(imagen);
        lblImg.setBounds(x, y, 75, 75);
        lypPozo.add(lblImg, Integer.valueOf(0));*/

        lypPozo.setBorder(new LineBorder(ColorRGB.YELLOW, 4));
        lypPozo.setPreferredSize(new Dimension(43, 59));
        lypPozo.revalidate();
        lypPozo.repaint();
    }
// TODO REVISAR DESDE ACA

    /**
     * Muestra por pantalla los juegos que posee un jugador.
     *
     * @param juegosMesa Lista de listas de fichas.
     */
    @Override
    public void mostrarJuegosMesa(ArrayList<ArrayList<IFicha>> juegosMesa) {
        if (juegosMesa == null || juegosMesa.isEmpty()) {
            mostrarTurno("No hay juegos en mesa");

            // TODO fix
        } else {

            pnlJugadas.removeAll();
            int cantidadFichas;
            for (ArrayList<IFicha> juego : juegosMesa) {
                JLayeredPane pnlTest = new JLayeredPane();

                pnlJugadas.add(pnlTest);
                cantidadFichas = juego.size() - 1;
                int prioridad = 0;
                DefaultListModel<ImageIcon> listModel = new DefaultListModel<>();
                RecortarMosaico cut = new RecortarMosaico();
                int x = -20;
                int y = -10;
                for (IFicha ficha : juego) {
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

                    JLabel lblImg = new JLabel(imagen);
                    lblImg.setBounds(x, y, 100, 100);
                    x += 40;
                    pnlTest.add(lblImg, Integer.valueOf(prioridad));
                    prioridad++;

                    //listModel.addElement(imagen);
                }
                int ancho = 100 + (40 * (cantidadFichas - 1));
                pnlTest.setPreferredSize(new Dimension(ancho, 80));
                // pnlTest.setBackground(Color.GREEN);
                pnlTest.setOpaque(false);
              /*  JList<ImageIcon> jList = new JList<>(listModel);
                pnlJugadas.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Ajusta el espaciado entre componentes
                JScrollPane scrollPane = new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                jList.setOpaque(false);
                jList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
                jList.setVisibleRowCount(1);
                pnlJugadas.add(scrollPane);
                pnlJugadas.revalidate();
                pnlJugadas.repaint();*/
                pnlTest.revalidate();
                pnlTest.repaint();
                pnlJugadas.revalidate();
                pnlJugadas.repaint();
                pnlCenter.revalidate();
                pnlCenter.repaint();

                // Suponiendo que "componente" es el componente del que deseas conocer el LayoutManager
                LayoutManager layoutManager = pnlTest.getLayout();
                if (layoutManager != null) {
                    System.out.println("El componente está utilizando el siguiente LayoutManager: " + layoutManager.getClass().getSimpleName());
                } else {
                    System.out.println("El componente no tiene un LayoutManager asociado.");
                }

            }
        }

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
        RecortarMosaico cut = new RecortarMosaico();
        listaModeloAbajo.removeAllElements();
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
            ImageIcon imagen = cut.getImagenRecortadaIcon(color, numero, 100, 100, 0);
            listaModeloAbajo.addElement(imagen);
        }
        scpSouth.setPreferredSize(new Dimension(lstSouth.getPreferredSize().width, lstSouth.getPreferredSize().height + 18));
        // Revalidar y repintar el panel
        pnlSouth.revalidate();
        pnlSouth.repaint();
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
        cambiarVista(pnlCardMenuPrincipal);
    }

    /**
     * @param puntaje
     */
    @Override
    public void mostrarPuntos(String puntaje) {

    }

}
