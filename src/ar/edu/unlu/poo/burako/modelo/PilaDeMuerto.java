package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;

public class PilaDeMuerto extends Pila {

    /**
     * Constructor de clase.
     * <li>Instancia de pila de muerto.</li>
     */
    public PilaDeMuerto() {
        pila = new ArrayList<>(11);
    }

    /**
     * Retorna la lista de fichas que posee el muerto.
     *
     * @return Lista de fichas.
     */
    public ArrayList<Ficha> getPilaDeMuerto() {
        return pila;
    }

}
