package ar.edu.unlu.poo.burako.modelo;

import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;

public class Burako extends ObservableRemoto implements IBurako {

    private Mazo mazo;
    private Pozo pozo;
    private final ArrayList<Jugador> jugadores;
    private String mensajeSistema;
    private final Tablero tableroEquipo1;
    private final Tablero tableroEquipo2;

    private ArrayList<Tablero> topPuntos;

    /**
     * Constructor de clase.
     * <li>Crea el ArrayList de Jugador, y 2 instancias de Tablero.</li>
     */
    public Burako() {
        jugadores = new ArrayList<>();
        tableroEquipo1 = new Tablero();
        tableroEquipo2 = new Tablero();
        deserializarPuntos();
    }

    /**
     * Recupera el mensaje guardado en la clase modelo.
     *
     * @return Mensaje a mostrar en las vistas.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public String getMensajeSistema() throws RemoteException {
        return mensajeSistema;
    }

    /**
     * Cierra la conexión de un jugador con el servidor remoto.
     *
     * @param controlador Controlador que está asociado a una vista.
     * @param jugadorId   N° de ID del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public void cerrar(IObservadorRemoto controlador, int jugadorId) throws RemoteException {
        this.removerObservador(controlador);
        this.desconectarUsuario(jugadores.get(jugadorId));
    }

    /**
     * Remueve al jugador de la lista de jugadores.
     *
     * @param jugador Instancia de jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    private void desconectarUsuario(Jugador jugador) throws RemoteException {
        this.enviarMensajeDelSistema("El jugador " + jugador.getNombre() + " se ha retirado de la partida");
        jugadores.remove(jugador);
    }

    /**
     * Obtiene El listado de jugadores conectados.
     *
     * @return Texto del listado de jugadores.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public String getJugadores() throws RemoteException {
        StringBuilder texto = new StringBuilder();
        for (Jugador jugador : this.jugadores) {
            texto.append(" • ").append(jugador.getNombre()).append("\n");
        }
        return texto.toString();
    }

    /**
     * Retorna el listado de fichas de un jugador.
     *
     * @param jugadorId N° de ID del jugador.
     * @return ArrayList de String, cada posición contiene el número y color de ficha.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public ArrayList<IFicha> getFichas(int jugadorId) throws RemoteException {
        ArrayList<IFicha> fichas = new ArrayList<>();
        Jugador jugador = jugadores.get(jugadorId);
        fichas = new ArrayList<>(jugador.getAtril());
        return fichas;
    }

    /**
     * Agrega un nuevo jugador
     *
     * @param nombre Nombre del jugador.
     * @return Instancia de jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public Jugador setJugador(String nombre) throws RemoteException {
        Jugador nuegoJugador = new Jugador(nombre);
        this.jugadores.add(nuegoJugador);
        switch (jugadores.size()) {
            case 1, 3 -> this.tableroEquipo1.agregarJugadores(nuegoJugador);
            case 2, 4 -> this.tableroEquipo2.agregarJugadores(nuegoJugador);
        }
        this.enviarMensajeDelSistema("El jugador " + nombre + " se ha unido a la partida");
        return nuegoJugador;
    }

    /**
     * Envía mensajes desde el modelo hacia las vistas.
     *
     * @param mensaje Mensaje a mostrar en las vistas.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    private void enviarMensajeDelSistema(String mensaje) throws RemoteException {
        this.mensajeSistema = mensaje;
        this.notificarObservadores(Eventos.NUEVO_MENSAJE);
    }

    /**
     * Reparte las fichas para los jugadores, pila de muertos y mazo.
     *
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    private void repartirFichas() throws RemoteException {
        switch (jugadores.size()) {
            case 2 -> this.mazo = new Mazo(jugadores.get(0), jugadores.get(1));
            case 4 -> this.mazo = new Mazo(jugadores.get(0), jugadores.get(1), jugadores.get(2), jugadores.get(3));
        }
    }

    /**
     * Agrega al atril del jugador las fichas de la pila de muertos.
     *
     * @param jugadorId N° de ID del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public void tomarMuerto(int jugadorId) throws RemoteException {
        if (!mazo.getMuerto().isEmpty()) {
            Jugador jugador = jugadores.get(jugadorId);
            jugador.addAtril(mazo.sacarMuerto());
            jugador.setTomoMuerto(true);
        }
    }

    /**
     * Inicia la partida:
     * <li>Reparte las fichas.</li>
     * <li>Instancia el pozo.</li>
     * <li>Asigna el turno al primer jugador.</li>
     *
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public void comenzarPartida() throws RemoteException {
        if (jugadores.size() == 2 || jugadores.size() == 4) {
            repartirFichas();
            this.pozo = new Pozo();
            jugadores.getFirst().setTurno(true);
            this.notificarObservadores(Eventos.PARTIDA);
        } else {
            this.enviarMensajeDelSistema("La partida no puede comenzar, ya que faltan jugadores en la partida");
        }
    }

    /**
     * Verifica si el pozo esta vacío.
     *
     * @return <li>TRUE: Si el pozo esta vacío.</li><li>FALSE: Si el pozo contiene fichas.</li>
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public boolean isPozoVacio() throws RemoteException {
        return pozo.isEmpty();
    }

    /**
     * Agrega al atril del jugador todas las fichas del pozo.
     *
     * @param jugadorId N° de ID del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public void recogerPozo(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        jugador.addAtril(pozo.recogerPozo());
        this.notificarObservadores(Eventos.CAMBIO_FICHAS_ATRIL);
    }

    /**
     * Retorna el nombre del jugador que posee el turno.
     *
     * @return Nombre del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public String nombreJugadorTurno() throws RemoteException {
        for (Jugador jugador : jugadores) {
            if (jugador.isTurno()) {
                return jugador.getNombre();
            }
        }
        return null;
    }

    /**
     * Retorna una lista que posee en cada posición, la lista de fichas que forman una jugada en mesa.
     *
     * @param jugadorId N° de ID del jugador.
     * @return Lista de listas de fichas que forma las jugadas.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public ArrayList<ArrayList<IFicha>> mostrarJuegosEnMesa(int jugadorId) throws RemoteException {
        switch (jugadorId) {
            case 0, 2 -> {
                return this.tableroEquipo1.mostrarJuegosEnMesa();
            }
            case 1, 3 -> {
                return this.tableroEquipo2.mostrarJuegosEnMesa();
            }
            default -> {
                return null;
            }
        }


    }

    /**
     * Retorna el nombre del jugador que posee el turno.
     *
     * @param jugadorId N° de ID del jugador.
     * @return Nombre del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public String mostrarTurno(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        return jugador.getNombre();
    }

    /**
     * Retorna el listado de fichas del pozo.
     *
     * @return ArrayList de String, cada posición contiene el número y color de ficha.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public ArrayList<IFicha> mostrarPozo() throws RemoteException {
        return pozo.mostrarPozo();
    }

    /**
     * Toma una ficha del mazo y la agrega al atril del jugador.
     *
     * @param jugadorId N° de ID del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public void recogerFichaMazo(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        jugador.addFichaAtril(mazo.recogerFichaMazo());
        this.notificarObservadores(Eventos.CAMBIO_FICHAS_ATRIL);
    }

    /**
     * Retorna la cantidad de fichas que posee el jugador en el atril.
     *
     * @param jugadorId N° de ID del jugador.
     * @return Cantidad de fichas.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public Integer cantidadFichasAtril(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        return jugador.getAtril().size();
    }

    /**
     * Informa a las vistas que se ha desconectado un jugador.
     *
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public void abandonarPartida() throws RemoteException {
        this.notificarObservadores(Eventos.ABANDONAR_PARTIDA);

    }

    /**
     * Verifica si el jugador posee juegos en mesa.
     *
     * @param jugadorId N° de ID del jugador.
     * @return <li>TRUE: Si la mesa posee juegos.</li><li>FALSE: Si la mesa esta vacía.</li>
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public Boolean hayJuegosMesa(int jugadorId) throws RemoteException {
        switch (jugadorId) {
            case 0, 2 -> {
                return !this.tableroEquipo1.isVacio();
            }
            case 1, 3 -> {
                return !this.tableroEquipo2.isVacio();
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * Verifica y agrega un nuevo juego a mesa.
     *
     * @param jugadorId N° de ID del jugador.
     * @param seleccion Arreglo de las posiciones de fichas que forman un posible juego.
     * @return <li>TRUE: Si las fichas forman un juego y se agregaron a la mesa.</li><li>FALSE: Si las fichas no forman un juego.</li>
     * @throws RemoteException       Se lanza si ocurre un error de red.
     * @throws NumberFormatException Se lanza si hay un error al convertir una cadena a un número.
     */
    @Override
    public boolean agregarNuevaJugada(int jugadorId, String[] seleccion) throws RemoteException, NumberFormatException {
        boolean estado = false;
        ArrayList<Ficha> jugada = armarJugada(jugadorId, seleccion);
        switch (jugadorId) {
            case 0, 2 -> {
                estado = tableroEquipo1.verificarJugadaNueva(jugada);
                if (estado) {
                    borrarJugadaAtril(jugadorId, seleccion);
                }
            }
            case 1, 3 -> {
                estado = tableroEquipo2.verificarJugadaNueva(jugada);
                if (estado) {
                    borrarJugadaAtril(jugadorId, seleccion);
                }
            }
        }
        return estado;
    }

