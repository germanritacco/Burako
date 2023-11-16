package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.vista.IVista;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        //controlador.escribirTexto();
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
        appendColor(txt, Color.GREEN);
        appendColor(txt, Color.CYAN);
        appendColor(txt, Color.MAGENTA);
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


    public void mostrarMenuPrincipal() {
        iniciar();
        flujoActual = new FlujoMenuPrincipal(this, controlador);
        flujoActual.mostrarSiguienteTexto();
    }

}
