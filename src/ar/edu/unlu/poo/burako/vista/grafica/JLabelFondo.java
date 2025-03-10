package ar.edu.unlu.poo.burako.vista.grafica;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class JLabelFondo extends JLabel {

    private BufferedImage imagenFondo;

    public JLabelFondo(String path) {
        cargarImagen(path);
    }

    public JLabelFondo() {

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

        // Ajustar la escala solo si el ancho de la imagen después de la escala
        // es mayor que la altura disponible
        if (imagenAlto * escalaAncho > alto) {
            double escala = (double) alto / imagenAlto;
            int nuevoAncho = (int) (imagenAncho * escala);
            int nuevoAlto = (int) (imagenAlto * escala);

            int x = (ancho - nuevoAncho) / 2;
            int y = (alto - nuevoAlto) / 2;

            g.drawImage(imagenFondo, x, y, nuevoAncho, nuevoAlto, this);
        } else {
            // La imagen cabe en el ancho, usar escalaAncho
            int nuevoAncho = (int) (imagenAncho * escalaAncho);
            int nuevoAlto = (int) (imagenAlto * escalaAncho);

            int x = (ancho - nuevoAncho) / 2;
            int y = (alto - nuevoAlto) / 2;

            g.drawImage(imagenFondo, x, y, nuevoAncho, nuevoAlto, this);
        }
    }

}
