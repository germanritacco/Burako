package ar.edu.unlu.poo.burako.juego;

import java.util.ArrayList;

import static java.lang.Math.random;

public class Mazo {

    private ArrayList<Pila> mazo; // pilas de 10 fichas c/u

    private ArrayList<PilaDeMuerto> muertos; // 2 pilas de 11 fichas c/u

    /**
     * Constructor para 2 jugadores
     *
     * @param jugador1 Jugador N°1
     * @param jugador2 Jugador N°2
     */
    public Mazo(Jugador jugador1, Jugador jugador2) {
        ArrayList<Ficha> fichasSinRepartir = crearFichas();
        this.mazo = new ArrayList<>(5);
        this.muertos = new ArrayList<>(2);
        jugador1.setAtril(repartirAtril(fichasSinRepartir));
        jugador2.setAtril(repartirAtril(fichasSinRepartir));
        repartirMuerto(fichasSinRepartir);
        repartirMazo(fichasSinRepartir);
    }

    /**
     * Constructor para 4 jugadores
     *
     * @param jugador1 Jugador N°1 (pareja de Jugador N°3)
     * @param jugador2 Jugador N°2 (pareja de Jugador N°4)
     * @param jugador3 Jugador N°3 (pareja de Jugador N°1)
     * @param jugador4 Jugador N°4 (pareja de Jugador N°2)
     */
    public Mazo(Jugador jugador1, Jugador jugador2, Jugador jugador3, Jugador jugador4) {
        ArrayList<Ficha> fichasSinRepartir = crearFichas();
        this.mazo = new ArrayList<>(5);
        this.muertos = new ArrayList<>(2);
        jugador1.setAtril(repartirAtril(fichasSinRepartir));
        jugador2.setAtril(repartirAtril(fichasSinRepartir));
        jugador3.setAtril(repartirAtril(fichasSinRepartir));
        jugador4.setAtril(repartirAtril(fichasSinRepartir));
        repartirMuerto(fichasSinRepartir);
        repartirMazo(fichasSinRepartir);
    }

    /**
     * Genera las 106 fichas necesarias para jugar
     *
     * @return ArrayList con el total de fichas
     */
    public ArrayList<Ficha> crearFichas() {
        ArrayList<Ficha> fichasSinRepartir = new ArrayList<>();
        for (ColorFicha color : ColorFicha.values()) {
            for (int i = 1; i <= 13; i++) {
                for (int j = 0; j < 2; j++) {
                    fichasSinRepartir.add(new Ficha(color, i));
                }
            }
        }
        fichasSinRepartir.add(new Comodin());
        fichasSinRepartir.add(new Comodin());
        return fichasSinRepartir;
    }

    /**
     * Reparte de forma aleatoria las 2 pilas de Muertos
     *
     * @param fichasSinRepartir ArrayList de fichas restantes a repartir
     */
    public void repartirMuerto(ArrayList<Ficha> fichasSinRepartir) {
        for (int i = 0; i < 2; i++) {
            PilaDeMuerto pilaDeMuertoAux = new PilaDeMuerto();
            for (int j = 1; j <= 11; j++) {
                Ficha fichaAux = fichasSinRepartir.remove((int) (random() * fichasSinRepartir.size()));
                pilaDeMuertoAux.agregarFicha(fichaAux);
            }
            this.muertos.add(pilaDeMuertoAux);
        }
    }

    /**
     * Reparte de forma aleatoria pilas de 10 fichas hasta que no haya mas fichas a repartir
     *
     * @param fichasSinRepartir ArrayList de fichas restantes a repartir
     */
    public void repartirMazo(ArrayList<Ficha> fichasSinRepartir) {
        while (!fichasSinRepartir.isEmpty()) {
            Pila pilaAux = new Pila();
            for (int i = 1; i <= 10 && !fichasSinRepartir.isEmpty(); i++) {
                Ficha fichaAux = fichasSinRepartir.remove((int) (Math.random() * fichasSinRepartir.size()));
                pilaAux.agregarFicha(fichaAux);
            }
            this.mazo.add(pilaAux);
        }
    }

    /**
     * Reparte 11 fichas para un jugador
     *
     * @param fichasSinRepartir ArrayList de fichas restantes a repartir
     * @return ArrayList con 11 fichas
     */
    public ArrayList<Ficha> repartirAtril(ArrayList<Ficha> fichasSinRepartir) {
        ArrayList<Ficha> fichasAtrilAux = new ArrayList<>();
        for (int j = 1; j <= 11; j++) {
            Ficha fichaAux = fichasSinRepartir.remove((int) (random() * fichasSinRepartir.size()));
            fichasAtrilAux.add(fichaAux);
        }
        return fichasAtrilAux;
    }

}
