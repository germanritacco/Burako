package ar.edu.unlu.poo.burako.juego;

import java.util.ArrayList;

public class PilaDeMuerto extends Pila {

    public PilaDeMuerto() {
        pila = new ArrayList<>(11);
    }

    public ArrayList<Ficha> getPilaDeMuerto(){
        return pila;
    }
}
