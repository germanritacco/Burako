package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.modelo.FichaComodin;
import ar.edu.unlu.poo.burako.modelo.IFicha;
import ar.edu.unlu.poo.burako.vista.ColorRGB;
import ar.edu.unlu.poo.burako.vista.IVista;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VistaConsola implements IVista {
    private JTextField txtEntrada;
    private JButton btnEnter;
    private JTextPane txtPane;
    private JPanel frmPrincipal;
    private JScrollPane scrollPane;
    private final JFrame frame;
    private Controlador controlador;
    private Flujo flujoActual;

    public VistaConsola(int x, int y, int width, int height) {
        frame = new JFrame("Burako Consola");
        frame.setContentPane(frmPrincipal);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocation(x, y);
        frame.setMinimumSize(new Dimension(width, height));

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controlador.cerrarApp();
            }
        });

        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escribirTexto();
            }
        });

        txtEntrada.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    escribirTexto();
                }
            }
        });

        // Remueve el jugador del controlador y de la lista al cerrar con la cruz.
        frame.getRootPane().setDefaultButton(btnEnter);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                controlador.cerrarApp();
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
     * Hace visible el JFrame.
     */
    private void iniciar() {
        frame.setVisible(true);
    }

    /**
     * Muestra por pantalla el texto ingresado por el usuario.
     */
    private void escribirTexto() {
        appendColor(txtEntrada.getText().toUpperCase() + "\n", ColorRGB.ORANGE);
        procesarEntrada(txtEntrada.getText().toUpperCase());
        txtEntrada.setText("");
    }

    /**
     * Procesa el dato ingresado por el usuario
     *
     * @param input Dato ingresado por el usuario
     */
    private void procesarEntrada(String input) {
        input = input.trim();
        if (input.isEmpty())
            return;
        flujoActual = flujoActual.procesarEntrada(input);
        flujoActual.mostrarSiguienteTexto();
    }

    /**
     * Muestra por pantalla el texto recibido por parámetro.
     *
     * @param txt Texto a mostrar.
     */
    @Override
    public void mostrarTexto(String txt, boolean critico) {
        if (critico) {
            appendColorPosicion(txt, ColorRGB.RED, 0);
        } else {
            appendColorPosicion(txt, ColorRGB.GREEN, 0);
        }
    }

    /**
     * Muestra por pantalla la lista de fichas con su correspondiente color y un separador personalizado.
     *
     * @param fichas    Lista de fichas.
     * @param separador Separador personalizado.
     */
    private void mostrarFichas(ArrayList<IFicha> fichas, String separador) {
        for (IFicha ficha : fichas) {
            colorFicha(ficha);
            appendColor(separador, ColorRGB.GRAY);
        }
    }

    /**
     * Muestra por pantalla la lista de fichas con su índice, su correspondiente color y un separador personalizado.
     *
     * @param fichas    Lista de fichas.
     * @param separador Separador personalizado.
     */
    private void mostrarFichasIndice(ArrayList<IFicha> fichas, String separador) {
        int indice = 1;
        for (IFicha ficha : fichas) {
            if (indice < 10) {
                appendColor(" " + indice + ")   ", ColorRGB.GRAY);
            } else {
                appendColor(" " + indice + ")  ", ColorRGB.GRAY);
            }
            colorFicha(ficha);
            appendColor(separador, ColorRGB.GRAY);
            indice++;
        }
    }

    /**
     * Muestra por pantalla la ficha en su correspondiente color.
     *
     * @param ficha ficha a mostrar.
     */
    private void colorFicha(IFicha ficha) {
        Color colorRGB;
        if (ficha instanceof FichaComodin) {
            colorRGB = ColorRGB.MAGENTA;
        } else {
            switch (ficha.getColor()) {
                case NEGRO -> colorRGB = Color.WHITE;
                case AZUL -> colorRGB = ColorRGB.BLUE;
                case AMARILLO -> colorRGB = ColorRGB.YELLOW;
                case ROJO -> colorRGB = ColorRGB.RED;
                default -> colorRGB = ColorRGB.GRAY;
            }
        }
        appendColor(ficha.toString(), colorRGB);
    }

    /**
     * Cambia el flujo cuando un jugador abandona la partida.
     *
     * @param nombre Nombre del jugador.
     */
    @Override
    public void abandonarPartida(String nombre) {
        if (!controlador.isJugadorTurno()) {
            controlador.guardarPartida();
            flujoActual = new FlujoAbandonarPartida(this, controlador, nombre);
            flujoActual.mostrarSiguienteTexto();
        }
    }

    /**
     * Añade nuevo texto al final de la pantalla, en el color indicado.
     *
     * @param texto Texto a añadir.
     * @param color Color que se desea mostrar el texto.
     */
    @Override
    public void appendColor(String texto, Color color) {
        StyledDocument doc = txtPane.getStyledDocument();
        Style style = txtPane.addStyle("Style", null);
        StyleConstants.setForeground(style, color);
        try {
            doc.insertString(doc.getLength(), texto, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        txtPane.setCaretPosition(txtPane.getDocument().getLength()); // Ajusta la posición del cursor al final del documento
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
        StyledDocument doc = txtPane.getStyledDocument();
        Style style = txtPane.addStyle("Style", null);
        StyleConstants.setForeground(style, color);
        // Obtener la posición de inicio y fin de la línea especificada
        Element root = doc.getDefaultRootElement();
        int startOffset = root.getElement(numeroLinea).getStartOffset();
        int endOffset = root.getElement(numeroLinea).getEndOffset();
        try {
            // Insertar el texto en la línea especificada
            doc.insertString(endOffset, texto, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        txtPane.setCaretPosition(endOffset); // Ajusta la posición del cursor al final de la línea
    }

    /**
     * Punto de partida de ejecución, iniciación con el flujo de nuevo jugador.
     */
    @Override
    public void nuevoJugador() {
        iniciar();
        flujoActual = new FlujoNuevoJugador(this, controlador);
        flujoActual.mostrarSiguienteTexto();

    }

    /**
     * Muestra por pantalla que jugador posee el turno.
     *
     * @param jugador Nombre del jugador.
     */
    @Override
    public void mostrarTurno(String jugador) {
        appendColor(" Es el turno de: ", ColorRGB.ORANGE);
        appendColor(jugador + "\n", ColorRGB.RED);
    }

    /**
     * Muestra por pantalla lo necesario para iniciar la partida.
     *
     * @param atril Lista de fichas que posee el jugador en el atril.
     * @param pozo  Lista de fichas que posee el pozo.
     */
    @Override
    public void iniciarPartida(ArrayList<IFicha> atril, ArrayList<IFicha> pozo) {
        appendColor("\n  ============================================================================\n", ColorRGB.CYAN);
        appendColor(" |                              PARTIDA EN CURSO                              |\n", ColorRGB.CYAN);
        appendColor("  ============================================================================\n", ColorRGB.CYAN);
        mostrarTurno(controlador.nombreJugadorTurno());
        mostrarPozo(pozo);
        mostrarAtril(atril);
        if (controlador.isJugadorTurno()) {
            flujoActual = new FlujoTomarFicha(this, controlador);
            flujoActual.mostrarSiguienteTexto();
        } else {
            flujoActual = new FlujoEsperarTurno(this, controlador);
            flujoActual.mostrarSiguienteTexto();
        }
    }

    /**
     * Muestra por pantalla los juegos que posee un jugador.
     *
     * @param juegosMesa Lista de listas de fichas.
     */
    @Override
    public void mostrarJuegosMesa(ArrayList<ArrayList<IFicha>> juegosMesa) {
        appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        if (juegosMesa == null || juegosMesa.isEmpty()) {
            appendColor(" No hay juegos en mesa\n", ColorRGB.RED);
        } else {
            appendColor(" Juegos en mesa: \n", ColorRGB.GREEN);
            int numeroJuego = 1;
            for (ArrayList<IFicha> juego : juegosMesa) {
                appendColor("N°" + numeroJuego + ": ", ColorRGB.GREEN);
                mostrarFichas(juego, " | ");
                appendColor("\n", ColorRGB.CYAN);
                numeroJuego++;
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
        appendColor("\n ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        appendColor(" Fichas en Pozo: ", ColorRGB.GREEN);
        mostrarFichas(pozo, " | ");
    }

    /**
     * Muestra por pantalla el atril del jugador.
     *
     * @param atril Lista de fichas que posee el atril.
     */
    @Override
    public void mostrarAtril(ArrayList<IFicha> atril) {
        appendColor("\n ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        appendColor(" Atril: \n", ColorRGB.GREEN);
        if (atril == null || atril.isEmpty()) {
            appendColor(" No hay fichas\n", ColorRGB.RED);
        } else {
            mostrarFichasIndice(atril, "\n");
        }
    }

    /**
     * Deshabilita los componentes que permiten la entrada de texto por parte del usuario.
     */
    @Override
    public void disableComponents() {
        txtEntrada.setEnabled(false);
        txtEntrada.setVisible(false);
        btnEnter.setEnabled(false);
        btnEnter.setVisible(false);
    }

    /**
     * Habilita los componentes que permiten la entrada de texto por parte del usuario y asigna el flujo correspondiente.
     */
    @Override
    public void enableComponents(boolean cambioTurno) {
        txtEntrada.setEnabled(true);
        txtEntrada.setVisible(true);
        btnEnter.setEnabled(true);
        btnEnter.setVisible(true);
        if (cambioTurno) {
            flujoActual = new FlujoTomarFicha(this, controlador);
            flujoActual.mostrarSiguienteTexto();
        }
    }

    /**
     * Cambia al flujo correspondiente de mostrar el menu principal.
     */
    @Override
    public void mostrarMenuPrincipal() {
        flujoActual = new FlujoMenuPrincipal(this, controlador);
        flujoActual.mostrarSiguienteTexto();
    }

    /**
     *
     */
    @Override
    public void mostrarPuntos(Integer puntaje) {
        appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        appendColor(" Puntos: ", ColorRGB.GREEN);
        appendColor(puntaje.toString() + "\n", ColorRGB.ORANGE);
    }

    /**
     * @param puntaje
     */
    @Override
    public void mostrarPuntosOponente(Integer puntaje) {
        appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        appendColor(" Puntos Oponente: ", ColorRGB.GREEN);
        appendColor(puntaje.toString() + "\n", ColorRGB.ORANGE);
    }

    /**
     * @param juegosMesa
     */
    @Override
    public void mostrarJuegosMesaOponente(ArrayList<ArrayList<IFicha>> juegosMesa) {
        appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        if (juegosMesa == null || juegosMesa.isEmpty()) {
            appendColor(" No hay juegos del oponente en mesa\n", ColorRGB.RED);
        } else {
            appendColor(" Juegos del oponente en mesa: \n", ColorRGB.GREEN);
            int numeroJuego = 1;
            for (ArrayList<IFicha> juego : juegosMesa) {
                appendColor("N°" + numeroJuego + ": ", ColorRGB.GREEN);
                mostrarFichas(juego, " | ");
                appendColor("\n", ColorRGB.CYAN);
                numeroJuego++;
            }
        }
    }

    /**
     *
     */
    @Override
    public void mostrarCantidadFichasAtril() {
        appendColor("\n ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        appendColor(" Cantidad de fichas en el atril: ", ColorRGB.GREEN);
        Integer cantidadFichas = controlador.getCantidadFichasAtril(0);
        appendColor(cantidadFichas.toString() + "\n", ColorRGB.RED);
        Integer cantidadJugadores = controlador.getCantidadJugadores();
        for (int i = 1; i < cantidadFichas - 1; i++) {
            appendColor(" Cantidad de fichas en el atril de ", ColorRGB.GREEN);
            String oponente = controlador.getOponente(i);
            appendColor(oponente + ": ", ColorRGB.GREEN);
            cantidadFichas = controlador.getCantidadFichasAtril(i);
            appendColor(cantidadFichas.toString() + "\n", ColorRGB.RED);
        }
    }

    /**
     * @param cantidadFichas
     */
    @Override
    public void mostrarCantidadFichasMazo(int cantidadFichas) {
        appendColor("\n ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        appendColor(" Cantidad de fichas en el mazo: ", ColorRGB.GREEN);
        appendColor(String.valueOf(cantidadFichas) + "\n", ColorRGB.RED);
    }

    /**
     *
     */
    @Override
    public void tomarMuerto() {
        // No es necesario VistaConsola.
    }

}
