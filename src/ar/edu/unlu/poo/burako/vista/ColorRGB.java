package ar.edu.unlu.poo.burako.vista;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class ColorRGB {

    public static final Color CYAN = new Color(86, 182, 194);
    public static final Color RED = new Color(224, 108, 117);
    public static final Color BLUE = new Color(97, 175, 239);
    public static final Color MAGENTA = new Color(198, 120, 221);
    public static final Color YELLOW = new Color(229, 192, 123);
    public static final Color GREEN = new Color(152, 195, 121);
    public static final Color ORANGE = new Color(209, 154, 102);
    public static final Color PINK = new Color(152, 195, 121);
    public static final Color GRAY = new Color(100, 106, 115);

    private ColorRGB() {
        // Constructor privado para evitar instanciación
    }

    // Método estático para realizar la operación de apendizado de texto con color
    public static void appendColor(JTextPane textPane, String texto, Color color) {
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Style", null);
        StyleConstants.setForeground(style, color);
        try {
            doc.insertString(doc.getLength(), texto, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        textPane.setCaretPosition(doc.getLength()); // Ajusta la posición del cursor al final del documento
    }
}
