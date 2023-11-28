package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;
import java.util.Collection;

public class Pozo {

    private ArrayList<Ficha> pozo;

    public Pozo() {
        pozo = new ArrayList<>();
    }

    public void agregarAlPozo(Ficha ficha) {
        pozo.add(ficha);
    }

    public ArrayList<Ficha> recogerPozo() {
        ArrayList<Ficha> pozoCompleto = new ArrayList<>(pozo);
        pozo.clear();
        return pozoCompleto;
    }

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

    public boolean isEmpty() {
        return pozo.isEmpty();
    }
}
