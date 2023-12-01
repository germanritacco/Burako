package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Jugador implements Serializable {

    private final String nombre;

    private Integer puntos;

    private ArrayList<Ficha> atril;

    private boolean turno;

    private static int ID = 0;

    private int id;

    /**
     * Retorna si el jugador posee el turno.
     *
     * @return <li>TRUE: Si el jugador posee el turno.</li><li>FALSE: Si el jugador no posee el turno.</li>
     */
    public boolean isTurno() {
        return turno;
    }

    /**
     * Cambia el estado del turno del jugador.
     *
     * @param estado <li>TRUE: Si el jugador posee el turno.</li><li>FALSE: Si el jugador no posee el turno.</li>
     */
    public void setTurno(boolean estado) {
        this.turno = estado;
    }

    /**
     * Retorna el N° de ID del jugador.
     *
     * @return N° de ID del jugador.
     */
    public int getId() {
        return id;
    }

    /**
     * Constructor de clase.
     * <li>Asigna el nombre.</li>
     * <li>Resetea los puntos.</li>
     * <li>Instancia el atril.</li>
     * <li>Asigna el turno en falso.</li>
     * <li>Asigna el ID.</li>
     *
     * @param nombre Nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.reset();
        this.atril = new ArrayList<>();
        this.turno = false;
        this.id = Jugador.ID++;
    }

    /**
     * Resetea los puntos a 0.
     */
    public void reset() {
        puntos = 0;
    }

    /**
     * Retorna el nombre del jugador.
     *
     * @return Nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el puntaje del jugador.
     *
     * @return Puntos.
     */
    public Integer getPuntos() {
        return puntos;
    }

    /**
     * Retorna las fichas que posee el atril del jugador.
     *
     * @return Lista de fichas.
     */
    public ArrayList<Ficha> getAtril() {
        return atril;
    }

    /**
     * Agrega al atril una lista de fichas.
     *
     * @param atril Lista de fichas.
     */
    public void addAtril(ArrayList<Ficha> atril) {
        this.atril.addAll(atril);
    }

    /**
     * Agrega al atril una ficha.
     *
     * @param ficha Ficha.
     */
    public void addFichaAtril(Ficha ficha) {
        this.atril.add(ficha);
    }

    /**
     * Remueve una ficha del atril.
     *
     * @param posicion Posicion de la ficha.
     * @return Ficha eliminada del atril.
     */
    public Ficha removeFichaAtril(int posicion) {
        return this.atril.remove(posicion);
    }

}
