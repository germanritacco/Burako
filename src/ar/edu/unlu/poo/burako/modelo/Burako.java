package ar.edu.unlu.poo.burako.modelo;

import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Burako extends ObservableRemoto implements IBurako {

    private Mazo mazo;

    private Pozo pozo;

    private final ArrayList<Jugador> jugadores;

    private String mensajeSistema;

    private final Tablero tableroEquipo1;
    private final Tablero tableroEquipo2;


    public Burako() {
        jugadores = new ArrayList<>();
        tableroEquipo1 = new Tablero();
        tableroEquipo2 = new Tablero();
    }

    @Override
    public String getMensajeSistema() throws RemoteException {
        return mensajeSistema;
    }

    public void cerrar(IObservadorRemoto controlador, int jugadorId) throws RemoteException {
        this.removerObservador(controlador);
        this.desconectarUsuario(jugadores.get(jugadorId));
    }

    @Override
    public void desconectarUsuario(Jugador jugador) throws RemoteException {
        this.enviarMensajeDelSistema("El jugador " + jugador.getNombre() + " se ha retirado de la partida");
        jugadores.remove(jugador);
    }

    public String getJugadores() throws RemoteException {
        String texto = "";
        for (Jugador jugador : this.jugadores) {
            texto += " • " + jugador.getNombre() + "\n";
        }
        return texto;
    }

    public ArrayList<String> getFichas(int jugadorId) throws RemoteException {
        ArrayList<String> fichas = new ArrayList<>();
        Jugador jugador = jugadores.get(jugadorId);
        for (Ficha ficha : jugador.getAtril()) {
            if (ficha instanceof Comodin) {
                fichas.add("COMODIN");
            } else {
                fichas.add(ficha.toString());
            }
        }
        return fichas;
    }

    public Jugador setJugador(String nombre) throws RemoteException {
        Jugador nuegoJugador = new Jugador(nombre);
        this.jugadores.add(nuegoJugador);
        switch (jugadores.size()) {
            case 0, 2 -> this.tableroEquipo1.agregarJugadores(nuegoJugador);
            case 1, 3 -> this.tableroEquipo2.agregarJugadores(nuegoJugador);
        }
        this.enviarMensajeDelSistema("El jugador " + nombre + " se ha unido a la partida");
        return nuegoJugador;
    }

    @Override
    public void enviarMensajeDelSistema(String mensaje) throws RemoteException {
        this.mensajeSistema = mensaje;
        this.notificarObservadores(Eventos.NUEVO_MENSAJE);
    }

    public void repartirFichas() throws RemoteException {
        switch (jugadores.size()) {
            case 2 -> this.mazo = new Mazo(jugadores.get(0), jugadores.get(1));
            case 4 -> this.mazo = new Mazo(jugadores.get(0), jugadores.get(1), jugadores.get(2), jugadores.get(3));
        }
    }

    private void tomarMuerto(int jugadorId) {
        if (!mazo.getMuerto().isEmpty()) {
            Jugador jugador = jugadores.get(jugadorId);
            jugador.addAtril(mazo.sacarMuerto());
        }
    }

    public void comenzarPartida() throws RemoteException {
        if (jugadores.size() == 2 || jugadores.size() == 4) {
            repartirFichas();
            this.pozo = new Pozo();
            jugadores.get(0).setTurno(true);
            this.notificarObservadores(Eventos.PARTIDA);
        } else {
            this.enviarMensajeDelSistema("La partida no puede comenzar ya que faltan jugadores en la partida");
        }
    }

    /**
     * @return
     */
    @Override
    public boolean isPozoVacio() throws RemoteException {
        return pozo.isEmpty();
    }

    /**
     * @throws RemoteException
     */
    @Override
    public void recogerPozo(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        jugador.addAtril(pozo.recogerPozo());
        this.notificarObservadores(Eventos.CAMBIO_FICHAS_ATRIL);
    }

    /**
     * @return
     * @throws RemoteException
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
     * @param jugadorId
     * @throws RemoteException
     */
    @Override
    public ArrayList<ArrayList<String>> mostrarJuegosEnMesa(int jugadorId) throws RemoteException {
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

    public String mostrarTurno(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        return jugador.getNombre();
    }

    /**
     *
     */

    public ArrayList<String> mostrarPozo() throws RemoteException {
        return pozo.mostrarPozo();
    }

    public void recogerFichaMazo(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        jugador.addFichaAtril(mazo.recogerFichaMazo());
        this.notificarObservadores(Eventos.CAMBIO_FICHAS_ATRIL);
    }

    public Integer cantidadFichasAtril(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        return jugador.getAtril().size();
    }

    /**
     *
     */
    @Override
    public void abandonarPartida() throws RemoteException {
        this.notificarObservadores(Eventos.ABANDONAR_PARTIDA);

    }

    /**
     * @param jugador
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
     * @param seleccion
     * @throws RemoteException
     */
    @Override
    public boolean agregarNuevaJugada(int jugadorId, String[] seleccion) throws RemoteException, NumberFormatException {
        boolean estado = false;
        ArrayList<Ficha> jugada = armarJugada(jugadorId, seleccion);
        switch (jugadorId) {
            case 0, 2 -> {
                estado = tableroEquipo1.verificarJugadaNueva(jugada);
                System.out.println("paso pir aca eq 1");
                if (estado) {
                    borrarJugadaAtril(jugadorId, seleccion);
                }
            }
            case 1, 3 -> {
                estado = tableroEquipo2.verificarJugadaNueva(jugada);
                System.out.println("paso pir aca eq 2");
                if (estado) {
                    borrarJugadaAtril(jugadorId, seleccion);
                }
            }
        }
        return estado;
    }

    private ArrayList<Ficha> armarJugada(int jugadorId, String[] seleccion) {
        ArrayList<Ficha> jugada = new ArrayList<>();
        Jugador jugador = jugadores.get(jugadorId);
        for (String numero : seleccion) {
            int posicion = Integer.parseInt(numero);
            jugada.add(jugador.getAtril().get(posicion - 1));
        }
        return jugada;
    }

    private void borrarJugadaAtril(int jugadorId, String[] seleccion) {
        Jugador jugador = jugadores.get(jugadorId);
        int incremento = 0;
        for (String numero : seleccion) {
            int posicion = Integer.parseInt(numero) - incremento;
            jugador.removeFichaAtril(posicion - 1);
            incremento++;
        }
    }

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
     * @param jugador
     * @param posicion
     * @throws RemoteException
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
     * @param jugadorId
     * @return
     */
    @Override
    public boolean isJugadorActual(int jugadorId) throws RemoteException {
        Jugador jugador = jugadores.get(jugadorId);
        return jugador.isTurno();
    }

    /**
     * @param jugador
     * @return
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

    public void cambiarTurno(int jugadorId) throws RemoteException {
        int indice = (jugadorId + 1) % jugadores.size(); // Recorrido circular del ArrayList
        jugadores.get(indice).setTurno(true);
    }

}