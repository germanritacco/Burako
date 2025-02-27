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

    /**
     * Verifica si el atril esta vacio.
     *
     * @return <li>TRUE: Si el atril se encuentra vacío.</li><li>FALSE: Si hay fichas en el atril.</li>
     */
    boolean atrilIsEmpty();

    /**
     * Verífica si el muerto fue tomado.
     *
     * @return <li>TRUE: Si el muerto fue tomado.</li><li>FALSE: Si el muerto no fue tomado.</li>
     */
    boolean isTomoMuerto();

    /**
     * Cambia el estado de 'tomoMuerto'.
     *
     * @param tomoMuerto
     */
    void setTomoMuerto(boolean tomoMuerto);

    /**
     * Sumatoria del valor de todas las fichas del atril.
     */
    void calcularPuntosAtril();

    /**
     * Retorna el número de ID del compañero.
     *
     * @return N° de ID del compañero.
     */
    Integer getCompanieroId();

    /**
     * Asigna el ID de número de compañero.
     *
     * @param companieroId N° de ID del compañero.
     */
    void setCompanieroId(int companieroId);
}
