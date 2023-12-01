package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;

public class Pila {

    protected ArrayList<Ficha> pila;

    /**
     * Constructor de clase.
     * <li>Instancia de pila.</li>
     */
    public Pila() {
        pila = new ArrayList<>();
    }

    /**
     * Agrega una ficha a la pila.
     *
     * @param ficha Ficha.
     */
    public void agregarFicha(Ficha ficha) {
        pila.add(ficha);
    }

    /**
     * Remueve y retorna una ficha de la pila.
     *
     * @return Ficha.
     */
    public Ficha sacarFicha() {
        if (!pila.isEmpty()) {
            return pila.remove(pila.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * Verifica si la pila es vacía.
     *
     * @return <li>TRUE: Si la pila esta vacío.</li><li>FALSE: Si la pila contiene fichas.</li>
     */
    public boolean isEmpty() {
        return pila.isEmpty();
    }

}
