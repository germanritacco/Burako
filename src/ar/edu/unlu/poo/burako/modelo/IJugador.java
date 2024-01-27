package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public interface IJugador extends Serializable {
    /**
     * Retorna si el jugador posee el turno.
     *
     * @return <li>TRUE: Si el jugador posee el turno.</li><li>FALSE: Si el jugador no posee el turno.</li>
     */
    boolean isTurno();

    /**
     * Cambia el estado del turno del jugador.
     *
     * @param estado <li>TRUE: Si el jugador posee el turno.</li><li>FALSE: Si el jugador no posee el turno.</li>
     */
    void setTurno(boolean estado);

    /**
     * Retorna el N° de ID del jugador.
     *
     * @return N° de ID del jugador.
     */
    int getId();

    /**
     * Resetea los puntos a 0.
     */
    void reset();

    /**
     * Retorna el nombre del jugador.
     *
     * @return Nombre del jugador.
     */
    String getNombre();

    /**
     * Retorna el puntaje del jugador.
     *
     * @return Puntos.
     */
    Integer getPuntos();

    void setPuntos(int puntos);

    /**
     * Retorna las fichas que posee el atril del jugador.
     *
     * @return Lista de fichas.
     */
    ArrayList<Ficha> getAtril();

    /**
     * Agrega al atril una lista de fichas.
     *
     * @param atril Lista de fichas.
     */
    void addAtril(ArrayList<Ficha> atril);

    /**
     * Agrega al atril una ficha.
     *
     * @param ficha Ficha.
     */
    void addFichaAtril(Ficha ficha);

    /**
     * Remueve una ficha del atril.
     *
     * @param posicion Posicion de la ficha.
     * @return Ficha eliminada del atril.
     */
    Ficha removeFichaAtril(int posicion);

    boolean atrilIsEmpty();

    boolean isTomoMuerto();


    void setTomoMuerto(boolean tomoMuerto);

    void calcularPuntosAtril();

    int getCompanieroId();

    void setCompanieroId(int companieroId);
}
