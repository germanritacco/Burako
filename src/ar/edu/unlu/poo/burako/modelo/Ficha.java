package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;

public abstract class Ficha implements IFicha, Serializable {

    protected ColorFicha color;
    protected Integer numeroFicha;
    protected Integer valorFicha;
    protected Integer numeroJugada;

    /**
     * Obtiene el color de la ficha.
     *
     * @return Color de la ficha.
     */
    @Override
    public ColorFicha getColor() {
        return color;
    }

    /**
     * Obtiene el número de la ficha.
     *
     * @return Número de la ficha.
     */
    @Override
    public Integer getNumeroFicha() {
        return numeroFicha;
    }

    /**
     * Obtiene el valor (puntaje) de la ficha.
     *
     * @return Valor (puntaje) de la ficha.
     */
    @Override
    public Integer getValorFicha() {
        return valorFicha;
    }

    /**
     * Obtiene el número de jugada en mesa al que pertenece la ficha.
     *
     * @return Número de jugada en mesa.
     */
    @Override
    public Integer getNumeroJugada() {
        return numeroJugada;
    }

    /**
     * Asigna el número de jugada en mesa al que pertenece la ficha.
     *
     * @param numeroJugada Número de jugada en mesa.
     */
    public void setNumeroJugada(Integer numeroJugada) {
        this.numeroJugada = numeroJugada;
    }

}
