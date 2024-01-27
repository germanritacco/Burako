package ar.edu.unlu.poo.burako.modelo;

import java.util.ArrayList;
import java.util.Comparator;

public class Tablero implements ITablero {

    private final ArrayList<ArrayList<Ficha>> jugadaEnMesa;
    private final ArrayList<IJugador> jugadores;

    private int puntosEquipos;

    /**
     * Constructor de clase.
     * <li>Instancia de juegos en mesa.</li>
     * <li>Instancia de lista de jugador.</li>
     */
    public Tablero() {
        this.jugadaEnMesa = new ArrayList<>();
        this.jugadores = new ArrayList<>(2);
    }

    /**
     * Retorna una lista de listas de Fichas, que posee los juegos en pesa.
     *
     * @return Lista de listas de Fichas.
     */
    @Override
    public ArrayList<ArrayList<Ficha>> getJugadaEnMesa() {
        return jugadaEnMesa;
    }

    /**
     * Verifica si hay juegos en mesa.
     *
     * @return <li>TRUE: Si en mesa no hay juegos.</li><li>FALSE: Si hay juegos en mesa.</li>
     */
    @Override
    public boolean isVacio() {
        return this.jugadaEnMesa.isEmpty();
    }

    /**
     * Retorna los jugadores que puede usar la instancia de Tablero.
     *
     * @return Lista de Jugador.
     */
    @Override
    public ArrayList<IJugador> getJugadores() {
        return jugadores;
    }

    /**
     * Asigna un jugador a la instancia de tablero.
     *
     * @param jugador Instancia de jugador.
     */
    @Override
    public void agregarJugadores(IJugador jugador) {
        jugadores.add(jugador);
    }

