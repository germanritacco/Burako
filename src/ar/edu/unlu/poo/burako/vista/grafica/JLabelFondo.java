package ar.edu.unlu.poo.burako.vista.grafica;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class JLabelFondo extends JLabel {

    private BufferedImage imagenFondo;

    public JLabelFondo(String path) {

        try {
            imagenFondo = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Rellenar el panel sin deformar la imagen
        int ancho = getWidth();
        int alto = getHeight();
        int imagenAncho = imagenFondo.getWidth(this);
        int imagenAlto = imagenFondo.getHeight(this);

        double escalaAncho = (double) ancho / imagenAncho;
        double escalaAlto = (double) alto / imagenAlto;

        double escala = Math.max(escalaAncho, escalaAlto);

        int nuevoAncho = (int) (imagenAncho * escala);
        int nuevoAlto = (int) (imagenAlto * escala);

        int x = (ancho - nuevoAncho) / 2;
        int y = (alto - nuevoAlto) / 2;

        g.drawImage(imagenFondo, x, y, nuevoAncho, nuevoAlto, this);
    }

}
