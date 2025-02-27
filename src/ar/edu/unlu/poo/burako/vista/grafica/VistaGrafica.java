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
    private JList lstSouth;
    private JPanel pnlCenter;
    private JLabel lblJugadorNorth;
    private JLabel lblJugadorEast;
    private JLabel lblSouth;
    private JPanel pnlNorth;
    private JPanel pnlWest;
    private JPanel pnlEast;
    private JPanel pnlSouth;
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
    private JButton btnIniciarPartidaDos;
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
    private JSplitPane splMain;
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
    private JPanel pnlMensajes;
    private JPanel pnlCardTopJugadores;
    private JTextPane txtTopJugadores;
    private JPanel pnlTopJugadores;
    private JLayeredPane lypJugadorWest;
    private JPanel pnlJugadorWest;
    private JPanel pnlJugadorEast;
    private JLabel lblJugadorWest;
    private JLayeredPane lypJugadorEast;
    private JButton btnIniciarPartidaCuatro;
    private JLabel lblDescripcionMenu;
    private JMenuItem mniGuardar;
    private JButton btnCargarPartida;
    private JPanelFondo pnlFelt;
    private final JFrame frame;
    private final JPanel areaPozo;
    private Controlador controlador;
    private final DefaultListModel<ImageIcon> listaModeloAbajo;
    private final JLabel lblFichasAtrilNorth;
    private final JLabel lblFichasAtrilWest;
    private final JLabel lblFichasAtrilEast;
    private JLabel lblMazoSize;
    private boolean fichaRecogida = false;
    private boolean tomarMuerto = false;

    public VistaGrafica(int x, int y, int width, int height) {
        // Ajustes del JFrame
        frame = new JFrame("Burako Grafico");
        frame.setContentPane(pnlMain);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(width, height));
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setLocation(x, y);
        // Ajusta el color de las áreas de juego con transparencia
        pnlPuntajeOponente.setBackground(new Color(224, 108, 117, 150));
        pnlPuntajeJugador.setBackground(new Color(97, 175, 239, 150));
        // Crea JPanel para representar el área de descarte del pozo
        areaPozo = new JPanel();
        // Crea JLabel que índica la cantidad que fichas que posee el atril el jugador en la zona norte
        lblFichasAtrilNorth = new JLabel();
        lblFichasAtrilWest = new JLabel();
        lblFichasAtrilEast = new JLabel();
        // Crea JLabel que índica la cantidad que fichas que posee el mazo
        lblMazoSize = new JLabel();
        // Crea el listModel necesario para mostrar las fichas del atril
        listaModeloAbajo = new DefaultListModel<>();
        lstSouth.setModel(listaModeloAbajo);
        lstSouth.setBackground(new Color(0, 0, 0, 0)); // Setea el color en transparente
        lstSouth.setVisibleRowCount(1);

        // Agregar un WindowListener para ejecutar un método al cerrar
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.cerrarApp(); // Llama al método que deseas ejecutar
            }
        });

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
                    splMain.setDividerLocation(0.8);
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
                // TODO Revisar metodo para que retorne void.
                controlador.finalizarPartida();
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
                frame.dispose();
            }
        });

        mniGuardar.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.guardarPartida();
            }
        });

        mniReglas.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ReglasJuego reglasJuego = new ReglasJuego(pnlMenu);
            }
        });

        mniAcercaDe.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO colocar Acerca De si hay tiempo
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarTamanioTitulo();
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

        btnIniciarPartidaDos.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador.getCantidadJugadores() >= 2) {
                    controlador.iniciarPartida();
                } else {
                    mostrarPopUp("La partida no puede comenzar, ya que faltan jugadores en la partida", ColorRGB.RED, pnlCardMenuPrincipal);
                }
            }
        });

        btnIniciarPartidaCuatro.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador.getCantidadJugadores() >= 4) {
                    controlador.iniciarPartida();
                } else {
                    mostrarPopUp("La partida no puede comenzar, ya que faltan jugadores en la partida", ColorRGB.RED, pnlCardMenuPrincipal);
                }
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
                    controlador.nuevoJugador(txtNombreJugador.getText().toUpperCase());
                    colocarTituloFondo(pnlCardMenuPrincipal);
                    ajustarTamanioTitulo();
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
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (controlador.isJugadorTurno()) {
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
                } else {
                    mostrarPopUp("Aguarde su turno", ColorRGB.RED, pnlCardPartida);
                }
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
                frame.dispose();
            }
        });

        lypPozo.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
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
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                eventoFichaPozo();
            }
        });

        btnMejoresJugadores.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lblDescripcionMenu.setForeground(ColorRGB.BLUE);
                lblDescripcionMenu.setText("Top 5 Mejores Jugadores");
                txtTopJugadores.setText("");
                String topJugadores = controlador.deserializar();
                if (!topJugadores.isEmpty()) {
                    ColorRGB.appendColor(txtTopJugadores, topJugadores, ColorRGB.ORANGE);
                } else {
                    ColorRGB.appendColor(txtTopJugadores, " El top de jugadores se encuentra vacio.", ColorRGB.RED);
                }
                colocarTituloFondo(pnlCardTopJugadores);
                ajustarTamanioTitulo();
                cambiarVista(pnlCardTopJugadores);
            }
        });

        btnMostarJugadores.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                lblDescripcionMenu.setForeground(ColorRGB.BLUE);
                lblDescripcionMenu.setText("Jugadores Conectados");
                txtTopJugadores.setText("");
                ColorRGB.appendColor(txtTopJugadores, controlador.mostrarJugadores(), ColorRGB.GREEN);
                colocarTituloFondo(pnlCardTopJugadores);
                ajustarTamanioTitulo();
                cambiarVista(pnlCardTopJugadores);
            }
        });

        btnCargarPartida.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!controlador.cargarPartida()) {
                    mostrarTexto("No hay partida guardada para cargar o jugadores suficientes conectados.", true);
                }
            }
        });

        pnlTopJugadores.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mostrarMenuPrincipal();
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

    private void eventoTomarPozo() {
        if (controlador.isJugadorTurno()) {
            if (!fichaRecogida) {
                fichaRecogida = true;
                tomarFichaPozo();
                mostrarAtril(controlador.mostrarAtril());
            } else {
                mostrarPopUp("No puedes volver a tomar el pozo o tomar una ficha del mazo durante este turno", ColorRGB.RED, pnlCardPartida);
            }
        } else {
            mostrarPopUp("Aguarde su turno", ColorRGB.RED, pnlCardPartida);
        }
    }

    private void eventoTomarFichaMazo() {
        if (controlador.isJugadorTurno()) {
            if (!fichaRecogida) {
                fichaRecogida = true;
                tomarFichaMazo();
                mostrarAtril(controlador.mostrarAtril());
            } else {
                mostrarPopUp("No puedes volver a tomar una ficha del mazo o tomar el pozo durante este turno", ColorRGB.RED, pnlCardPartida);
            }
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

    private void ajustarTamanioTitulo() {
        int ancho = frame.getWidth();
        int alto = frame.getHeight();
        // Ajustar el tamaño preferido del JLabelFondo
        lblBurako.setPreferredSize(new Dimension(ancho, alto / 4));
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
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss: ");
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
        colocarTituloFondo(pnlCardJugador);
        cambiarVista(pnlCardJugador);
        controlador.deserializar();
        frame.setVisible(true);
    }

    /**
     * Muestra por pantalla que jugador posee el turno.
     *
     * @param jugador Nombre del jugador.
     */
    @Override
    public void mostrarTurno(String jugador) {
        mostrarTexto("Es el turno de " + jugador.toUpperCase() + "\n", false);
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
                popUp.dispose(); // Cerrar el mensaje emergente después de 3 segundos
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
        if (controlador.getCantidadJugadores() >= 4) {
            pnlEast.setVisible(true);
            pnlWest.setVisible(true);
            lblJugadorEast.setText(controlador.getOponente(1).toUpperCase());
            lblJugadorWest.setText(controlador.getOponente(2).toUpperCase());
        } else {
            pnlEast.setVisible(false);
            pnlWest.setVisible(false);
        }
        // Setea los colores de los paneles con transparencia
        pnlJugadasOponente.setBackground(new Color(33, 37, 43, 150));
        pnlJugadas.setBackground(new Color(33, 37, 43, 150));
        pnlSouth.setBackground(new Color(33, 37, 43, 240));
        pnlJugadorNorth.setBackground(new Color(33, 37, 43, 240));
        pnlJugadorWest.setBackground(new Color(33, 37, 43, 240));
        pnlJugadorEast.setBackground(new Color(33, 37, 43, 240));
        // Coloca la textura de fondo
        pnlFelt = new JPanelFondo("/ar/edu/unlu/poo/burako/texture/greenFeltTexture.png");
        pnlFelt.setOpaque(false); // Hace que sea visible los componentes sobre él
        pnlFelt.setLayout(new BorderLayout());
        // Ajusta la jerarquía de paneles
        splMain.remove(pnlMenu);
        pnlFelt.add(pnlMenu, BorderLayout.CENTER);
        splMain.setLeftComponent(pnlFelt);
        // Setea en transparente para que vea el color asignado en la lista de fichas
        scpSouth.getViewport().setOpaque(false);
        // Cambia a la vista principal de Partida
        cambiarVista(pnlCardPartida);
        // Asigna los nombres de los jugadores
        lblSouth.setForeground(ColorRGB.CYAN);
        lblSouth.setText(" " + controlador.getJugador().toUpperCase() + " ");
        lblJugadorNorth.setForeground(ColorRGB.RED);
        lblJugadorNorth.setText(controlador.getOponente(0).toUpperCase());
        // TODO Implementar cambio de ubicacion de jugadores, arriba el compañero
        // TODO para 4 jugadores
        lblJugadorEast.setForeground(ColorRGB.CYAN);

        lblJugadorWest.setForeground(ColorRGB.RED);

        mostrarAtril(atril); // Muestra las fichas del atril
        // Ajusta los paneles de Pila de Muertos, Mazo y Pozo
        setLypPilaMuertos();
        setLypMazo();
        setLypPozo();
        // Ajusta el panel del oponente
        setLypJugador(lypJugadorNorth, lblFichasAtrilNorth, 1);
        setLypJugador(lypJugadorWest, lblFichasAtrilWest, 1);
        setLypJugador(lypJugadorEast, lblFichasAtrilEast, 1);
        mostrarTurno(controlador.nombreJugadorTurno());
        fichaRecogida = false;
    }

    private void setLypJugador(JLayeredPane lypJugador, JLabel lblFichasAtril, int desplazamiento) {
        lypJugador.setLayout(null);
        RecortarMosaico cut = new RecortarMosaico();

        ImageIcon imagen = cut.getImagenRecortadaIcon(4, 1, 45, 60, 0);
        JLabel lblImgV1 = new JLabel(imagen);
        lblImgV1.setBounds(6, 0, 60, 60);

        imagen = cut.getImagenRecortadaIcon(4, 1, 60, 60, 6);
        JLabel lblImgV2 = new JLabel(imagen);
        lblImgV2.setBounds(8, 2, 60, 60);

        lypJugador.add(lblImgV1, Integer.valueOf(0));
        lypJugador.add(lblImgV2, Integer.valueOf(1));

        lblFichasAtril.setText(String.valueOf(controlador.getCantidadFichasAtril(desplazamiento)));
        lblFichasAtril.revalidate();
        lblFichasAtril.repaint();
        setLabelCantidadFichas(lypJugador, lblFichasAtril);
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

        lblImgV3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                eventoTomarFichaMazo();
            }
        });

        //TODO No redibujar las fichas de mazo de forma innecesaria
        lblMazoSize.setText(String.valueOf(controlador.cantidadFichasMazo()));
        setLabelCantidadFichas(lypMazo, lblMazoSize);
    }

    private void setLabelCantidadFichas(JComponent contenedor, JLabel labelCantidadFichas) {
        labelCantidadFichas.setForeground(ColorRGB.YELLOW);
        Font font = labelCantidadFichas.getFont();
        labelCantidadFichas.setFont(new Font(font.getName(), font.getStyle(), 15)); // Tamaño de fuente deseado
        String temp = labelCantidadFichas.getText();
        labelCantidadFichas.setText("99");
        Dimension size = labelCantidadFichas.getPreferredSize(); // Obtener el tamaño preferido del JLabel lblPozoSize
        labelCantidadFichas.setText(temp);
        labelCantidadFichas.setBounds(0, 41, size.width, size.height);
        contenedor.add(labelCantidadFichas, Integer.valueOf(3));
        contenedor.setPreferredSize(new Dimension(60, 60));
        contenedor.revalidate();
        contenedor.repaint();
    }

    private void setLypPozo() {
        lypPozo.setLayout(null);

        areaPozo.setOpaque(true);
        areaPozo.setBackground(new Color(33, 37, 43, 150));
        areaPozo.setBounds(0, 0, 43, 60);
        lypPozo.remove(areaPozo);
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
       /* if (juegosMesa == null || juegosMesa.isEmpty()) {
            mostrarTexto("No hay juegos en mesa\n", true);
        } else {*/
        panel.removeAll();
        int cantidadFichas;
        for (ArrayList<IFicha> juego : juegosMesa) {
            JLayeredPane lypJuegoMesa = new JLayeredPane();
            panel.add(lypJuegoMesa);
            cantidadFichas = juego.size();
            int prioridad = 0;
            RecortarMosaico cut = new RecortarMosaico();
            int x = 0;
            int y = 0;
            for (IFicha ficha : juego) {
                JLabel lblImg = new JLabel(fichaRecortada(ficha, cut));
                lblImg.setName(ficha.getNumeroJugada().toString());
                lblImg.setBounds(x, y, 45, 60);
                x += 30;
                lypJuegoMesa.add(lblImg, Integer.valueOf(prioridad));
                // Agregar el MouseListener a cada componente interno (en este caso, a cada JLabel)
                lblImg.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        if (controlador.isJugadorTurno()) {
                            int[] fichasSeleccionadas = lstSouth.getSelectedIndices();
                            String[] stringFichas = new String[fichasSeleccionadas.length];
                            if (fichasSeleccionadas.length > 0) {
                                for (int i = 0; i < fichasSeleccionadas.length; i++) {
                                    stringFichas[i] = String.valueOf(fichasSeleccionadas[i] + 1);
                                }
                            }
                            Integer ubicacion = Integer.valueOf(lblImg.getName());
                            if (!controlador.agregarFichaJugadaExistente(ubicacion, stringFichas)) {
                                mostrarPopUp("No se puede agregar la ficha a la jugada en mesa", ColorRGB.RED, pnlCardPartida);
                            }
                            verificarAtril(); // Toma el muerto si el atril esta vacio
                            mostrarJuegosMesa(controlador.mostrarJuegosEnMesa());
                            mostrarAtril(controlador.mostrarAtril());
                        } else {
                            mostrarPopUp("Aguarde su turno", ColorRGB.RED, pnlCardPartida);
                        }
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
    // }

    private ImageIcon fichaRecortada(IFicha ficha, RecortarMosaico cut) {
        int color;
        int numero;
        if (ficha instanceof FichaComodin) {
            color = 4;
            numero = ficha.getNumeroFicha();
        } else {
            color = ficha.getColor().ordinal();
            numero = ficha.getNumeroFicha() - 1;
        }
        return cut.getImagenRecortadaIcon(color, numero, 45, 60, 0);
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
            JLabel lblImg = new JLabel(fichaRecortada(ficha, cut));
            lblImg.setBounds(x, y, 45, 60);
            x += 30;
            lypPozo.add(lblImg, Integer.valueOf(prioridad));
            // Agregar el MouseListener a cada Ficha (lblImg)

            /* lblImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    lblImg.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    pnlCardPartida.add(lblImg);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    lblImg.setCursor(Cursor.getDefaultCursor());
                    // Verificar si el JLabel está dentro del JPanel
                    Point lblLocation = lblImg.getLocationOnScreen();
                    Point panelLocation = pnlSouth.getLocationOnScreen();
                    Rectangle panelBounds = new Rectangle(panelLocation, pnlSouth.getSize());
                    if (panelBounds.contains(lblLocation)) {
                        pnlCardPartida.remove(lblImg);
                        eventoTomarPozo();
                    }
                }
            });

            lblImg.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    // Mover el JLabel mientras se arrastra
                    Point point = SwingUtilities.convertPoint(lblImg, e.getPoint(), frame.getContentPane());
                    lblImg.setLocation(point.x - lblImg.getWidth() / 2, point.y - lblImg.getHeight() / 2);
                }
            }); */

            lblImg.addMouseListener(new MouseAdapter() {
                private Timer longPressTimer;
                private boolean longPressDetected = false;

                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    int LONG_PRESS_TIME = 1000; // 1 segundo de pulsación prolongada

                    // Reinicia el estado de la pulsación prolongada
                    longPressDetected = false;

                    // Inicia el temporizador cuando se presiona el botón
                    longPressTimer = new Timer(LONG_PRESS_TIME, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Si el temporizador llega a 1 segundo, se considera una pulsación prolongada
                            longPressDetected = true;
                            eventoTomarPozo();
                        }
                    });
                    longPressTimer.setRepeats(false); // Solo una vez, no repetido
                    longPressTimer.start();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // Detener el temporizador si el ratón es liberado antes de que se complete la pulsación prolongada
                    if (longPressTimer != null && longPressTimer.isRunning()) {
                        longPressTimer.stop();
                    }
                    if (!longPressDetected) { // Si no fue una pulsación prolongada, realiza la acción de clic normal
                        eventoFichaPozo();
                    }
                }
            });
            prioridad++;
        }

        if (cantidadFichas > 1) {
            int ancho = 43 + (30 * (cantidadFichas - 1));
            lypPozo.setPreferredSize(new Dimension(ancho, 60));
        } else {
            lypPozo.setPreferredSize(new Dimension(43, 60));
        }
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
        SwingUtilities.invokeLater(() -> {
            RecortarMosaico cut = new RecortarMosaico();
            if (listaModeloAbajo != null) {
                listaModeloAbajo.removeAllElements();
            }
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
            lstSouth.revalidate();
            lstSouth.repaint();
            scpSouth.getViewport().revalidate();
            scpSouth.getViewport().repaint();
            scpSouth.revalidate();
            scpSouth.repaint();
            frame.revalidate();
            frame.repaint();
        });
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
        fichaRecogida = false;
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
        String puntajeFormateado = String.format("%04d", puntaje);
        lblPuntajeJugador.setText(puntajeFormateado);
        pnlMenu.revalidate();
        pnlMenu.repaint();
    }

    @Override
    public void mostrarPuntosOponente(Integer puntaje) {
        String puntajeFormateado = String.format("%04d", puntaje);
        lblPuntajeOponente.setText(puntajeFormateado);
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
            mostrarTexto("No se ha seleccionado ninguna ficha para descartar\n", true);
        } else {
            if (fichaRecogida) {
                controlador.agregarFichaPozo(opcion);
                verificarAtril(); // Toma el muerto si el atril esta vacio
                if (controlador.isCanasta() && controlador.isMuertoTomado() && controlador.atrilVacio()) {
                    controlador.finalizarPartida();
                    mostrarPopUp(controlador.getPuntos(), ColorRGB.RED, pnlCardPartida);
                    // TODO partida terminada REVISAR
                }
            } else {
                mostrarPopUp("Debes recoger una ficha del mazo o tomar el pozo antes de descartar una ficha en el pozo", ColorRGB.RED, pnlCardPartida);
            }
        }
    }

    private void tomarFichaPozo() {
        if (!controlador.isPozoVacio()) {
            controlador.recogerPozo();
        } else {
            mostrarTexto(" ERROR: El pozo esta vacio.", true);
        }
    }

    private void tomarFichaMazo() {
        if (controlador.isJugadorTurno()) {
            controlador.tomarFichaMazo();
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

    // TODO acomodar
    public void mostrarCantidadFichasAtril() {
        lblFichasAtrilNorth.setText(String.valueOf(controlador.getCantidadFichasAtril(1)));
        lblFichasAtrilNorth.revalidate();
        lblFichasAtrilNorth.repaint();
        pnlJugadorNorth.revalidate();
        pnlJugadorNorth.repaint();
        if (pnlEast.isVisible()) {
            lblFichasAtrilEast.setText(String.valueOf(controlador.getCantidadFichasAtril(2)));
            pnlJugadorEast.revalidate();
            pnlJugadorEast.repaint();
        }
        if (pnlWest.isVisible()) {
            lblFichasAtrilWest.setText(String.valueOf(controlador.getCantidadFichasAtril(3)));
            pnlJugadorWest.revalidate();
            pnlJugadorWest.repaint();
        }
    }

    /**
     * @param cantidadFichas
     */
    @Override
    public void mostrarCantidadFichasMazo(int cantidadFichas) {
        lblMazoSize.setText(String.valueOf(cantidadFichas));
        lypMazo.revalidate();
        lypMazo.repaint();
    }

    /**
     *
     */
    @Override
    public void tomarMuerto() {
        if (!tomarMuerto) {
            if (lypPilaMuertos.getComponentCount() >= 2) {
                lypPilaMuertos.remove(0);
                lypPilaMuertos.remove(0);
            }
            tomarMuerto = true;
        } else {
            if (lypPilaMuertos.getComponentCount() != 0) {
                lypPilaMuertos.remove(0);
                lypPilaMuertos.remove(0);
            }
        }
        lypPilaMuertos.revalidate();
        lypPilaMuertos.repaint();
    }

    public void verificarAtril() {
        if (controlador.atrilVacio() && !controlador.isMuertoTomado()) {
            controlador.tomarMuerto();
            mostrarTexto(" Atril Vacío. Se ha tomado el muerto.\n", false);
        }
    }

}
