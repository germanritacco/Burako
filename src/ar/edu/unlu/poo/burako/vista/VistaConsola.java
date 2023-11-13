package ar.edu.unlu.poo.burako.vista;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaConsola implements IVista {
    private JTextField txtEntrada;
    private JButton btnEnter;
    private JTextPane txtPane;
    private JPanel frmPrincipal;
    private JScrollPane scrollPane;
    private final JFrame frame;
    private Controlador controlador;

    public VistaConsola() {
        frame = new JFrame("Burako Consola");
        frame.setContentPane(frmPrincipal);
        frame.setSize(725, 477);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escribirTexto();
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

    public void escribirTexto() {
        controlador.escribirTexto();
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
    }
}
