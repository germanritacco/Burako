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

    // TODO implementar tirarDado en el modelo, recibir el jugador por parametro. ordenar el ArrayList de jugadores segun el puntaje del dado. Cambiar ID jugador segun el orden

}
