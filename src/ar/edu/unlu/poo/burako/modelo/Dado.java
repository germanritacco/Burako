package ar.edu.unlu.poo.burako.modelo;

import static java.lang.Math.random;

public class Dado {

    private Integer cara;

    public Integer getCara() {
        return cara;
    }

    public void tirarDado() {
        this.cara = (int) (random() * 6) + 1; // Random entre 1 y 6
    }

}
