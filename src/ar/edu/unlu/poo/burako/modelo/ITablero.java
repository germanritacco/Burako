package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;

public interface ITablero {
    ArrayList<ArrayList<Ficha>> getJugadaEnMesa();

    void setJugadaEnMesa(ArrayList<Ficha> jugadaEnMesa);

    ArrayList<Jugador> getJugadores();

    void agregarJugadores(Jugador jugador);

    boolean esEscalera(ArrayList<Ficha> juego);

    boolean esPierna(ArrayList<Ficha> juego);

    boolean esCanastaPura(ArrayList<Ficha> juego);

    boolean esCanastaImpura(ArrayList<Ficha> juego);

    boolean isVacio();

    int sizeJugadaEnMesa();

    boolean verificarJugadaNueva(ArrayList<Ficha> jugada);

    boolean verificarJugadaExistente(ArrayList<Ficha> jugada, int posicion);

}
