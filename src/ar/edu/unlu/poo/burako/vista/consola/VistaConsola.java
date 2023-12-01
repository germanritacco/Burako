package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.modelo.Ficha;
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

    public VistaConsola(int x, int y) {
        frame = new JFrame("Burako Consola");
        frame.setContentPane(frmPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(x, y);

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

        // Cuando el botón tenga el foco y se presione "Enter", se activará automáticamente el ActionListener asociado al botón.
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
        appendColor(txtEntrada.getText().toUpperCase() + "\n", Color.ORANGE);
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
    public void mostrarTexto(String txt) {
        appendColorPosicion(txt, Color.GREEN, 0);
    }

    /**
     * Muestra por pantalla la lista de fichas con su correspondiente color y un separador personalizado.
     *
     * @param fichas    Lista de fichas.
     * @param separador Separador personalizado.
     */
    private void mostrarFichas(ArrayList<String> fichas, String separador) {
        for (String ficha : fichas) {
            colorFicha(ficha);
            appendColor(separador, Color.GRAY);
        }
    }

    /**
     * Muestra por pantalla la lista de fichas con su índice, su correspondiente color y un separador personalizado.
     *
     * @param fichas    Lista de fichas.
     * @param separador Separador personalizado.
     */
    private void mostrarFichasIndice(ArrayList<String> fichas, String separador) {
        int indice = 1;
        for (String ficha : fichas) {
            if (indice < 10) {
                appendColor(" " + indice + ")   ", Color.GRAY);
            } else {
                appendColor(" " + indice + ")  ", Color.GRAY);
            }
            colorFicha(ficha);
            appendColor(separador, Color.GRAY);
            indice++;
        }
    }

    /**
     * Muestra por pantalla la ficha en su correspondiente color.
     *
     * @param ficha ficha a mostrar.
     */
    private void colorFicha(String ficha) {
        if (ficha.contains("NEGRO")) {
            appendColor(ficha, Color.WHITE);
        } else if (ficha.contains("AZUL")) {
            appendColor(ficha, Color.BLUE);
        } else if (ficha.contains("AMARILLO")) {
            appendColor(ficha, Color.YELLOW);
        } else if (ficha.contains("ROJO")) {
            appendColor(ficha, Color.RED);
        } else {
            appendColor(ficha, Color.MAGENTA); // Comodin
        }

    }

    /**
     * Cambia el flujo cuando un jugador abandona la partida.
     *
     * @param nombre Nombre del jugador.
     */
    @Override
    public void abandonarPartida(String nombre) {
        if (!controlador.isJugadorTurno()) {
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
        appendColor(" Es el turno de: ", Color.ORANGE);
        appendColor(jugador + "\n", Color.RED);
    }


    /**
     * Muestra por pantalla lo necesario para iniciar la partida.
     *
     * @param atril Lista de fichas que posee el jugador en el atril.
     * @param pozo  Lista de fichas que posee el pozo.
     */
    @Override
    public void iniciarPartida(ArrayList<String> atril, ArrayList<String> pozo) {
        appendColor("\n  ============================================================================\n", Color.CYAN);
        appendColor(" |                              PARTIDA EN CURSO                              |\n", Color.CYAN);
        appendColor("  ============================================================================\n", Color.CYAN);
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
    public void mostrarJuegosMesa(ArrayList<ArrayList<String>> juegosMesa) {
        appendColor(" ------------------------------------------------------------------------------\n", Color.CYAN);
        if (juegosMesa == null || juegosMesa.isEmpty()) {
            appendColor(" No hay juegos en mesa\n", Color.RED);
        } else {
            appendColor(" Juegos en mesa: \n", Color.PINK);
            int numeroJuego = 1;
            for (ArrayList<String> juego : juegosMesa) {
                appendColor("N°" + numeroJuego + ": ", Color.PINK);
                mostrarFichas(juego, " | ");
                appendColor("\n", Color.CYAN);
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
    public void mostrarPozo(ArrayList<String> pozo) {
        appendColor("\n ------------------------------------------------------------------------------\n", Color.CYAN);
        appendColor(" Fichas en Pozo: ", Color.PINK);
        mostrarFichas(pozo, " | ");
    }

    /**
     * Muestra por pantalla el atril del jugador.
     *
     * @param atril Lista de fichas que posee el atril.
     */
    @Override
    public void mostrarAtril(ArrayList<String> atril) {
        appendColor("\n ------------------------------------------------------------------------------\n", Color.CYAN);
        appendColor(" Atril: \n", Color.PINK);
        mostrarFichasIndice(atril, "\n");
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
    public void enableComponents() {
        txtEntrada.setEnabled(true);
        txtEntrada.setVisible(true);
        btnEnter.setEnabled(true);
        btnEnter.setVisible(true);
        flujoActual = new FlujoTomarFicha(this, controlador);
        flujoActual.mostrarSiguienteTexto();
    }


    /**
     * Cambia al flujo correspondiente de mostrar el menu principal.
     */
    @Override
    public void mostrarMenuPrincipal() {
        flujoActual = new FlujoMenuPrincipal(this, controlador);
        flujoActual.mostrarSiguienteTexto();
    }
}
