package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements IJugador {

    private final String nombre;
    private Integer puntos;
    private ArrayList<Ficha> atril;
    private boolean turno;
    private static int ID = 0;
    private int id;

    private int companieroId;

    private boolean tomoMuerto;

    /**
     * Retorna si el jugador posee el turno.
     *
     * @return <li>TRUE: Si el jugador posee el turno.</li><li>FALSE: Si el jugador no posee el turno.</li>
     */
    @Override
    public boolean isTurno() {
        return turno;
    }

    /**
     * Cambia el estado del turno del jugador.
     *
     * @param estado <li>TRUE: Si el jugador posee el turno.</li><li>FALSE: Si el jugador no posee el turno.</li>
     */
    @Override
    public void setTurno(boolean estado) {
        this.turno = estado;
    }

    /**
     * Retorna el N° de ID del jugador.
     *
     * @return N° de ID del jugador.
     */
    @Override
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
        this.tomoMuerto = false;
    }

    /**
     * Resetea los puntos a 0.
     */
    @Override
    public void reset() {
        puntos = 0;
    }

    /**
     * Retorna el nombre del jugador.
     *
     * @return Nombre del jugador.
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el puntaje del jugador.
     *
     * @return Puntos.
     */
    @Override
    public Integer getPuntos() {
        return puntos;
    }

    @Override
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * Retorna las fichas que posee el atril del jugador.
     *
     * @return Lista de fichas.
     */
    @Override
    public ArrayList<Ficha> getAtril() {
        return atril;
    }

    /**
     * Agrega al atril una lista de fichas.
     *
     * @param atril Lista de fichas.
     */
    @Override
    public void addAtril(ArrayList<Ficha> atril) {
        this.atril.addAll(atril);
    }

    /**
     * Agrega al atril una ficha.
     *
     * @param ficha Ficha.
     */
    @Override
    public void addFichaAtril(Ficha ficha) {
        this.atril.add(ficha);
    }

    /**
     * Remueve una ficha del atril.
     *
     * @param posicion Posicion de la ficha.
     * @return Ficha eliminada del atril.
     */
    @Override
    public Ficha removeFichaAtril(int posicion) {
        return this.atril.remove(posicion);
    }

    @Override
    public boolean atrilIsEmpty() {
        return this.atril.isEmpty();
    }

    @Override
    public boolean isTomoMuerto() {
        return tomoMuerto;
    }

    @Override
    public void setTomoMuerto(boolean tomoMuerto) {
        this.tomoMuerto = tomoMuerto;
    }

    @Override
    public void calcularPuntosAtril() {
        int suma = 0;
        for (Ficha ficha : atril) {
            suma += ficha.getValorFicha();
        }
        this.puntos = suma;
    }

    @Override
    public int getCompanieroId() {
        return companieroId;
    }

    @Override
    public void setCompanieroId(int companieroId) {
        this.companieroId = companieroId;
    }
}
