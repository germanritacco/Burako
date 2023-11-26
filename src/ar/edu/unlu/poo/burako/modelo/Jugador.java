package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Jugador implements Serializable {

    private final String nombre;

    private Integer puntos;

    private ArrayList<Ficha> atril;

    private boolean turno;

    public boolean isTurno() {
        return turno;
    }

    public void setTurno() {
        this.turno = true;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.reset();
        this.atril = new ArrayList<>();
        this.turno = false;
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

    public void addFichaAtril(Ficha ficha){
        this.atril.add(ficha);
    }

    public Ficha removeFichaAtril(int posicion){
        return this.atril.remove(posicion);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Jugador jugador = (Jugador) obj;
        return Objects.equals(nombre, jugador.nombre);
    }

}
