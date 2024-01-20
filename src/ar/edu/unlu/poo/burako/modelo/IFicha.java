package ar.edu.unlu.poo.burako.modelo;

public interface IFicha {
    /**
     * Obtiene el color de la ficha.
     *
     * @return Color de la ficha.
     */
    ColorFicha getColor();

    /**
     * Obtiene el número de la ficha.
     *
     * @return Número de la ficha.
     */
    Integer getNumeroFicha();

    /**
     * Obtiene el valor (puntaje) de la ficha.
     *
     * @return Valor (puntaje) de la ficha.
     */
    Integer getValorFicha();

    /**
     * Convierte a texto la clase Ficha.
     *
     * @return Texto con el número y color de ficha.
     */
    @Override
    public String toString();

}
