package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;
import java.util.Comparator;

public class Tablero implements ITablero {

    // TODO Implementar jugadas


    protected ArrayList<ArrayList<Ficha>> jugadaEnMesa = new ArrayList<>();

    private ArrayList<Jugador> jugadores;

    public Tablero() {
        this.jugadaEnMesa = new ArrayList<>();
        this.jugadores = new ArrayList<>(2);
    }


    @Override
    public ArrayList<ArrayList<Ficha>> getJugadaEnMesa() {
        return jugadaEnMesa;
    }


    @Override
    public void setJugadaEnMesa(ArrayList<Ficha> jugadaEnMesa) {
        this.jugadaEnMesa.add(jugadaEnMesa);
    }

    public boolean isVacio() {
        return this.jugadaEnMesa.isEmpty();
    }


    @Override
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    @Override
    public void agregarJugadores(Jugador jugador) {
        jugadores.add(jugador);
    }

    /**
     * Escalera: Por lo menos tres fichas del mismo color, en orden sucesivo. Ejemplo: 3-4-5 rojo.
     *
     * @param juego ArrayList de Fichas a verificar la jugada
     * @return Retorna Verdadero si el juego forma una escalera, caso contrario falso.
     */

    @Override
    public boolean esEscalera(ArrayList<Ficha> juego) {
        // realiza una copia de "juego" para no alterar el orden
        ArrayList<Ficha> copiaJuego = new ArrayList<>(juego);
        copiaJuego.sort(Comparator.comparing(Ficha::getNumeroFicha)); // Ordena de forma ascendente por NumeroFicha
        int comodinesUsados = 0;
        // Verifica si hay al menos tres cartas y están en orden sucesivo
        for (int i = 0; i < copiaJuego.size() - 2; i++) {
            int numeroFichaActual = copiaJuego.get(i).getNumeroFicha();
            int numeroFichaSiguiente = copiaJuego.get(i + 1).getNumeroFicha();
            int diferencia = numeroFichaSiguiente - numeroFichaActual; // Si la diferencia es mayor a uno, es necesario utilizar un comodin
            // Verifica que no haya 2 numero consecutivos iguales diferentes a 2
            if (diferencia == 0 && numeroFichaActual != 2) {
                return false;
            }
            if (diferencia > 1) {
                comodinesUsados = comodinesUsados + diferencia - 1; // Si es necesario utilizar un comodin, verifica si hay comodines para usar
                if (comodinesUsados > cantidadComodines(copiaJuego)) {
                    return false;
                }
            }
        }
        // Verifica si todas las cartas son del mismo color y si hay comodines
        ColorFicha color = copiaJuego.get(0).getColor();
        for (Ficha ficha : copiaJuego) {
            if (!ficha.getColor().equals(color) && !(ficha instanceof Comodin)) { // con comodin
                return false;
            }
        }
        return true;
    }

    private int cantidadComodines(ArrayList<Ficha> juego) {
        int contador = 0;
        for (Ficha ficha : juego) {
            if (ficha instanceof Comodin || ficha.getNumeroFicha().equals(2)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Pierna: Tres fichas del mismo número, no importa el color. Ejemplo: 5 rojo, 5 azul, 5 azul.
     *
     * @param juego ArrayList de Fichas a verificar la jugada
     * @return Retorna Verdadero si el juego forma una escalera, caso contrario falso.
     */

    @Override
    public boolean esPierna(ArrayList<Ficha> juego) {
        // Contamos cuántas cartas tienen el mismo número
        int contador = 1;
        int i = 0;
        while (i < juego.size() && juego.get(i) instanceof Comodin) {
            contador++;
            i++;
        }
        if (i < juego.size()) {
            int numeroFicha = juego.get(i).getNumeroFicha();
            for (i = i + 1; i < juego.size() - 1; i++) {
                if (juego.get(i).getNumeroFicha().equals(numeroFicha) || juego.get(i) instanceof Comodin || juego.get(i).getNumeroFicha().equals(2)) { // con comodin
                    contador++;
                } else {
                    return false;
                }
            }
        }
        return contador >= 3;
    }


    /**
     * Verifica si un juego forma una canasta pura.
     * Una canasta pura es una pierna o una escalera de como mínimo 7 fichas sin ningún comodín.
     *
     * @param juego ArrayList de Fichas a verificar la canasta
     * @return Retorna Verdadero si el juego forma una canasta pura, caso contrario falso.
     */

    @Override
    public boolean esCanastaPura(ArrayList<Ficha> juego) {
        return (esPierna(juego) || esEscalera(juego)) && juego.size() >= 7 && !contieneComodin(juego);
    }

    /**
     * Verifica si un juego forma una canasta impura.
     * Una canasta impura es una pierna o una escalera de como mínimo 7 fichas que puede tener comodines.
     *
     * @param juego ArrayList de Fichas a verificar la canasta
     * @return Retorna Verdadero si el juego forma una canasta impura, caso contrario falso.
     */

    @Override
    public boolean esCanastaImpura(ArrayList<Ficha> juego) {
        return (esPierna(juego) || esEscalera(juego)) && juego.size() >= 7 && contieneComodin(juego);
    }

    /**
     * Verifica si un juego contiene al menos un comodín.
     *
     * @param juego ArrayList de Fichas a verificar
     * @return Retorna Verdadero si el juego contiene al menos un comodín, caso contrario falso.
     */
    private boolean contieneComodin(ArrayList<Ficha> juego) {
        for (Ficha ficha : juego) {
            if (ficha instanceof Comodin) {
                return true;
            }
        }
        return false;
    }

    private int puntos(ArrayList<Ficha> juego) {
        int suma = 0;
        for (Ficha ficha : juego) {
            suma += ficha.getValorFicha();
        }
        return suma;
    }

    public boolean verificarJugadaNueva(ArrayList<Ficha> jugada) {
        boolean estado = false;
        if (jugada.size() >= 3) {
            if (esEscalera(jugada) || esPierna(jugada)) {
                this.jugadaEnMesa.add(jugada);
                estado = true;
            }
        }
        return estado;
    }

    public boolean verificarJugadaExistente(ArrayList<Ficha> jugada, int posicion) {
        boolean estado = false;
        if (jugada.size() >= 3) {
            if (esEscalera(jugada) || esPierna(jugada)) {
                this.jugadaEnMesa.add(posicion -1, jugada);
                estado = true;
            }
        }
        return estado;
    }


    public int sizeJugadaEnMesa() {
       return this.jugadaEnMesa.size();
    }
}