    /**
     * Escalera: Por lo menos tres fichas del mismo color, en orden sucesivo.
     * <li>Ejemplo: 3-4-5 rojo.</li>
     *
     * @param juego Lista de Fichas a verificar la jugada
     * @return <li>TRUE: Si el juego forma una escalera.</li><li>FALSE: Si el juego no forma una escalera.</li>
     */
    private boolean esEscalera(ArrayList<Ficha> juego) {
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
            if (!ficha.getColor().equals(color) && !(ficha instanceof FichaComodin)) { // con comodin
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica la cantidad de comodines y Fichas N°2 que posee la lista de fichas.
     *
     * @param juego Lista de Fichas.
     * @return Cantidad de comodines y fichas N°2.
     */
    private int cantidadComodines(ArrayList<Ficha> juego) {
        int contador = 0;
        for (Ficha ficha : juego) {
            if (ficha instanceof FichaComodin || ficha.getNumeroFicha().equals(2)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Pierna: Tres fichas del mismo número, no importa el color.
     * * <li>Ejemplo: 5 rojo, 5 azul, 5 azul.</li>
     *
     * @param juego Lista de Fichas a verificar la jugada
     * @return <li>TRUE: Si el juego forma una pierna.</li><li>FALSE: Si el juego no forma una pierna.</li>
     */
    private boolean esPierna(ArrayList<Ficha> juego) {
        ArrayList<Ficha> copiaJuego = new ArrayList<>(juego);
        int contador = 0;
        // Cuenta y saca todos los comodines y 2 en el juego de fichas
        for (Ficha ficha : juego) {
            if (ficha.getNumeroFicha().equals(2) || ficha instanceof FichaComodin) {
                contador++;
                copiaJuego.remove(ficha);
            }
        }
        //verifica que el resto de fichas sean todas de igual numero
        int i = 0;
        int numeroFicha = copiaJuego.get(i).getNumeroFicha();
        contador++;
        for (i = i + 1; i <= copiaJuego.size() - 1; i++) {
            if (copiaJuego.get(i).getNumeroFicha().equals(numeroFicha)) {
                contador++;
            } else {
                return false;
            }
        }
        return contador >= 3;
    }

    /**
     * Verifica si un juego forma una canasta pura.
     * <li>Una canasta pura es una pierna o una escalera de como mínimo 7 fichas sin ningún comodín.</li>
     *
     * @param juego Lista de Fichas a verificar la canasta.
     * @return <li>TRUE: Si el juego forma una canasta pura.</li><li>FALSE: Si el juego no forma una canasta pura.</li>
     */
    private boolean isCanastaPura(ArrayList<Ficha> juego) {
        return (esPierna(juego) || esEscalera(juego)) && juego.size() >= 7 && !contieneComodin(juego);
    }

    /**
     * Verifica si un juego forma una canasta impura.
     * <li>Una canasta impura es una pierna o una escalera de como mínimo 7 fichas que puede tener comodines.</li>
     *
     * @param juego Lista de Fichas a verificar la canasta.
     * @return <li>TRUE: Si el juego forma una canasta impura.</li><li>FALSE: Si el juego no forma una canasta impura.</li>
     */
    private boolean isCanastaImpura(ArrayList<Ficha> juego) {
        return (esPierna(juego) || esEscalera(juego)) && juego.size() >= 7 && contieneComodin(juego);
    }

    /**
     * Verifica si un juego contiene al menos un comodín.
     *
     * @param juego Lista de Fichas a verificar.
     * @return <li>TRUE: Si el juego contiene Comodín.</li><li>FALSE: Si el juego no contiene Comodín.</li>
     */
    private boolean contieneComodin(ArrayList<Ficha> juego) {
        for (Ficha ficha : juego) {
            if (ficha instanceof FichaComodin) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna la sumatoria de puntos de la lista de fichas.
     *
     * @param juego Lista de fichas.
     * @return Sumaria de puntos de la lista de fichas.
     */
    private int puntos(ArrayList<Ficha> juego) {
        int suma = 0;
        for (Ficha ficha : juego) {
            suma += ficha.getValorFicha();
        }
        return suma;
    }

    /**
     * Verifica si la lista de fichas posee una escalera o pierna.
     *
     * @param jugada Lista de fichas.
     * @return <li>TRUE: Si el juego es escalera o pierna.</li><li>FALSE: Si el juego no es escalera o pierna.</li>
     */
    @Override
    public boolean verificarJugadaNueva(ArrayList<Ficha> jugada) {
        boolean estado = false;
        if (jugada.size() >= 3) {
            if (esEscalera(jugada) || esPierna(jugada)) {
                jugada.sort(Comparator.comparing(Ficha::getNumeroFicha));
                this.jugadaEnMesa.add(jugada);
                estado = true;
            }
        }
        return estado;
    }

    /**
     * Verifica y agrega fichas a un juego existente en mesa.
     *
     * @param jugada   Lista de fichas.
     * @param posicion Posición de la lista de fichas a donde se desea agregar nuevas fichas.
     * @return <li>TRUE: Si el juego es escalera o pierna.</li><li>FALSE: Si el juego no es escalera o pierna.</li>
     */
    @Override
    public boolean verificarJugadaExistente(ArrayList<Ficha> jugada, int posicion) {
        boolean estado = false;
        if (jugada.size() >= 3) {
            if (esEscalera(jugada) || esPierna(jugada)) {
                this.jugadaEnMesa.add(posicion - 1, jugada);
                estado = true;
            }
        }
        return estado;
    }

    /**
     * Retorna la cantidad de juegos que hay en mesa.
     *
     * @return Cantidad de juegos en mesa.
     */
    @Override
    public int sizeJugadaEnMesa() {
        return this.jugadaEnMesa.size();
    }

    /**
     * * Retorna una lista que posee en cada posición, la lista de fichas que forman una jugada en mesa.
     *
     * @return Lista de listas de fichas que forma las jugadas.
     */
    @Override
    public ArrayList<ArrayList<IFicha>> mostrarJuegosEnMesa() {
        ArrayList<ArrayList<IFicha>> juegosEnMesa = new ArrayList<>();
        for (ArrayList<Ficha> juego : jugadaEnMesa) {
            ArrayList<IFicha> fichas = new ArrayList<>();
            fichas = new ArrayList<>(juego);
            juegosEnMesa.add(fichas);
        }
        return juegosEnMesa;
    }

    public boolean isCanasta() {
        for (ArrayList<Ficha> juego : jugadaEnMesa) {
            if (isCanastaPura(juego) || isCanastaImpura(juego)) {
                return true;
            }
        }
        return false;
    }

    public void puntosEnMesa() {
        boolean isCanasta = false;
        int suma = 0;
        for (ArrayList<Ficha> juego : jugadaEnMesa) {
            for (Ficha ficha : juego) {
                suma += ficha.getValorFicha();
            }
            if (isCanastaPura(juego)) {
                suma += 100;
                isCanasta = true;
            }
            if (isCanastaImpura(juego)) {
                suma += 200;
                isCanasta = true;
            }
        }
        if (isCanasta) {
            this.puntosEquipos = suma;
        } else {
            this.puntosEquipos -= suma;
        }
    }

    public int getPuntosEquipos() {
        return puntosEquipos;
    }

    public void restarPuntos(int puntos) {
        this.puntosEquipos -= puntos;
    }

    public void sumarPuntos(int puntos) {
        this.puntosEquipos += puntos;
    }

    public String mostrarPuntajeJugadores() {
        String jugadoresTablero = " • ";
        for (IJugador jugador : jugadores) {
            jugadoresTablero += jugador.getNombre() + ", ";

        }
        jugadoresTablero = jugadoresTablero.substring(0, jugadoresTablero.length() - 2);
        jugadoresTablero += ": " + puntosEquipos;
        return jugadoresTablero;
    }
}