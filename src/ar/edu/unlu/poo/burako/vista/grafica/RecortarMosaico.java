package ar.edu.unlu.poo.burako.vista.grafica;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RecortarMosaico implements Serializable {

    private final BufferedImage originalTexture;
    private final Map<String, ImageIcon> cache;
    private static final int TILE_SIZE = 100;

    public RecortarMosaico() {
        originalTexture = loadImage("/ar/edu/unlu/poo/burako/texture/burakoTexture.png"); // Cargar la imagen original
        cache = new HashMap<>(); // Inicializar el caché
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ImageIcon getImagenRecortadaIcon(int color, int numeroFicha, int nuevoAncho, int nuevoAlto, double angulo) {
        String cacheKey = color + "-" + numeroFicha + "-" + nuevoAncho + "-" + nuevoAlto + "-" + angulo;
        int ancho = 60;
        int alto = 80;
        int desplazamientoX = 20;
        int desplazamientoY = 10;

        // Verificar si la imagen está en el caché
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }

        // Recortar una región específica
        int x = TILE_SIZE * (numeroFicha);  // Coordenada X del inicio de la nueva imagen
        int y = TILE_SIZE * color;  // Coordenada Y del inicio de la nueva imagen

        if (originalTexture != null) {
            BufferedImage subimage;
            if (angulo == 0) {
                subimage = originalTexture.getSubimage(x + desplazamientoX, y + desplazamientoY, ancho, alto);
            } else {
                subimage = originalTexture.getSubimage(x + desplazamientoY, y + desplazamientoY, alto, alto);
            }

            // Rotar la imagen
            AffineTransform at = new AffineTransform();
            at.rotate(Math.toRadians(angulo), alto / 2.0, alto / 2.0);
            AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            subimage = op.filter(subimage, null);

            // Ajustar el tamaño de la imagen
            Image imagenRedimensionada = subimage.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
            // Crear un ImageIcon directamente con la imagen redimensionada
            ImageIcon imagenIcon = new ImageIcon(imagenRedimensionada);

            // Almacenar en caché el ImageIcon
            cache.put(cacheKey, imagenIcon);

            return imagenIcon;
        } else {
            return null;
        }
    }
}
