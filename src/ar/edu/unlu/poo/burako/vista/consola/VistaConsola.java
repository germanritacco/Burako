package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;
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
        appendColor(txtEntrada.getText() + "\n", Color.ORANGE);
        procesarEntrada(txtEntrada.getText());
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

    public void mostrarAtril(ArrayList<String> fichas) {
        for (String ficha : fichas) {
            if (ficha.contains("NEGRO")) {
                appendColor(ficha + "\n", Color.WHITE);
            } else if (ficha.contains("AZUL")) {
                appendColor(ficha + "\n", Color.BLUE);
            } else if (ficha.contains("AMARILLO")) {
                appendColor(ficha + "\n", Color.YELLOW);
            } else if (ficha.contains("ROJO")) {
                appendColor(ficha + "\n", Color.RED);
            } else {
                // Si no contiene ninguna palabra clave, usar un color predeterminado
                appendColor(ficha + "\n", Color.MAGENTA);
            }
        }
    }

    /**
     * @param nombre
     */
    @Override
    public void abandonarPartida(String nombre) {

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

    public void mostrarPartida(String jugador, ArrayList<String> atril){
        mostrarTurno(jugador);
        mostrarAtril(atril);
        if (!controlador.isTurno()){
            flujoActual = new FlujoEsperarTurno(this, controlador);
            flujoActual.mostrarSiguienteTexto();
        } else if ((controlador.isTurno())){
            flujoActual = new FlujoTurno(this, controlador);
            flujoActual.mostrarSiguienteTexto();
        }


    }

    private void mostrarTurno(String jugador) {
        appendColor(" Es el turno de: ", Color.ORANGE);
        appendColor(jugador + "\n", Color.RED);
    }

    public void disableComponents(){
        txtEntrada.setEnabled(false);
        txtEntrada.setVisible(false);
        btnEnter.setEnabled(false);
        btnEnter.setVisible(false);
    }

    public void enableComponents(){
        txtEntrada.setEnabled(false);
        txtEntrada.setVisible(false);
        btnEnter.setEnabled(false);
        btnEnter.setVisible(false);
    }

}
