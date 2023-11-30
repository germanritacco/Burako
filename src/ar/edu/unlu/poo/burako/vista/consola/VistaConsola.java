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

    public VistaConsola() {
        frame = new JFrame("Burako Consola");
        frame.setContentPane(frmPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

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

    @Override
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void iniciar() {
        frame.setVisible(true);
    }

    private void escribirTexto() {
        appendColor(txtEntrada.getText().toUpperCase() + "\n", Color.ORANGE);
        procesarEntrada(txtEntrada.getText().toUpperCase());
        txtEntrada.setText("");
    }

    private void procesarEntrada(String input) {
        input = input.trim();
        if (input.isEmpty())
            return;
        flujoActual = flujoActual.procesarEntrada(input);
        flujoActual.mostrarSiguienteTexto();
    }

    @Override
    public void mostrarTexto(String txt) {
        appendColorPosicion(txt, Color.GREEN, 0);
    }

    public void mostrarFichas(ArrayList<String> fichas, String separador) {
        for (String ficha : fichas) {
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
            appendColor(separador, Color.GRAY);
        }
    }

    public void mostrarFichasIndice(ArrayList<String> fichas, String separador) {
        int indice = 1;
        for (String ficha : fichas) {
            if (indice < 10) {
                appendColor(" " + indice + ")   ", Color.GRAY);
            } else {
                appendColor(" " + indice + ")  ", Color.GRAY);
            }
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
            appendColor(separador, Color.GRAY);
            indice++;
        }
    }

    /**
     * @param nombre
     */
    @Override
    public void abandonarPartida(String nombre) {
        if (!controlador.isJugadorTurno()) {
            flujoActual = new FlujoAbandonarPartida(this, controlador, nombre);
            flujoActual.mostrarSiguienteTexto();
        }
    }

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

    public void nuevoJugador() {
        iniciar();
        flujoActual = new FlujoNuevoJugador(this, controlador);
        flujoActual.mostrarSiguienteTexto();

    }

    public void mostrarTurno(String jugador) {
        appendColor(" Es el turno de: ", Color.ORANGE);
        appendColor(jugador + "\n", Color.RED);
    }

    /**
     * @param fichas
     * @param pozo
     * @param nombreJugador
     */
    @Override
    public void iniciarPartida(ArrayList<String> atril, ArrayList<String> pozo, String nombreJugador) {
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
     * @param juegosMesa
     */

    /**
     * @param atril
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


    public void mostrarPozo(ArrayList<String> pozo) {
        appendColor("\n ------------------------------------------------------------------------------\n", Color.CYAN);
        appendColor(" Fichas en Pozo: ", Color.PINK);
        mostrarFichas(pozo, " | ");
    }

    public void mostrarAtril(ArrayList<String> atril) {
        appendColor("\n ------------------------------------------------------------------------------\n", Color.CYAN);
        appendColor(" Atril: \n", Color.PINK);
        mostrarFichasIndice(atril, "\n");
    }

    public void disableComponents() {
        txtEntrada.setEnabled(false);
        txtEntrada.setVisible(false);
        btnEnter.setEnabled(false);
        btnEnter.setVisible(false);
    }

    public void enableComponents() {
        txtEntrada.setEnabled(true);
        txtEntrada.setVisible(true);
        btnEnter.setEnabled(true);
        btnEnter.setVisible(true);
        flujoActual = new FlujoTomarFicha(this, controlador);
        flujoActual.mostrarSiguienteTexto();
    }


    public void mostrarMenuPrincipal(){
        flujoActual = new FlujoMenuPrincipal(this, controlador);
        flujoActual.mostrarSiguienteTexto();
    }
}