    /**
     * Arma una lista de fichas, a partir de un arreglo que posee las posiciones de ficha.
     *
     * @param jugadorId N° de ID del jugador.
     * @param seleccion Arreglo de las posiciones de fichas que forman un posible juego.
     * @return Lista de fichas.
     */
    private ArrayList<Ficha> armarJugada(int jugadorId, String[] seleccion) {
        ArrayList<Ficha> jugada = new ArrayList<>();
        Jugador jugador = jugadores.get(jugadorId);
        for (String numero : seleccion) {
            int posicion = Integer.parseInt(numero);
            jugada.add(jugador.getAtril().get(posicion - 1));
        }
        return jugada;
    }

    /**
     * Remueve del atril las fichas seleccionadas.
     *
     * @param jugadorId N° de ID del jugador.
     * @param seleccion Arreglo de las posiciones de fichas que forman un posible juego.
     */
    private void borrarJugadaAtril(int jugadorId, String[] seleccion) {
        Jugador jugador = jugadores.get(jugadorId);
        int incremento = 0;
        for (String numero : seleccion) {
            int posicion = Integer.parseInt(numero) - incremento;
            jugador.removeFichaAtril(posicion - 1);
            incremento++;
        }
    }

    /**
     * Verifica y agrega fichas a un juego existente en mesa.
     *
     * @param jugadorId N° de ID del jugador.
     * @param seleccion Arreglo de las posiciones de fichas que forman un posible juego.
     * @param posicion  Posición de la lista de fichas a donde se desea agregar nuevas fichas.
     * @return <li>TRUE: Si las fichas forman un juego y se agregaron a la jugada existente en mesa.</li><li>FALSE: Si las fichas no se puede agregar la jugada existente en mesa.</li>
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public boolean agregarFichaJugadaExistente(int jugadorId, String[] seleccion, int posicion) throws RemoteException {
        boolean estado = false;
        ArrayList<Ficha> jugada = armarJugada(jugadorId, seleccion);
        switch (jugadorId) {
            case 0, 2 -> {
                jugada.addAll(tableroEquipo1.getJugadaEnMesa().get(posicion - 1));
                estado = tableroEquipo1.verificarJugadaExistente(jugada, posicion);
                if (estado) {
                    borrarJugadaAtril(jugadorId, seleccion);
                }
            }
            case 1, 3 -> {
                jugada.addAll(tableroEquipo2.getJugadaEnMesa().get(posicion - 1));
                estado = tableroEquipo2.verificarJugadaExistente(jugada, posicion);
                if (estado) {
                    borrarJugadaAtril(jugadorId, seleccion);
                }
            }
        }
        return estado;
    }

    /**
     * Agrega una ficha del atril al pozo.
     *
     * @param jugadorId N° de ID del jugador.
     * @param posicion  Posición de la ficha seleccionada.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public void agregarFichaPozo(int jugadorId, int posicion) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        Ficha ficha = jugador.removeFichaAtril(posicion - 1);
        this.pozo.agregarAlPozo(ficha);
        jugador.setTurno(false);
        this.notificarObservadores(Eventos.CAMBIO_TURNO);
    }

    /**
     * Verifica si un jugador posee el turno.
     *
     * @param jugadorId N° de ID del jugador.
     * @return <li>TRUE: Si el jugador posee el turno.</li><li>FALSE: Si el jugador no posee el turno.</li>
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public boolean isJugadorActual(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        return jugador.isTurno();
    }

    /**
     * Retorna la cantidad de juegos que posee un jugador en mesa.
     *
     * @param jugadorId N° de ID del jugador.
     * @return Cantidad de juegos en mesa.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public int cantidadJuegosMesa(int jugadorId) throws RemoteException {
        switch (jugadorId) {
            case 0, 2 -> {
                return this.tableroEquipo1.sizeJugadaEnMesa();
            }
            case 1, 3 -> {
                return this.tableroEquipo2.sizeJugadaEnMesa();
            }
            default -> {
                return 0;
            }
        }
    }

    /**
     * Asigna el turno al siguiente jugador de la ronda.
     *
     * @param jugadorId N° de ID del jugador.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public void cambiarTurno(int jugadorId) throws RemoteException {
        int indice = (jugadorId + 1) % jugadores.size(); // Recorrido circular del ArrayList
        jugadores.get(indice).setTurno(true);
    }

    /**
     * @param jugadorId
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean atrilVacio(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        return jugador.atrilIsEmpty();
    }

    /**
     * @param jugadorId
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean tomoMuerto(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        return jugador.isTomoMuerto();
    }

    /**
     * @param jugadorId
     * @throws RemoteException
     */
    @Override
    public Boolean isCanasta(int jugadorId) throws RemoteException {
        switch (jugadorId) {
            case 0, 2 -> {
                return this.tableroEquipo1.isCanasta();
            }
            case 1, 3 -> {
                return this.tableroEquipo2.isCanasta();
            }
            default -> {
                return null;
            }
        }

    }

