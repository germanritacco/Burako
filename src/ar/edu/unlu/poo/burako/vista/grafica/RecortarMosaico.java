package ar.edu.unlu.poo.burako.vista.grafica;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class RecortarMosaico extends JFrame implements Serializable {

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

    public ImageIcon getImagenRecortadaIcon(int color, int numeroFicha, int nuevoAncho, int nuevoAlto) {
        // Recortar una región específica
        int x = TILE_SIZE * (numeroFicha);  // Coordenada X del inicio de la nueva imagen
        int y = TILE_SIZE * color;  // Coordenada Y del inicio de la nueva imagen

        if (originalTexture != null) {
            BufferedImage subimage = originalTexture.getSubimage(x, y, TILE_SIZE, TILE_SIZE);

            // Ajustar el tamaño de la imagen
            Image imagenRedimensionada = subimage.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);

            // Crear un ImageIcon directamente con la imagen redimensionada
            return new ImageIcon(subimage);
        } else {
            return null;
        }
    }

    // TODO Ejemplo de prueba, eliminar en la version final.
   /* public static void main(String[] args) {
        RecortarMosaico recortador = new RecortarMosaico();
        // Obtener la imagen recortada
        int color = 3;
        int numeroFicha = 5;
        BufferedImage imagenRecortada = recortador.getImagenRecortada(color, numeroFicha);
        // Puedes hacer algo con la imagen recortada, por ejemplo, mostrarla en un JLabel
        if (imagenRecortada != null) {
            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(imagenRecortada));
            frame.getContentPane().add(label);
            frame.setSize(TILE_SIZE, TILE_SIZE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }*/

}