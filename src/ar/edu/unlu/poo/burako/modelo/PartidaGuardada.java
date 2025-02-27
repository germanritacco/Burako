package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class PartidaGuardada implements Serializable {

    private Mazo mazo;
    private Pozo pozo;
    private final ArrayList<IJugador> jugadores;
    private final Tablero tableroEquipo1;
    private final Tablero tableroEquipo2;

    /**
     * Constructor de clase.
     * <li>Asigna el mazo</li>
     * <li>Asigna el pozo</li>
     * <li>Asigna la lista de jugadores</li>
     * <li>Asigna el tablero Jugador</li>
     * <li>Asigna el tablero Jugador Oponente</li>
     */
    public PartidaGuardada(Mazo mazo, Pozo pozo, ArrayList<IJugador> jugadores, Tablero tableroEquipo1, Tablero tableroEquipo2) {
        this.mazo = mazo;
        this.pozo = pozo;
        this.jugadores = jugadores;
        this.tableroEquipo1 = tableroEquipo1;
        this.tableroEquipo2 = tableroEquipo2;
    }

    /**
     * Retorna el mazo.
     *
     * @return Mazo.
     */
    public Mazo getMazo() {
        return mazo;
    }

    /**
     * Retorna el pozo.
     *
     * @return Pozo.
     */
    public Pozo getPozo() {
        return pozo;
    }

    /**
     * Retorna lista de jugadores.
     *
     * @return Lista de jugadores.
     */
    public ArrayList<IJugador> getJugadores() {
        return jugadores;
    }

    /**
     * Retorna el tablero '1'.
     *
     * @return Tablero jugador.
     */
    public Tablero getTableroEquipo1() {
        return tableroEquipo1;
    }

    /**
     * Retorna el tablero '2'.
     *
     * @return Tablero oponente.
     */
    public Tablero getTableroEquipo2() {
        return tableroEquipo2;
    }

}
