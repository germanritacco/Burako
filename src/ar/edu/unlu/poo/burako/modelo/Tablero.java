package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Tablero implements ITablero, Serializable {

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
     * Retorna una lista de listas de Fichas, que posee los juegos en mesa.
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
     * Convierte a texto el listado de jugadores asignado al tablero.
     *
     * @return Listado de jugadores.
     */
    public String toStringJugadores() {
        String jugadoresTablero = "";
        for (IJugador jugador : jugadores) {
            jugadoresTablero += jugador.getNombre() + ", ";

        }
        jugadoresTablero = jugadoresTablero.substring(0, jugadoresTablero.length() - 2);
        return jugadoresTablero;
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
     * Se puede usar un solo comodín (o ficha N°2) por escalera.
     * <li>Ejemplo: 3-4-5 rojo.</li>
     *
     * @param juego Lista de Fichas a verificar la jugada
     * @return <li>TRUE: Si el juego forma una escalera.</li><li>FALSE: Si el juego no forma una escalera.</li>
     */
    private boolean esEscalera(ArrayList<Ficha> juego) {
        // Verifica que haya al menos 3 fichas
        if (juego.size() < 3) {
            return false;
        }
        // Copia y ordena las fichas por número
        ArrayList<Ficha> copiaJuego = new ArrayList<>(juego);
        copiaJuego.sort(Comparator.comparing(Ficha::getNumeroFicha));
        int comodinesDisponibles = 0;
        int fichaComodin = 0;
        ColorFicha color = null;
        // Contar comodines disponibles y establecer el color inicial
        for (Ficha ficha : juego) {
            if (ficha instanceof FichaComodin) {
                if (fichaComodin > 0) {
                    return false;
                } else {
                    fichaComodin++;
                    comodinesDisponibles++;
                    copiaJuego.remove(ficha);
                }
            } else if (ficha.getNumeroFicha() == 2) {
                comodinesDisponibles++;
            } else if (color == null) {
                color = ficha.getColor(); // Establecer el color de la escalera
            } else if (!ficha.getColor().equals(color)) {
                return false; // Fichas de diferentes colores
            }
        }
        // Verificar continuidad numérica y uso de comodines
        for (int i = 0; i < copiaJuego.size() - 1; i++) {
            Ficha fichaActual = copiaJuego.get(i);
            Ficha fichaSiguiente = copiaJuego.get(i + 1);
            int numeroFichaActual = fichaActual.getNumeroFicha();
            int numeroFichaSiguiente = fichaSiguiente.getNumeroFicha();
            // Si la ficha actual es N°2 y se usa como comodín, ajustar número
            if (fichaActual.getNumeroFicha() == 2 && fichaActual.getColor().equals(color)) {
                numeroFichaActual = i == 0 ? numeroFichaSiguiente - 1 : copiaJuego.get(i - 1).getNumeroFicha() + 1;
            }
            // Verificar continuidad numérica
            int diferencia = numeroFichaSiguiente - numeroFichaActual;
            if (diferencia > 1) {
                // Necesita comodines para cubrir el hueco
                comodinesDisponibles -= (diferencia - 1);
                if (comodinesDisponibles < 0) {
                    return false; // No hay suficientes comodines
                }
            } else if (diferencia == 0 && numeroFichaActual != 2) {
                // Números duplicados no válidos (excepto ficha N°2)
                return false;
            }
        }
        // Verificar que la ficha N°2, si se usa como comodín, coincida en color
        for (Ficha ficha : copiaJuego) {
            if (ficha.getNumeroFicha() == 2 && !ficha.getColor().equals(color)) {
                return false; // Ficha N°2 no coincide en color
            }
        }
        return true; // Si todas las verificaciones pasan, es una escalera válida
    }

    /**
     * Verifica la cantidad de comodines que posee la lista de fichas.
     *
     * @param juego Lista de Fichas.
     * @return Cantidad de comodines.
     */
    private int cantidadComodines(ArrayList<Ficha> juego) {
        int contador = 0;
        for (Ficha ficha : juego) {
            if (ficha instanceof FichaComodin) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Verifica la cantidad Fichas N°2 que posee la lista de fichas.
     *
     * @param juego Lista de Fichas.
     * @return Cantidad de fichas N°2.
     */
    private int cantidadFichaDos(ArrayList<Ficha> juego) {
        int contador = 0;
        for (Ficha ficha : juego) {
            if (ficha.getNumeroFicha().equals(2)) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Pierna: Por lo menos tres fichas del mismo número, no importa el color.
     * * Se puede usar un solo comodín (o ficha N°2) por escalera.
     * * <li>Ejemplo: 5 rojo, 5 azul, 5 azul.</li>
     *
     * @param juego Lista de Fichas a verificar la jugada
     * @return <li>TRUE: Si el juego forma una pierna.</li><li>FALSE: Si el juego no forma una pierna.</li>
     */
    private boolean esPierna(ArrayList<Ficha> juego) {
        ArrayList<Ficha> copiaJuego = new ArrayList<>(juego);
        int contador = 0;
        int comodinesDisponibles = 0;
        int fichaN2 = 0;
        // Cuenta y saca todos los comodines y 2 en el juego de fichas
        for (Ficha ficha : juego) {
            //if (ficha.getNumeroFicha().equals(2) || ficha instanceof FichaComodin) {
            if (ficha instanceof FichaComodin) {
                if (comodinesDisponibles > 0) {
                    return false;
                } else {
                    contador++;
                    comodinesDisponibles++;
                    copiaJuego.remove(ficha);
                }
            }
            if (ficha.getNumeroFicha().equals(2)) {
                contador++;
                fichaN2++;
                copiaJuego.remove(ficha);
            }
        }
        //verifica que el resto de fichas sean todas de igual numero
        int i;
        if (!copiaJuego.isEmpty()) {
            int numeroFicha;
            if (fichaN2 >= 3) {
                i = -1;
                numeroFicha = 2;
            } else {
                i = 0;
                numeroFicha = copiaJuego.get(i).getNumeroFicha();
            }
            contador++;
            for (i = i + 1; i <= copiaJuego.size() - 1; i++) {
                if (copiaJuego.get(i).getNumeroFicha().equals(numeroFicha)) {
                    contador++;
                } else {
                    return false;
                }
            }
        } else {
            if (comodinesDisponibles >= 3) {
                return true;
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
            if (cantidadComodines(jugada) <= 1) { // No se puede tener más de 1 comodín
                if (esEscalera(jugada) || esPierna(jugada)) {
                    jugada.sort(Comparator.comparing(Ficha::getNumeroFicha));
                    Integer numeroJugada = tamanioJugadaEnMesa() + 1;
                    for (Ficha ficha : jugada) {
                        ficha.setNumeroJugada(numeroJugada);
                    }

                    this.jugadaEnMesa.add(jugada);
                    estado = true;
                }
            }
        }
        return estado;
    }


    /**
     * Retorna la cantidad de jugadas que hay en el tablero.
     *
     * @return N° de jugadas en mesa.
     */
    private Integer tamanioJugadaEnMesa() {
        return this.jugadaEnMesa.size();
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
                this.jugadaEnMesa.remove(posicion - 1);
                jugada.sort(Comparator.comparing(Ficha::getNumeroFicha));
                for (Ficha ficha : jugada) {
                    ficha.setNumeroJugada(posicion);
                }
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
     * Retorna una lista que posee en cada posición, la lista de fichas que forman una jugada en mesa.
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


    /**
     * Verifica si hay canasta
     *
     * @return <li>TRUE: Si hay al menos una canasta en el tablero.</li><li>FALSE: Si no hay canasta en el tablero.</li>
     */
    public boolean isCanasta() {
        for (ArrayList<Ficha> juego : jugadaEnMesa) {
            if (isCanastaPura(juego) || isCanastaImpura(juego)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sumatoria de los valores de fichas en mesa, incluyendo los puntos por canasta pura e impura.
     */
    public void puntosEnMesa() {
        boolean isCanasta = false;
        int suma = 0;
        for (ArrayList<Ficha> juego : jugadaEnMesa) {
            for (Ficha ficha : juego) {
                suma += ficha.getValorFicha();
            }
            if (isCanastaPura(juego)) {
                suma += 200;
                isCanasta = true;
            }
            if (isCanastaImpura(juego)) {
                suma += 100;
                isCanasta = true;
            }
        }
        /*if (isCanasta) {
            suma += 100;
            ;*/
        this.puntosEquipos = suma;
    }

    /**
     * Sumatoria de los valores de las fichas en mesa.
     */
    public void puntosEnMesaParcial() {
        int suma = 0;
        for (ArrayList<Ficha> juego : jugadaEnMesa) {
            for (Ficha ficha : juego) {
                suma += ficha.getValorFicha();
            }
            if (isCanastaPura(juego)) {
                suma += 100;
            }
            if (isCanastaImpura(juego)) {
                suma += 200;
            }
        }
        this.puntosEquipos = suma;
    }

    /**
     * Retorna los puntos del jugador/pareja
     *
     * @return Puntos del jugador/equipo
     */
    public int getPuntosEquipos() {
        return puntosEquipos;
    }

    /**
     * Resta puntos al puntaje del jugador/equipo
     *
     * @param puntos N° a restar al puntaje.
     */
    public void restarPuntos(int puntos) {
        this.puntosEquipos -= puntos;
    }

    /**
     * Suma puntos al puntaje del jugador/equipo
     *
     * @param puntos N° a sumar al puntaje.
     */
    public void sumarPuntos(int puntos) {
        this.puntosEquipos += puntos;
    }

    /**
     * Convierte a texto el nombre y puntaje del tablero.
     *
     * @return Nombre y puntaje del tablero.
     */
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