package ar.edu.unlu.poo.burako.juego;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tablero {

    // TODO Implementar jugadas

    protected ArrayList<ArrayList<Ficha>> jugadaEnMesa = new ArrayList<>();

    private Jugador jugador;

    public ArrayList<ArrayList<Ficha>> getJugadaEnMesa() {
        return jugadaEnMesa;
    }

    public void setJugadaEnMesa(ArrayList<Ficha> jugadaEnMesa) {
        this.jugadaEnMesa.add(jugadaEnMesa);
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean esEscalera(ArrayList<Ficha> juego) {
        // realiza una copia de "juego" para no alterar el orden
        ArrayList<Ficha> copiaJuego = new ArrayList<>(juego);
        copiaJuego.sort(Comparator.comparing(Ficha::getNumeroFicha)); // Ordena de forma ascendente
        // Verifica si hay al menos tres cartas y están en orden sucesivo
        for (int i = 0; i < copiaJuego.size() - 2; i++) {
            int num1 = copiaJuego.get(i).getNumeroFicha();
            int num2 = copiaJuego.get(i + 1).getNumeroFicha();
            if (num1 + 1 != num2) {
                return false;
            }
        }
        // Verifica si todas las cartas son del mismo color
        ColorFicha color = copiaJuego.get(0).getColor();
        for (Ficha ficha : copiaJuego) {
            if (!ficha.getColor().equals(color)) {
                return false;
            }
        }
        return true;
    }
}
