package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;
import java.util.Collection;

public class Pozo {

    protected ArrayList<Ficha> pozo;

    public Pozo() {
        pozo = new ArrayList<>();
    }

    public void agregarAlPozo(Ficha ficha) {
        pozo.add(ficha);
    }

    public Collection<Ficha> recogerPozo() {
        Collection<Ficha> pozoCompleto = new ArrayList<>(pozo);
        pozo.clear();
        return pozoCompleto;
    }

}
