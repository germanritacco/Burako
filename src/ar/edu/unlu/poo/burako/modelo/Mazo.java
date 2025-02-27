package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Math.random;

public class Mazo implements Serializable {

    private final PilaEstandar mazo;
    private final ArrayList<PilaDeMuerto> muerto; // 2 pilas de 11 fichas c/u

    public ArrayList<Ficha> sacarMuerto() {
        return muerto.removeFirst().getPilaDeMuerto();
    }

    public ArrayList<PilaDeMuerto> getMuerto() {
        return muerto;
    }

    /**
     * Constructor de clase para 2 jugadores.
     * <li>Asigna las fichas para los jugadores.</li>
     * <li>Instancia y asigna fichas al mazo</li>
     * <li>Instancia y asigna fichas al muerto.</li>
     *
     * @param jugador1 Jugador N°1
     * @param jugador2 Jugador N°2
     */
    public Mazo(IJugador jugador1, IJugador jugador2) {
        ArrayList<Ficha> fichasSinRepartir = crearFichas();
        this.mazo = new PilaEstandar();
        this.muerto = new ArrayList<>(2);
        jugador1.addAtril(repartirAtril(fichasSinRepartir));
        jugador2.addAtril(repartirAtril(fichasSinRepartir));
        repartirMuerto(fichasSinRepartir);
        repartirMazo(fichasSinRepartir);
    }

    /**
     * Constructor de clase para 4 jugadores.
     * <li>Asigna las fichas para los jugadores.</li>
     * <li>Instancia y asigna fichas al mazo</li>
     * <li>Instancia y asigna fichas al muerto.</li>
     *
     * @param jugador1 Jugador N°1 (pareja de Jugador N°3)
     * @param jugador2 Jugador N°2 (pareja de Jugador N°4)
     * @param jugador3 Jugador N°3 (pareja de Jugador N°1)
     * @param jugador4 Jugador N°4 (pareja de Jugador N°2)
     */
    public Mazo(IJugador jugador1, IJugador jugador2, IJugador jugador3, IJugador jugador4) {
        ArrayList<Ficha> fichasSinRepartir = crearFichas();
        this.mazo = new PilaEstandar();
        this.muerto = new ArrayList<>(2);
        jugador1.addAtril(repartirAtril(fichasSinRepartir));
        jugador2.addAtril(repartirAtril(fichasSinRepartir));
        jugador3.addAtril(repartirAtril(fichasSinRepartir));
        jugador4.addAtril(repartirAtril(fichasSinRepartir));
        repartirMuerto(fichasSinRepartir);
        repartirMazo(fichasSinRepartir);
    }

    /**
     * Genera las 106 fichas necesarias para jugar.
     *
     * @return Lista de fichas.
     */
    public ArrayList<Ficha> crearFichas() {
        ArrayList<Ficha> fichasSinRepartir = new ArrayList<>();
        for (ColorFicha color : ColorFicha.values()) {
            for (int i = 1; i <= 13; i++) {
                for (int j = 0; j < 2; j++) {
                    fichasSinRepartir.add(new FichaEstandar(color, i));
                }
            }
        }
        fichasSinRepartir.add(new FichaComodin());
        fichasSinRepartir.add(new FichaComodin());
        return fichasSinRepartir;
    }

    /**
     * Reparte de forma aleatoria las 2 pilas de Muertos.
     *
     * @param fichasSinRepartir Lista de fichas restantes a repartir.
     */
    public void repartirMuerto(ArrayList<Ficha> fichasSinRepartir) {
        for (int i = 0; i < 2; i++) {
            PilaDeMuerto pilaDeMuertoAux = new PilaDeMuerto();
            for (int j = 1; j <= 11; j++) {
                Ficha fichaAux = fichasSinRepartir.remove((int) (random() * fichasSinRepartir.size()));
                pilaDeMuertoAux.agregarFicha(fichaAux);
            }
            this.muerto.add(pilaDeMuertoAux);
        }
    }

    /**
     * Reparte de forma aleatoria el resto de fichas para formar el mazo.
     *
     * @param fichasSinRepartir Lista de fichas restantes a repartir.
     */
    public void repartirMazo(ArrayList<Ficha> fichasSinRepartir) {
        while (!fichasSinRepartir.isEmpty()) {
            Ficha ficha = fichasSinRepartir.remove((int) (Math.random() * fichasSinRepartir.size()));
            mazo.agregarFicha(ficha);
        }
    }

    /**
     * Reparte 11 fichas para un jugador.
     *
     * @param fichasSinRepartir Lista de fichas restantes a repartir.
     * @return Lista con 11 fichas.
     */
    public ArrayList<Ficha> repartirAtril(ArrayList<Ficha> fichasSinRepartir) {
        ArrayList<Ficha> fichasAtrilAux = new ArrayList<>();
        //TODO BORRAR
        fichasAtrilAux.add(new FichaComodin());
        fichasAtrilAux.add(new FichaEstandar(ColorFicha.NEGRO, 2));
        fichasAtrilAux.add(new FichaEstandar(ColorFicha.NEGRO, 2));
        fichasAtrilAux.add(new FichaEstandar(ColorFicha.NEGRO, 2));
        fichasAtrilAux.add(new FichaEstandar(ColorFicha.NEGRO, 2));
        /*for (int j = 1; j <= 11; j++) {
            Ficha fichaAux = fichasSinRepartir.remove((int) (random() * fichasSinRepartir.size()));
            fichasAtrilAux.add(fichaAux);
        }*/
        return fichasAtrilAux;
    }

    /**
     * Recoge una ficha del mazo.
     *
     * @return Ficha.
     */
    public Ficha recogerFichaMazo() {
        if (!mazo.isEmpty()) {
            return mazo.sacarFicha();
        } else {
            return null;
        }
    }

    /**
     * Retorna el tamaño del mazo.
     *
     * @return N° de fichas del mazo.
     */
    public int size() {
        return mazo.size();
    }
}
