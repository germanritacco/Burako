package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;

public interface ITablero {

    /**
     * Retorna una lista de listas de Fichas, que posee los juegos en pesa.
     *
     * @return Lista de listas de Fichas.
     */
    ArrayList<ArrayList<Ficha>> getJugadaEnMesa();

    /**
     * Verifica si hay juegos en mesa.
     *
     * @return <li>TRUE: Si en mesa no hay juegos.</li><li>FALSE: Si hay juegos en mesa.</li>
     */
    boolean isVacio();

    /**
     * Retorna los jugadores que puede usar la instancia de Tablero.
     *
     * @return Lista de Jugador.
     */
    ArrayList<Jugador> getJugadores();

    /**
     * Asigna un jugador a la instancia de tablero.
     *
     * @param jugador Instancia de jugador.
     */
    void agregarJugadores(Jugador jugador);

    /**
     * Verifica si la lista de fichas posee una escalera o pierna.
     *
     * @param jugada Lista de fichas.
     * @return <li>TRUE: Si el juego es escalera o pierna.</li><li>FALSE: Si el juego no es escalera o pierna.</li>
     */
    boolean verificarJugadaNueva(ArrayList<Ficha> jugada);

    /**
     * Verifica y agrega fichas a un juego existente en mesa.
     *
     * @param jugada   Lista de fichas.
     * @param posicion Posición de la lista de fichas a donde se desea agregar nuevas fichas.
     * @return <li>TRUE: Si el juego es escalera o pierna.</li><li>FALSE: Si el juego no es escalera o pierna.</li>
     */
    boolean verificarJugadaExistente(ArrayList<Ficha> jugada, int posicion);

    /**
     * Retorna la cantidad de juegos que hay en mesa.
     *
     * @return Cantidad de juegos en mesa.
     */
    int sizeJugadaEnMesa();

    /**
     * * Retorna una lista que posee en cada posición, la lista de fichas que forman una jugada en mesa.
     *
     * @return Lista de listas de fichas que forma las jugadas.
     */
    ArrayList<ArrayList<String>> mostrarJuegosEnMesa();

}
