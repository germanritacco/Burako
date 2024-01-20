package ar.edu.unlu.poo.burako.modelo;

public class FichaComodin extends Ficha {

    public FichaComodin() {
        valorFicha = 50;
        numeroFicha = 0;
        color = null;
    }

    /**
     * Convierte a texto la clase Ficha.
     *
     * @return Texto con el número y color de ficha.
     */
    @Override
    public String toString() {
        return "COMODÍN";
    }
}
