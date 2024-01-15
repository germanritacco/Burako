package ar.edu.unlu.poo.burako.vista.grafica;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.vista.IVista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaGrafica implements IVista {
    private JPanel pnlMain;
    private JTabbedPane tabPartida;
    private JPanel pnlPartida;
    private JList lstNorth;
    private JList lstWest;
    private JList lstEast;
    private JList lstSouth;
    private JPanel pnlCenter;
    private JLabel lblJugadorNorth;
    private JLabel lblJugadorWest;
    private JLabel lblEast;
    private JLabel lblSouth;
    private JPanel test1;
    private JPanel pnlNorth;
    private JPanel pnlWest;
    private JPanel pnlEast;
    private JPanel pnlSouth;
    private JPanel pnlFelt;
    private final JFrame frame;

    private Image imgTablero;
    private Controlador controlador;

    public VistaGrafica() {
        frame = new JFrame("Burako Grafico");
        frame.setContentPane(pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

frame.pack();





        // Cargar la imagen de fondo
       try {
            imgTablero = new ImageIcon(getClass().getResource("/ar/edu/unlu/poo/burako/texture/greenFeltTexture.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurar el pnlCenter
        pnlFelt = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Rellenar el panel sin deformar la imagen
                int ancho = getWidth();
                int alto = getHeight();
                int imagenAncho = imgTablero.getWidth(this);
                int imagenAlto = imgTablero.getHeight(this);

                double escalaAncho = (double) ancho / imagenAncho;
                double escalaAlto = (double) alto / imagenAlto;

                double escala = Math.max(escalaAncho, escalaAlto);

                int nuevoAncho = (int) (imagenAncho * escala);
                int nuevoAlto = (int) (imagenAlto * escala);

                int x = (ancho - nuevoAncho) / 2;
                int y = (alto - nuevoAlto) / 2;

                g.drawImage(imgTablero, x, y, nuevoAncho, nuevoAlto, this);
            }
        };

        // Establecer un layout para pnlCenter (puedes ajustar según tus necesidades)
        pnlFelt.setLayout(new BorderLayout());
        pnlCenter.add(pnlFelt, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();


    }


    /**
     * Asigna el controlador de la vista.
     *
     * @param controlador Instancia de controlador.
     */
    @Override
    public void setControlador(Controlador controlador) {

    }

    /**
     * Muestra por pantalla el texto recibido por parámetro.
     *
     * @param txt Texto a mostrar.
     */
    @Override
    public void mostrarTexto(String txt) {

    }

    /**
     * Cambia el flujo cuando un jugador abandona la partida.
     *
     * @param nombre Nombre del jugador.
     */
    @Override
    public void abandonarPartida(String nombre) {

    }

    /**
     * Añade nuevo texto al final de la pantalla, en el color indicado.
     *
     * @param texto Texto a añadir.
     * @param color Color que se desea mostrar el texto.
     */
    @Override
    public void appendColor(String texto, Color color) {

    }

    /**
     * Añade nuevo texto en la posición indicada de la pantalla, en el color indicado.
     *
     * @param texto       Texto a añadir.
     * @param color       Color que se desea mostrar el texto.
     * @param numeroLinea Posición donde se desea agregar el texto.
     */
    @Override
    public void appendColorPosicion(String texto, Color color, int numeroLinea) {

    }

    /**
     * Punto de partida de ejecución, iniciación con el flujo de nuevo jugador.
     */
    @Override
    public void nuevoJugador() {
        frame.setVisible(true);
    }

    /**
     * Muestra por pantalla que jugador posee el turno.
     *
     * @param jugador Nombre del jugador.
     */
    @Override
    public void mostrarTurno(String jugador) {

    }

    /**
     * Muestra por pantalla lo necesario para iniciar la partida.
     *
     * @param atril Lista de fichas que posee el jugador en el atril.
     * @param pozo  Lista de fichas que posee el pozo.
     */
    @Override
    public void iniciarPartida(ArrayList<String> atril, ArrayList<String> pozo) {

    }

    /**
     * Muestra por pantalla los juegos que posee un jugador.
     *
     * @param juegosMesa Lista de listas de fichas.
     */
    @Override
    public void mostrarJuegosMesa(ArrayList<ArrayList<String>> juegosMesa) {

    }

    /**
     * Muestra por pantalla el pozo.
     *
     * @param pozo Lista de fichas que posee el pozo.
     */
    @Override
    public void mostrarPozo(ArrayList<String> pozo) {

    }

    /**
     * Muestra por pantalla el atril del jugador.
     *
     * @param atril Lista de fichas que posee el atril.
     */
    @Override
    public void mostrarAtril(ArrayList<String> atril) {

    }

    /**
     * Deshabilita los componentes que permiten la entrada de texto por parte del usuario.
     */
    @Override
    public void disableComponents() {

    }

    /**
     * Habilita los componentes que permiten la entrada de texto por parte del usuario y asigna el flujo correspondiente.
     */
    @Override
    public void enableComponents() {

    }

    /**
     * Cambia al flujo correspondiente de mostrar el menu principal.
     */
    @Override
    public void mostrarMenuPrincipal() {

    }

    /**
     * @param puntaje
     */
    @Override
    public void mostrarPuntos(String puntaje) {

    }

    public static void main(String[] args) {
        IVista vista = new VistaGrafica();

    }
}
