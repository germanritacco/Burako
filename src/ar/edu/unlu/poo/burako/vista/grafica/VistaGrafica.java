package ar.edu.unlu.poo.burako.vista.grafica;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.modelo.FichaComodin;
import ar.edu.unlu.poo.burako.modelo.IFicha;
import ar.edu.unlu.poo.burako.vista.ColorRGB;
import ar.edu.unlu.poo.burako.vista.IVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VistaGrafica implements IVista, Serializable {
    private JPanel pnlMain;
    private JPanel pnlCardPartida;
    private JList lstWest;
    private JList lstEast;
    private JList lstSouth;
    private JPanel pnlCenter;
    private JLabel lblJugadorNorth;
    private JLabel lblJugadorWest;
    private JLabel lblEast;
    private JLabel lblSouth;
    private JPanel pnlNorth;
    private JPanel pnlWest;
    private JPanel pnlEast;
    private JPanel pnlSouth;
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
    private JPanel pnlPuntajeJugador;
    private JPanel pnlPuntajeOponente;
    private JLabel lblPuntajeJugador;
    private JLabel lblPuntajeOponente;
    private JLayeredPane lypJugadorNorth;
    private JPanel pnlJugadorNorth;
    private JPanelFondo pnlFelt;
    private JFrame frame;
    private JPanel areaPozo;
    private Controlador controlador;
    private DefaultListModel<ImageIcon> listaModeloAbajo;

    public VistaGrafica(int x, int y) {
        // Ajustes del JFrame
        frame = new JFrame("Burako Grafico");
        frame.setContentPane(pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setLocation(x, y);
        // Ajusta el color de las áreas de juego con transparencia
        pnlPuntajeOponente.setBackground(new Color(224, 108, 117, 150));
        pnlPuntajeJugador.setBackground(new Color(97, 175, 239, 150));
        // Crea JPanel para representar el área de descarte del pozo
        areaPozo = new JPanel();
        // Crea el listModel necesario para mostrar las fichas del atril
        listaModeloAbajo = new DefaultListModel<>();
        lstSouth.setModel(listaModeloAbajo);
        lstSouth.setBackground(new Color(0, 0, 0, 0)); // Setea el color en transparente
        lstSouth.setVisibleRowCount(1);

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

        lypPozo.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventoFichaPozo();
            }
        });

        areaPozo.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventoFichaPozo();
            }
        });
    }

    private void eventoFichaPozo() {
        if (controlador.isJugadorTurno()) {
            agregarFichaPozo();
            mostrarAtril(controlador.mostrarAtril());
        } else {
            mostrarPopUp("Aguarde su turno", ColorRGB.RED, pnlCardPartida);
        }
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
            mostrarTexto("¡PARTIDA TERMINADA! El jugador " + nombre + " ha terminado la partida. Volveras al menu principal.\n", true);
        } else {
            mostrarTexto("¡PARTIDA TERMINADA! Volveras al menu principal.\n", true);
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
        mostrarTexto("Es el turno de " + jugador.toUpperCase(), false);
    }

    private void mostrarPopUp(String texto, Color color, Component centrarPopUpA) {
        JWindow popUp = new JWindow();
        popUp.setLayout(new BorderLayout());
        popUp.setBackground(new Color(33, 37, 43, 250)); // alfa aplica transparencia
        JLabel lblTexto = new JLabel(texto);
        lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
        lblTexto.setForeground(color);
        popUp.add(lblTexto, BorderLayout.CENTER);
        Dimension lblTextoSize = lblTexto.getPreferredSize();
        int ancho = Math.max(300, lblTextoSize.width + 20); // Mínimo ancho de 300, agregando un margen de 20
        popUp.setSize(ancho, 100);
        popUp.setLocationRelativeTo(centrarPopUpA); // Centrar en la pantalla
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

    /**
     * Muestra por pantalla lo necesario para iniciar la partida.
     *
     * @param atril Lista de fichas que posee el jugador en el atril.
     * @param pozo  Lista de fichas que posee el pozo.
     */
    @Override
    public void iniciarPartida(ArrayList<IFicha> atril, ArrayList<IFicha> pozo) {
        pnlEast.setVisible(false);
        pnlWest.setVisible(false);
        // Controla que solo quien tenga el turno pueda ejecutar los eventos
        if (controlador.isJugadorTurno()) {
            enableComponents(true);
        } else {
            disableComponents();
        }
        // Setea los colores de los paneles con transparencia
        pnlJugadasOponente.setBackground(new Color(33, 37, 43, 150));
        pnlJugadas.setBackground(new Color(33, 37, 43, 150));
        pnlSouth.setBackground(new Color(33, 37, 43, 240));
        pnlJugadorNorth.setBackground(new Color(33, 37, 43, 240));
        // Coloca la textura de fondo
        pnlFelt = new JPanelFondo("/ar/edu/unlu/poo/burako/texture/greenFeltTexture.png");
        pnlFelt.setOpaque(false); // Hace que sea visible los componentes sobre él
        pnlFelt.setLayout(new BorderLayout());
        // Ajusta la jerarquía de paneles
        splRegistro.remove(pnlMenu);
        pnlFelt.add(pnlMenu, BorderLayout.CENTER);
        splRegistro.setLeftComponent(pnlFelt);
        // Setea en transparente para que vea el color asignado en la lista de fichas
        scpSouth.getViewport().setOpaque(false);
        // Cambia a la vista principal de Partida
        cambiarVista(pnlCardPartida);
        // Asigna los nombres de los jugadores
        lblSouth.setForeground(ColorRGB.CYAN);
        lblSouth.setText(" " + controlador.getJugador().toUpperCase() + " ");
        lblJugadorNorth.setForeground(ColorRGB.RED);
        lblJugadorNorth.setText(controlador.getOponente().toUpperCase());
        mostrarAtril(atril); // Muestra las fichas del atril
        /* // Ajusta las dimensiones de los paneles
        scpEast.setPreferredSize(new Dimension(lstEast.getPreferredSize().width + 18, lstEast.getPreferredSize().height));
        scpWest.setPreferredSize(new Dimension(lstWest.getPreferredSize().width + 18, lstWest.getPreferredSize().height));*/
        // Ajusta los paneles de Pila de Muertos, Mazo y Pozo
        setLypPilaMuertos();
        setLypMazo();
        setLypPozo();
        // Ajusta el panel del oponente
        setLypJugadorNorth();
        mostrarTurno(controlador.nombreJugadorTurno());
    }

    private void setLypJugadorNorth() {
        lypJugadorNorth.setLayout(null);
        RecortarMosaico cut = new RecortarMosaico();

        ImageIcon imagen = cut.getImagenRecortadaIcon(4, 1, 45, 60, 0);
        JLabel lblImgV1 = new JLabel(imagen);
        lblImgV1.setBounds(6, 0, 60, 60);

        imagen = cut.getImagenRecortadaIcon(4, 1, 60, 60, 6);
        JLabel lblImgV2 = new JLabel(imagen);
        lblImgV2.setBounds(8, 2, 60, 60);

        lypJugadorNorth.add(lblImgV1, Integer.valueOf(0));
        lypJugadorNorth.add(lblImgV2, Integer.valueOf(1));

        JLabel lblFichasAtrilNorth = new JLabel();
        lblFichasAtrilNorth.setText("56"); // TODO implementar metodo que retorne cantidad de fichas en mazo
        lblFichasAtrilNorth.setForeground(ColorRGB.YELLOW);
        Font font = lblFichasAtrilNorth.getFont();
        lblFichasAtrilNorth.setFont(new Font(font.getName(), font.getStyle(), 15)); // Tamaño de fuente deseado
        Dimension size = lblFichasAtrilNorth.getPreferredSize(); // Obtener el tamaño preferido del JLabel lblPozoSize
        lblFichasAtrilNorth.setBounds(0, 41, size.width, size.height);
        lypJugadorNorth.add(lblFichasAtrilNorth, Integer.valueOf(3));

        lypJugadorNorth.setPreferredSize(new Dimension(60, 60));
        lypJugadorNorth.revalidate();
        lypJugadorNorth.repaint();
    }

    private void setLypPilaMuertos() {
        lypPilaMuertos.setLayout(null);
        RecortarMosaico cut = new RecortarMosaico();

        ImageIcon imagen = cut.getImagenRecortadaIcon(4, 1, 45, 60, 0);
        JLabel lblImgV1 = new JLabel(imagen);
        lblImgV1.setBounds(0, 0, 60, 60);

        imagen = cut.getImagenRecortadaIcon(4, 1, 60, 60, 6);
        JLabel lblImgV2 = new JLabel(imagen);
        lblImgV2.setBounds(2, 2, 60, 60);

        imagen = cut.getImagenRecortadaIcon(4, 1, 60, 60, 90);
        JLabel lblImgH1 = new JLabel(imagen);
        lblImgH1.setBounds(0, 0, 60, 60);

        imagen = cut.getImagenRecortadaIcon(4, 1, 60, 60, 96);
        JLabel lblImgH2 = new JLabel(imagen);
        lblImgH2.setBounds(2, 2, 60, 60);

        lypPilaMuertos.add(lblImgV1, Integer.valueOf(0));
        lypPilaMuertos.add(lblImgV2, Integer.valueOf(1));
        lypPilaMuertos.add(lblImgH1, Integer.valueOf(2));
        lypPilaMuertos.add(lblImgH2, Integer.valueOf(3));

        lypPilaMuertos.setPreferredSize(new Dimension(60, 60));
        lypPilaMuertos.revalidate();
        lypPilaMuertos.repaint();
    }

    private void setLypMazo() {
        lypMazo.setLayout(null);
        RecortarMosaico cut = new RecortarMosaico();

        ImageIcon imagen = cut.getImagenRecortadaIcon(4, 1, 45, 60, 0);
        JLabel lblImgV1 = new JLabel(imagen);
        lblImgV1.setBounds(6, -2, 60, 60);

        imagen = cut.getImagenRecortadaIcon(4, 1, 60, 60, -3);
        JLabel lblImgV2 = new JLabel(imagen);
        lblImgV2.setBounds(4, 2, 60, 60);

        imagen = cut.getImagenRecortadaIcon(4, 1, 60, 60, 2);
        JLabel lblImgV3 = new JLabel(imagen);
        lblImgV3.setBounds(9, -1, 60, 60);

        lypMazo.add(lblImgV1, Integer.valueOf(0));
        lypMazo.add(lblImgV2, Integer.valueOf(1));
        lypMazo.add(lblImgV3, Integer.valueOf(2));

        JLabel lblPozoSize = new JLabel();
        lblPozoSize.setText("58"); // TODO implementar metodo que retorne cantidad de fichas en mazo
        lblPozoSize.setForeground(ColorRGB.YELLOW);
        Font font = lblPozoSize.getFont();
        lblPozoSize.setFont(new Font(font.getName(), font.getStyle(), 15)); // Tamaño de fuente deseado
        Dimension size = lblPozoSize.getPreferredSize(); // Obtener el tamaño preferido del JLabel lblPozoSize
        lblPozoSize.setBounds(0, 41, size.width, size.height);
        lypMazo.add(lblPozoSize, Integer.valueOf(3));

        lypMazo.setPreferredSize(new Dimension(60, 60));
        lypMazo.revalidate();
        lypMazo.repaint();
    }

    private void setLypPozo() {
        lypPozo.setLayout(null);
        RecortarMosaico cut = new RecortarMosaico();

        areaPozo.setOpaque(true);
        areaPozo.setBackground(new Color(33, 37, 43, 150));
        areaPozo.setBounds(0, 0, 43, 60);
        lypPozo.add(areaPozo, Integer.valueOf(0));

        areaPozo.revalidate();
        areaPozo.repaint();

        lypPozo.setPreferredSize(new Dimension(43, 60));
        lypPozo.revalidate();
        lypPozo.repaint();
    }

    /**
     * Muestra por pantalla los juegos que posee un jugador.
     *
     * @param juegosMesa Lista de listas de fichas.
     */
    @Override
    public void mostrarJuegosMesa(ArrayList<ArrayList<IFicha>> juegosMesa) {
        mostrarJuegosFichas(juegosMesa, pnlJugadas);
    }

    private void mostrarJuegosFichas(ArrayList<ArrayList<IFicha>> juegosMesa, JPanel panel) {
        if (juegosMesa == null || juegosMesa.isEmpty()) {
            mostrarTexto("No hay juegos en mesa", true);
        } else {
            panel.removeAll();
            int cantidadFichas;
            for (ArrayList<IFicha> juego : juegosMesa) {
                JLayeredPane lypJuegoMesa = new JLayeredPane();
                lypJuegoMesa.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        // TODO Agregar aca para agregar ficha a jugada
                    }
                });
                panel.add(lypJuegoMesa);
                cantidadFichas = juego.size();
                int prioridad = 0;
                RecortarMosaico cut = new RecortarMosaico();
                int x = 0;
                int y = 0;
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
                    ImageIcon imagen = cut.getImagenRecortadaIcon(color, numero, 45, 60, 0);
                    JLabel lblImg = new JLabel(imagen);
                    lblImg.setBounds(x, y, 45, 60);
                    x += 30;
                    lypJuegoMesa.add(lblImg, Integer.valueOf(prioridad));
                    // Agregar el MouseListener a cada componente interno (en este caso, a cada JLabel)
                    lblImg.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            // Coloca aquí la acción que deseas que se ejecute cuando se haga clic en cada componente interno
                            JOptionPane.showMessageDialog(null, "Se hizo clic en un componente interno");
                        }
                    });
                    prioridad++;
                }
                int ancho = 43 + (30 * (cantidadFichas - 1));
                lypJuegoMesa.setPreferredSize(new Dimension(ancho, 60));
                lypJuegoMesa.setOpaque(false);
                lypJuegoMesa.revalidate();
                lypJuegoMesa.repaint();
                panel.revalidate();
                panel.repaint();
                pnlCenter.revalidate();
                pnlCenter.repaint();
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
        removeAllLabelFichas(lypPozo);
        int cantidadFichas = pozo.size();
        int prioridad = 1;
        int x = -1;
        int y = 0;
        RecortarMosaico cut = new RecortarMosaico();
        for (IFicha ficha : pozo) {
            int color;
            int numero;
            if (ficha instanceof FichaComodin) {
                color = 4;
                numero = ficha.getNumeroFicha();
            } else {
                color = ficha.getColor().ordinal();
                numero = ficha.getNumeroFicha() - 1;
            }
            ImageIcon imagen = cut.getImagenRecortadaIcon(color, numero, 45, 60, 0);
            JLabel lblImg = new JLabel(imagen);
            lblImg.setBounds(x, y, 45, 60);
            x += 30;
            lypPozo.add(lblImg, Integer.valueOf(prioridad));
            // Agregar el MouseListener a cada componente interno (en este caso, a cada JLabel)
            lblImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Coloca aquí la acción que deseas que se ejecute cuando se haga clic en cada componente interno
                    eventoFichaPozo();
                }
            });
            prioridad++;
        }
        int ancho = 43 + (30 * (cantidadFichas - 1));
        lypPozo.setPreferredSize(new Dimension(ancho, 60));

        lypPozo.revalidate();
        lypPozo.repaint();
        pnlJugadas.revalidate();
        pnlJugadas.repaint();
        pnlCenter.revalidate();
        pnlCenter.repaint();
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
            ImageIcon imagen = cut.getImagenRecortadaIcon(color, numero, 60, 80, 0);
            listaModeloAbajo.addElement(imagen);
        }
        scpSouth.setPreferredSize(new Dimension(lstSouth.getPreferredSize().width, lstSouth.getPreferredSize().height + 18));
        // Revalidar y repintar el panel
        scpSouth.revalidate();
        scpSouth.repaint();
        frame.revalidate();
        frame.repaint();
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
    public void mostrarPuntos(Integer puntaje) {
        lblPuntajeJugador.setText(String.valueOf(puntaje));
        pnlMenu.revalidate();
        pnlMenu.repaint();
    }

    @Override
    public void mostrarPuntosOponente(Integer puntaje) {
        lblPuntajeOponente.setText(String.valueOf(puntaje));
        pnlMenu.revalidate();
        pnlMenu.repaint();
    }

    /**
     * @param juegosMesa
     */
    @Override
    public void mostrarJuegosMesaOponente(ArrayList<ArrayList<IFicha>> juegosMesa) {
        mostrarJuegosFichas(juegosMesa, pnlJugadasOponente);
    }

    private void agregarFichaPozo() {
        int opcion = lstSouth.getSelectedIndex();
        if (opcion == -1) {
            mostrarTexto("No se ha seleccionado ninguna ficha para descartar", true);
        } else {
            controlador.agregarFichaPozo(opcion);
            if (controlador.atrilVacio() && !controlador.tomoMuerto()) {
                controlador.tomarMuerto();
                mostrarTexto(" Atril Vacío. Se ha tomado el muerto.", false);
            }
            if (controlador.isCanasta() && controlador.tomoMuerto() && controlador.atrilVacio()) {
                // TODO partida terminada
            }
        }
    }

    private void removeAllLabelFichas(JLayeredPane layeredPane) {
        Component[] components = layeredPane.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                layeredPane.remove(component);
            }
        }
        // Revalidar y repintar el JLayeredPane después de eliminar los componentes
        layeredPane.revalidate();
        layeredPane.repaint();
    }

}
