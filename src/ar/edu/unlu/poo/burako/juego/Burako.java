package ar.edu.unlu.poo.burako.juego;

import java.util.ArrayList;
import java.util.List;

public class Burako {

    private List<Jugador> jugadores;

    private void agregarJugador() {
        Jugador jugador1 = new Jugador("Test1");
        jugadores.add(jugador1);
        Jugador jugador2 = new Jugador("Test2");
        jugadores.add(jugador2);
        Mazo mazo = new Mazo(jugador1, jugador2);
    }

    public Burako() {
        jugadores = new ArrayList<Jugador>();
    }

}
