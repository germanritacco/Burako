package ar.edu.unlu.poo.burako.vista;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.modelo.IFicha;

import java.awt.*;
import java.util.ArrayList;

public interface IVista {

    /**
     * Asigna el controlador de la vista.
     *
     * @param controlador Instancia de controlador.
     */
    void setControlador(Controlador controlador);

    /**
     * Muestra por pantalla el texto recibido por parámetro.
     *
     * @param txt Texto a mostrar.
     */
    void mostrarTexto(String txt);

    /**
     * Cambia el flujo cuando un jugador abandona la partida.
     *
     * @param nombre Nombre del jugador.
     */
    void abandonarPartida(String nombre);

    /**
     * Añade nuevo texto al final de la pantalla, en el color indicado.
     *
     * @param texto Texto a añadir.
     * @param color Color que se desea mostrar el texto.
     */
    void appendColor(String texto, Color color);

    /**
     * Añade nuevo texto en la posición indicada de la pantalla, en el color indicado.
     *
     * @param texto       Texto a añadir.
     * @param color       Color que se desea mostrar el texto.
     * @param numeroLinea Posición donde se desea agregar el texto.
     */
    void appendColorPosicion(String texto, Color color, int numeroLinea);

    /**
     * Punto de partida de ejecución, iniciación con el flujo de nuevo jugador.
     */
    void nuevoJugador();

    /**
     * Muestra por pantalla que jugador posee el turno.
     *
     * @param jugador Nombre del jugador.
     */
    void mostrarTurno(String jugador);

    /**
     * Muestra por pantalla lo necesario para iniciar la partida.
     *
     * @param atril Lista de fichas que posee el jugador en el atril.
     * @param pozo  Lista de fichas que posee el pozo.
     */
    void iniciarPartida(ArrayList<IFicha> atril, ArrayList<IFicha> pozo);

    /**
     * Muestra por pantalla los juegos que posee un jugador.
     *
     * @param juegosMesa Lista de listas de fichas.
     */
    void mostrarJuegosMesa(ArrayList<ArrayList<IFicha>> juegosMesa);

    /**
     * Muestra por pantalla el pozo.
     *
     * @param pozo Lista de fichas que posee el pozo.
     */
    void mostrarPozo(ArrayList<IFicha> pozo);

    /**
     * Muestra por pantalla el atril del jugador.
     *
     * @param atril Lista de fichas que posee el atril.
     */
    void mostrarAtril(ArrayList<IFicha> atril);

    /**
     * Deshabilita los componentes que permiten la entrada de texto por parte del usuario.
     */
    void disableComponents();

    /**
     * Habilita los componentes que permiten la entrada de texto por parte del usuario y asigna el flujo correspondiente.
     */
    void enableComponents();

    /**
     * Cambia al flujo correspondiente de mostrar el menu principal.
     */
    void mostrarMenuPrincipal();

    void mostrarPuntos(String puntaje);

}
