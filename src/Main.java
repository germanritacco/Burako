import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.modelo.Burako;
import ar.edu.unlu.poo.burako.vista.IVista;
import ar.edu.unlu.poo.burako.vista.consola.VistaConsola;
import ar.edu.unlu.poo.burako.vista.VistaGrafica;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Burako modelo = new Burako();
                    IVista frameConsola = new VistaConsola();
                    IVista frameConsola2 = new VistaConsola();
                    IVista frameGrafica = new VistaGrafica();
                    //Controlador controlador = new Controlador(frameConsola, modelo);
                    //Controlador controlador2 = new Controlador(frameConsola2, modelo);
                    //Controlador controlador3 = new Controlador(frameGrafica, modelo);
                    frameConsola.iniciar();

                    frameConsola2.iniciar();
                    frameGrafica.iniciar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}