package ar.edu.unlu.poo.burako.vista.grafica;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class RecortarMosaico implements Serializable {

    private final BufferedImage originalTexture;

    private static final int TILE_SIZE = 100;

    public RecortarMosaico() {
        originalTexture = loadImage("/ar/edu/unlu/poo/burako/texture/burakoTexture.png"); // Cargar la imagen original
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para obtener la imagen recortada
    public BufferedImage getImagenRecortada(int color, int numeroFicha) {
        // Recortar una región específica
        int x = TILE_SIZE * (numeroFicha);  // Coordenada X del inicio de la nueva imagen
        int y = TILE_SIZE * color;  // Coordenada Y del inicio de la nueva imagen
        if (originalTexture != null) {
            return originalTexture.getSubimage(x, y, TILE_SIZE, TILE_SIZE);
        } else {
            return null;
        }
    }

    public ImageIcon getImagenRecortadaIcon(int color, int numeroFicha, int nuevoAncho, int nuevoAlto, double angulo) {
        // Recortar una región específica
        int x = TILE_SIZE * (numeroFicha);  // Coordenada X del inicio de la nueva imagen
        int y = TILE_SIZE * color;  // Coordenada Y del inicio de la nueva imagen

        if (originalTexture != null) {
            BufferedImage subimage = originalTexture.getSubimage(x, y, TILE_SIZE, TILE_SIZE);

            // Rotar la imagen
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angulo), TILE_SIZE / 2.0, TILE_SIZE / 2.0);
            AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            subimage = op.filter(subimage, null);

            // Ajustar el tamaño de la imagen
            Image imagenRedimensionada = subimage.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
            // Crear un ImageIcon directamente con la imagen redimensionada
            return new ImageIcon(imagenRedimensionada);
        } else {
            return null;
        }
    }
}