    /**
     * @throws RemoteException
     */
    @Override
    public void calcularPuntos(int jugadorCierre) throws RemoteException {
        int puntosEnMesa;
        int puntosAtril;
        for (Jugador jugador : jugadores) {
            jugador.calcularPuntosAtril();
            int jugadorId = jugador.getId();
            switch (jugadorId) {
                case 0, 2 -> {
                    if (jugadorId == jugadorCierre) {
                        this.tableroEquipo1.sumarPuntos(100);
                    }
                    this.tableroEquipo1.puntosEnMesa();
                    this.tableroEquipo1.restarPuntos(jugador.getPuntos());
                }
                case 1, 3 -> {
                    if (jugadorId == jugadorCierre) {
                        this.tableroEquipo2.sumarPuntos(100);
                    }
                    this.tableroEquipo2.puntosEnMesa();
                    this.tableroEquipo2.restarPuntos(jugador.getPuntos());
                }
            }
        }
        puntosMuerto();
        ganador();
        this.notificarObservadores(Eventos.PUNTAJE);
    }

    /**
     * @return
     */
    @Override
    public String mostrarPuntos() throws RemoteException {
        String puntos = "";
        puntos = tableroEquipo1.mostrarPuntajeJugadores();
        puntos = "\n";
        puntos = tableroEquipo2.mostrarPuntajeJugadores();
        return puntos;
    }

