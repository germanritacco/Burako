package ar.edu.unlu.poo.burako.juego;

import java.util.ArrayList;

public class Pila {

    protected ArrayList<Ficha> pila;

    public Pila() {
        pila = new ArrayList<>(10);
    }

    public void agregarFicha(Ficha ficha) {
        pila.add(ficha);
    }

}
