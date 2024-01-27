package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;

public class PilaEstandar extends Pila {

    /**
     * Constructor de clase.
     * <li>Instancia de pila.</li>
     */
    public PilaEstandar() {
        pila = new ArrayList<>();
    }

    /**
     * Remueve y retorna una ficha de la pila.
     *
     * @return Ficha.
     */
    public Ficha sacarFicha() {
        if (!pila.isEmpty()) {
            return pila.removeLast();
        } else {
            return null;
        }
    }

}