    private void puntosMuerto() {
        switch (jugadores.size()) {
            case 4 -> {
                if (jugadores.get(0).isTomoMuerto() || jugadores.get(2).isTomoMuerto()) {
                    this.tableroEquipo1.sumarPuntos(100);
                } else {
                    this.tableroEquipo1.restarPuntos(100);
                }
                if (jugadores.get(1).isTomoMuerto() || jugadores.get(3).isTomoMuerto()) {
                    this.tableroEquipo2.sumarPuntos(100);
                } else {
                    this.tableroEquipo2.restarPuntos(100);
                }
            }
            case 2 -> {
                if (jugadores.get(0).isTomoMuerto()) {
                    this.tableroEquipo1.sumarPuntos(100);
                } else {
                    this.tableroEquipo1.restarPuntos(100);
                }
                if (jugadores.get(1).isTomoMuerto()) {
                    this.tableroEquipo2.sumarPuntos(100);
                } else {
                    this.tableroEquipo2.restarPuntos(100);
                }
            }
        }
    }

    public void ganador() {
        Tablero tableroGanador;
        if (tableroEquipo1.getPuntosEquipos() > tableroEquipo2.getPuntosEquipos()) {
            tableroGanador = tableroEquipo1;
        } else {
            tableroGanador = tableroEquipo2;
        }
        actualizarTopPuntos(tableroGanador);
    }

    public void actualizarTopPuntos(Tablero tableroGanador) {
        if (topPuntos.size() < 5) {
            topPuntos.add(tableroGanador);
        } else {
            topPuntos.sort(Comparator.comparingInt(Tablero::getPuntosEquipos).reversed());
            int ultimoTop = topPuntos.get(topPuntos.size() - 1).getPuntosEquipos();
            if (tableroGanador.getPuntosEquipos() > ultimoTop) {
                topPuntos.remove(topPuntos.size() - 1);
                topPuntos.add(tableroGanador);
            }
            topPuntos.sort(Comparator.comparingInt(Tablero::getPuntosEquipos));
        }
        serializarPuntos();
    }

    public void serializarPuntos() {
        try {
            FileOutputStream fosPuntos = new FileOutputStream("puntos.bin");
            var oos = new ObjectOutputStream(fosPuntos);
            oos.writeObject(topPuntos);
            oos.close();
            fosPuntos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deserializarPuntos() {
        try {
            File archivo = new File("puntos.bin");
            if (archivo.exists()) {
                FileInputStream fisPuntos = new FileInputStream(archivo);
                var ois = new ObjectInputStream(fisPuntos);
                topPuntos = (ArrayList<Tablero>) ois.readObject();
                ois.close();
                fisPuntos.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}