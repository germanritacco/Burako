package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;

public class Ficha implements Serializable {

    private ColorFicha color;

    protected Integer numeroFicha;

    protected Integer valorFicha;

    /**
     * Obtiene el color de la ficha.
     *
     * @return Color de la ficha.
     */
    public ColorFicha getColor() {
        return color;
    }

    /**
     * Obtiene el número de la ficha.
     *
     * @return Número de la ficha.
     */
    public Integer getNumeroFicha() {
        return numeroFicha;
    }

    /**
     * Obtiene el valor (puntaje) de la ficha.
     *
     * @return Valor (puntaje) de la ficha.
     */
    public Integer getValorFicha() {
        return valorFicha;
    }

    /**
     * Constructor de clase.
     * <li>Asigna el color.</li>
     * <li>Asigna el numero.</li>
     * <li>Asigna el valor.</li>
     *
     * @param color       Color de la ficha.
     * @param numeroFicha Número de la ficha.
     */
    public Ficha(ColorFicha color, Integer numeroFicha) {
        this.color = color;
        valorFicha = Valor(numeroFicha);
        this.numeroFicha = numeroFicha;
    }

    public Ficha() {
    }

    /**
     * Asigna el valor(puntaje) a la ficha.
     *
     * @param ficha Numero de ficha.
     * @return Valor (puntaje) de la ficha.
     */
    public Integer Valor(Integer ficha) {
        int ValorFinal = 0;
        switch (ficha) {
            case 1 -> ValorFinal = 15;
            case 2 -> ValorFinal = 20;
            case 3, 4, 5, 6, 7 -> ValorFinal = 5;
            case 8, 9, 10, 11, 12, 13 -> ValorFinal = 10;
        }
        return ValorFinal;
    }

    /**
     * Convierte a texto la clase Ficha.
     *
     * @return Texto con el número y color de ficha.
     */
    @Override
    public String toString() {
        return numeroFicha.toString() + " " + color;
    }

}
