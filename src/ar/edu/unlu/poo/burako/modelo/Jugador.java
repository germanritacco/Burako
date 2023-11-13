package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;

public class Jugador {

    private final String nombre;

    private Integer puntos;

    private ArrayList<Ficha> atril;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.reset();
        this.atril = new ArrayList<>();
    }

    public void reset() {
        puntos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public ArrayList<Ficha> getAtril() {
        return atril;
    }

    public void setAtril(ArrayList<Ficha> atril) {
        this.atril = atril;
    }

}
