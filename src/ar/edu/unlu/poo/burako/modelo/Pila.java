package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Pila implements Serializable {

    protected ArrayList<Ficha> pila;

    /**
     * Agrega una ficha a la pila.
     *
     * @param ficha Ficha.
     */
    public void agregarFicha(Ficha ficha) {
        pila.add(ficha);
    }

    /**
     * Verifica si la pila es vacía.
     *
     * @return <li>TRUE: Si la pila esta vacío.</li><li>FALSE: Si la pila contiene fichas.</li>
     */
    public boolean isEmpty() {
        return pila.isEmpty();
    }

    /**
     * Retorna el tamaño del mazo.
     *
     * @return N° de fichas del mazo.
     */
    public int size() {
        return pila.size();
    }

}
