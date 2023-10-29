package ar.edu.unlu.poo.burako.juego;

public class Jugador {

    private String nombre;

    private Integer puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.reset();
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
}
