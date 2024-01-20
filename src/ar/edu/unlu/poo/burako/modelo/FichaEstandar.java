package ar.edu.unlu.poo.burako.modelo;

public class FichaEstandar extends Ficha {

    /**
     * Constructor de clase.
     * <li>Asigna el color.</li>
     * <li>Asigna el numero.</li>
     * <li>Asigna el valor.</li>
     *
     * @param color       Color de la ficha.
     * @param numeroFicha Número de la ficha.
     */
    public FichaEstandar(ColorFicha color, Integer numeroFicha) {
        this.color = color;
        valorFicha = valor(numeroFicha);
        this.numeroFicha = numeroFicha;
    }

    /**
     * Asigna el valor(puntaje) a la ficha.
     *
     * @param ficha Numero de ficha.
     * @return Valor (puntaje) de la ficha.
     */
    private Integer valor(Integer ficha) {
        int valorFinal = 0;
        switch (ficha) {
            case 1 -> valorFinal = 15;
            case 2 -> valorFinal = 20;
            case 3, 4, 5, 6, 7 -> valorFinal = 5;
            case 8, 9, 10, 11, 12, 13 -> valorFinal = 10;
        }
        return valorFinal;
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
