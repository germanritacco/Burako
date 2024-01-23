package ar.edu.unlu.poo.burako.vista.grafica;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class JPanelFondo extends JPanel {

    private BufferedImage imagenFondo;

    public JPanelFondo(String path) {
        cargarImagen(path);
    }

    private void cargarImagen(String path) {
        try {
            imagenFondo = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImagenFondo(String path) {
        cargarImagen(path);
        revalidate();
        repaint();
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

