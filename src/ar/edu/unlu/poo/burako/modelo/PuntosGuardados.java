package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;

public class PuntosGuardados implements Serializable {

    private Integer puntaje;
    private String nombre;

    /**
     * Constructor de clase.
     * <li>Asigna el puntaje</li>
     * <li>Asigna el nombre del jugador</li>
     */
    public PuntosGuardados(Integer puntaje, String nombre) {
        this.puntaje = puntaje;
        this.nombre = nombre;
    }

    /**
     * Retorna el puntaje.
     *
     * @return Puntaje.
     */
    public Integer getPuntaje() {
        return puntaje;
    }

    /**
     * Retorna el nombre.
     *
     * @return Nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Convierte a texto el jugador y su puntaje.
     *
     * @return Jugador y puntaje.
     */
    @Override
    public String toString() {
        return "• " + nombre + ": " + puntaje + " puntos";
    }

}

