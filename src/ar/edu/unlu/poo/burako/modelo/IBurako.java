package ar.edu.unlu.poo.burako.modelo;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IBurako extends IObservableRemoto {

    /**
     * Recupera el mensaje guardado en la clase modelo.
     *
     * @return Mensaje a mostrar en las vistas.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    String getMensajeSistema() throws RemoteException;

    boolean getEstadoMensajeSistema() throws RemoteException;

    /**
     * Cierra la conexión de un jugador con el servidor remoto.
     *
     * @param controlador Controlador que está asociado a una vista.
     * @param jugadorId   N° de ID del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    void cerrar(IObservadorRemoto controlador, int jugadorId) throws RemoteException;

    /**
     * Obtiene El listado de jugadores conectados.
     *
     * @return Texto del listado de jugadores.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    String getJugadores() throws RemoteException;

    /**
     * Retorna el listado de fichas de un jugador.
     *
     * @param jugadorId N° de ID del jugador.
     * @return ArrayList de String, cada posición contiene el número y color de ficha.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    ArrayList<IFicha> getFichas(int jugadorId) throws RemoteException;

    /**
     * Agrega un nuevo jugador
     *
     * @param nombre Nombre del jugador.
     * @return Instancia de jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    IJugador setJugador(String nombre) throws RemoteException;

    /**
     * Agrega al atril del jugador las fichas de la pila de muertos.
     *
     * @param jugadorId N° de ID del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    void tomarMuerto(int jugadorId) throws RemoteException;

    /**
     * Inicia la partida:
     * <li>Reparte las fichas.</li>
     * <li>Instancia el pozo.</li>
     * <li>Asigna el turno al primer jugador.</li>
     *
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    void comenzarPartida() throws RemoteException;

    /**
     * Verifica si el pozo esta vacío.
     *
     * @return <li>TRUE: Si el pozo esta vacío.</li><li>FALSE: Si el pozo contiene fichas.</li>
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    boolean isPozoVacio() throws RemoteException;

    /**
     * Agrega al atril del jugador todas las fichas del pozo.
     *
     * @param jugadorId N° de ID del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    void recogerPozo(int jugadorId) throws RemoteException;

    /**
     * Retorna el nombre del jugador que posee el turno.
     *
     * @return Nombre del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    String nombreJugadorTurno() throws RemoteException;

    /**
     * Retorna una lista que posee en cada posición, la lista de fichas que forman una jugada en mesa
     *
     * @param jugadorId N° de ID del jugador.
     * @return Lista de listas de fichas que forma las jugadas
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    ArrayList<ArrayList<IFicha>> mostrarJuegosEnMesa(int jugadorId, boolean informar) throws RemoteException;

    /**
     * Retorna el nombre del jugador que posee el turno.
     *
     * @param jugadorId N° de ID del jugador.
     * @return Nombre del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    String mostrarTurno(int jugadorId) throws RemoteException;

    /**
     * Retorna el listado de fichas del pozo.
     *
     * @return ArrayList de String, cada posición contiene el número y color de ficha.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    ArrayList<IFicha> mostrarPozo() throws RemoteException;

    /**
     * Toma una ficha del mazo y la agrega al atril del jugador.
     *
     * @param jugadorId N° de ID del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    void recogerFichaMazo(int jugadorId) throws RemoteException;

    /**
     * Retorna la cantidad de fichas que posee el jugador en el atril.
     *
     * @param jugadorId N° de ID del jugador.
     * @return Cantidad de fichas.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    Integer cantidadFichasAtril(int jugadorId, int desplazamiento) throws RemoteException;

    /**
     * Informa a las vistas que se ha desconectado un jugador.
     *
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    void abandonarPartida() throws RemoteException;

    /**
     * Verifica si el jugador posee juegos en mesa.
     *
     * @param jugadorId N° de ID del jugador.
     * @return <li>TRUE: Si la mesa posee juegos.</li><li>FALSE: Si la mesa esta vacía.</li>
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    Boolean hayJuegosMesa(int jugadorId) throws RemoteException;

    /**
     * Verifica y agrega un nuevo juego a mesa.
     *
     * @param jugadorId N° de ID del jugador.
     * @param seleccion Arreglo de las posiciones de fichas que forman un posible juego.
     * @return <li>TRUE: Si las fichas forman un juego y se agregaron a la mesa.</li><li>FALSE: Si las fichas no forman un juego.</li>
     * @throws RemoteException       Se lanza si ocurre un error de red.
     * @throws NumberFormatException Se lanza si hay un error al convertir una cadena a un número.
     */
    boolean agregarNuevaJugada(int jugadorId, String[] seleccion) throws RemoteException, NumberFormatException;

    /**
     * Verifica y agrega fichas a un juego existente en mesa.
     *
     * @param jugadorId N° de ID del jugador.
     * @param seleccion Arreglo de las posiciones de fichas que forman un posible juego.
     * @param posicion  Posición de la lista de fichas a donde se desea agregar nuevas fichas.
     * @return <li>TRUE: Si las fichas forman un juego y se agregaron a la jugada existente en mesa.</li><li>FALSE: Si las fichas no se puede agregar la jugada existente en mesa.</li>
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    boolean agregarFichaJugadaExistente(int jugadorId, String[] seleccion, int posicion) throws RemoteException;

    /**
     * Agrega una ficha del atril al pozo.
     *
     * @param jugadorId N° de ID del jugador.
     * @param posicion  Posición de la ficha seleccionada.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    void agregarFichaPozo(int jugadorId, int posicion) throws RemoteException;

    /**
     * Verifica si un jugador posee el turno.
     *
     * @param jugadorId N° de ID del jugador.
     * @return <li>TRUE: Si el jugador posee el turno.</li><li>FALSE: Si el jugador no posee el turno.</li>
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    boolean isJugadorActual(int jugadorId) throws RemoteException;

    /**
     * Retorna la cantidad de juegos que posee un jugador en mesa.
     *
     * @param jugadorId N° de ID del jugador.
     * @return Cantidad de juegos en mesa.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    int cantidadJuegosMesa(int jugadorId) throws RemoteException;

    /**
     * Asigna el turno al siguiente jugador de la ronda.
     *
     * @param jugadorId N° de ID del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    void cambiarTurno(int jugadorId) throws RemoteException;

    /**
     * Retorna si el atril del jugador está vacío.
     *
     * @param jugadorId N° de ID del jugador
     * @return <li>TRUE: Si el atril se encuentra vacío.</li><li>FALSE: Si el atril no se encuentra vacío.</li>
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    boolean atrilVacio(int jugadorId) throws RemoteException;

    /**
     * Retorna si la pila de muerto fue tomada.
     *
     * @param jugadorId N° de ID del jugador.
     * @return <li>TRUE: Si la pila de muerto fue tomada.</li><li>FALSE: Si la pila de muerto no fue tomada.</li>
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    boolean isMuertoTomado(int jugadorId) throws RemoteException;

    /**
     * Retorna si jugador ha realizado una jugada Canasta.
     *
     * @param jugadorId N° de ID del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    Boolean isCanasta(int jugadorId) throws RemoteException;

    /**
     * Calcula los puntos totales de los jugadores/parejas.
     *
     * @param jugadorCierre N° de ID del jugador que ha cerrado la partida.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    void calcularPuntos(int jugadorCierre) throws RemoteException;

    /**
     * Convierte a texto los puntos de ambos jugadores/parejas.
     *
     * @return Texto con el nombre y puntaje total.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    String mostrarPuntos() throws RemoteException;

    /**
     * Retorna el puntaje parcial de los juegos formados en mesa de un jugador específico.
     *
     * @param jugadorId N° de ID del jugador.
     * @return Puntaje parcial del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    int mostrarPuntosParciales(int jugadorId) throws RemoteException;

    /**
     * Retorna el nombre del jugador.
     *
     * @param jugadorId      N° de ID del jugador.
     * @param desplazamiento Cantidad de veces que se desplazara hacia la derecha.
     * @return N° de ID del jugador resultado del desplazamiento.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    String getJugadorOponente(int jugadorId, int desplazamiento) throws RemoteException;

    /**
     * Retorna la cantidad de fichas que posee el Mazo.
     *
     * @return Cantidad de fichas del Mazo.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    Integer cantidadFichasMazo() throws RemoteException;

    /**
     * Carga en memoria el top de jugadores persistido en disco.
     *
     * @return String del top de jugadores.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    String deserializarPuntos() throws RemoteException;

    /**
     * Retorna el N° de ID del jugador actual.
     *
     * @return N° de ID del jugador actual.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    Integer idJugadorActual() throws RemoteException;

    /**
     * Retorna el ID del compañero del jugador.
     *
     * @param jugadorId N° de ID de jugador.
     * @return N° ID de jugador compañero.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    Integer getIdCompaniero(int jugadorId) throws RemoteException;

    /**
     * Retorna la cantidad de jugadores conectados.
     *
     * @return Cantidad de jugadores.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    Integer getCantidadJugadores() throws RemoteException;

    /**
     * Persiste el disco la partida en curso.
     *
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    void serializarPartida() throws RemoteException;

    /**
     * Carga en memoria la partida persistido en disco.
     *
     * @return <li>TRUE: Si se recupero exitosamente en memoria la partida guardada.</li><li>FALSE: Si no se pudo recuperar en memoria la partida guardada.</li>
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    boolean deserializarPartida() throws RemoteException;

}
