package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;
import java.util.Collection;

public class Pozo {

    private ArrayList<Ficha> pozo;

    /**
     * Constructor de clase.
     * <li>Instancia de pozo.</li>
     */
    public Pozo() {
        pozo = new ArrayList<>();
    }

    /**
     * Agrega una ficha al pozo.
     *
     * @param ficha Ficha
     */
    public void agregarAlPozo(Ficha ficha) {
        pozo.add(ficha);
    }

    /**
     * Retorna todas las fichas que posee el pozo.
     *
     * @return Lista de fichas.
     */
    public ArrayList<Ficha> recogerPozo() {
        ArrayList<Ficha> pozoCompleto = new ArrayList<>(pozo);
        pozo.clear();
        return pozoCompleto;
    }

    /**
     * Retorna el listado de fichas del pozo.
     *
     * @return ArrayList de String, cada posición contiene el número y color de ficha.
     */
    public ArrayList<String> mostrarPozo() {
        ArrayList<String> fichas = new ArrayList<>();
        for (Ficha ficha : pozo) {
            if (ficha instanceof Comodin) {
                fichas.add("COMODIN");
            } else {
                fichas.add(ficha.toString());
            }
        }
        return fichas;
    }

    /**
     * Verifica si el pozo es vacío.
     *
     * @return <li>TRUE: Si la pila esta vacío.</li><li>FALSE: Si la pila contiene fichas.</li>
     */
    public boolean isEmpty() {
        return pozo.isEmpty();
    }
}
