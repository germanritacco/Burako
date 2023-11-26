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

    private Tablero tableroEquipo1;
    private Tablero tableroEquipo2;


    public Burako() {
        jugadores = new ArrayList<>();

        tableroEquipo1 = new Tablero();
        tableroEquipo2 = new Tablero();


    }

    @Override
    public String getMensajeSistema() throws RemoteException {
        return mensajeSistema;
    }

    public void cerrar(IObservadorRemoto controlador, String jugador) throws RemoteException {
        this.removerObservador(controlador);
        this.desconectarUsuario(getJugador(jugador));
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

    public void mostrarJugadores() throws RemoteException {
        this.notificarObservadores(Eventos.MOSTRAR_JUGADORES);
    }

    public ArrayList<String> getFichas(Jugador jugadorActual) throws RemoteException {
        ArrayList<String> fichas = new ArrayList<>();
        for (Jugador jugador : this.jugadores) {
            if (jugador.equals(jugadorActual)) {
                for (Ficha ficha : jugador.getAtril()) {
                    System.out.println("entro por aca");
                    if (ficha instanceof Comodin) {
                        fichas.add("COMODIN");
                    } else {
                        fichas.add(ficha.toString());
                    }
                }
            }
        }
        return fichas;
    }

    public Jugador getJugador(String nombre) throws RemoteException {
        Jugador jugadorEncontrado = null;
        for (Jugador jugador : this.jugadores) {
            if (jugador.getNombre().equals(nombre)) {
                jugadorEncontrado = jugador;
            }
        }
        return jugadorEncontrado;
    }

    public Jugador setJugador(String nombre) throws RemoteException {
        // TODO validar cuando hay 1 y 3 jugadores, a donde setear
        Jugador nuegoJugador = new Jugador(nombre);
        this.jugadores.add(nuegoJugador);
        //this.notificarObservadores(Eventos.JUGADOR);
        switch (jugadores.size()) {
            case 1, 3 -> this.tableroEquipo1.agregarJugadores(nuegoJugador);
            case 2, 4 -> this.tableroEquipo2.agregarJugadores(nuegoJugador);
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
        this.notificarObservadores(Eventos.MOSTRAR_ATRIL);
    }

    private void tomarMuerto(Jugador jugador) {
        if (!mazo.getMuerto().isEmpty()) {
            jugador.setAtril(mazo.sacarMuerto());
        }
    }

    public void comenzarPartida() throws RemoteException {
        if (jugadores.size() == 2 || jugadores.size() == 4) {
            repartirFichas();
            this.pozo = new Pozo();
            jugadores.get(0).setTurno();
            this.notificarObservadores(Eventos.PARTIDA);
        } else {
            this.enviarMensajeDelSistema("La partida no puede comenzar ya que faltan jugadores en la partida");
        }
    }

    public String mostrarTurno() throws RemoteException {
        for (Jugador jugador : jugadores) {
            if (jugador.isTurno()) {
                return jugador.getNombre();
            }
        }
        return null;
    }

    public ArrayList<String> mostrarPozo() throws RemoteException {
        return pozo.mostrarPozo();
    }

    public void recogerFichaMazo(String nombre) throws RemoteException {
        for (Jugador jugador : this.jugadores) {
            if (jugador.getNombre().equals(nombre)) {
                jugador.addFichaAtril(mazo.recogerFichaMazo());
            }

        }
    }

    public Integer cantidadFichasAtril(String nombre) throws RemoteException {
        for (Jugador jugador : this.jugadores) {
            if (jugador.getNombre().equals(nombre)) {
                return jugador.getAtril().size();
            }

        }
        return null;
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
    public Boolean hayJuegosMesa(Jugador jugadorActual) throws RemoteException {
        switch (jugadores.indexOf(jugadorActual)) {
            case 1, 3 -> {
                return this.tableroEquipo1.isVacio();
            }
            case 2, 4 -> {
                return this.tableroEquipo2.isVacio();
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
    public boolean agregarNuevaJugada(Jugador jugador, String[] seleccion) throws RemoteException, NumberFormatException {
        boolean estado = false;
        ArrayList<Ficha> jugada = armarJugada(jugador, seleccion);
        estado = tableroEquipo1.verificarJugadaNueva(jugada);
        // TODO hardcodeado!!!
        return estado;
    }

    private ArrayList<Ficha> armarJugada(Jugador jugadorActual, String[] seleccion) {
        ArrayList<Ficha> jugada = new ArrayList<>();
        for (Jugador jugador : this.jugadores) {
            if (jugador.equals(jugadorActual)) {
                for (String numero : seleccion) {
                    int posicion = Integer.parseInt(numero);
                    jugada.add(jugador.getAtril().get(posicion - 1));
                }
            }
        }
        return jugada;
    }

    @Override
    public boolean agregarFichaJugadaExistente(Jugador jugador, String[] seleccion, int posicion) throws RemoteException {
        boolean estado = false;
        ArrayList<Ficha> jugada = armarJugada(jugador, seleccion);
        jugada.addAll(tableroEquipo1.getJugadaEnMesa().get(posicion - 1));
        estado = tableroEquipo1.verificarJugadaExistente(jugada, posicion);
        // TODO hardcodeado!!!
        return estado;


    }

    /**
     * @param jugador
     * @param posicion
     * @throws RemoteException
     */
    @Override
    public void agregarFichaPozo(Jugador jugadorActual, int posicion) throws RemoteException {
        for (Jugador jugador : this.jugadores) {
            if (jugador.equals(jugadorActual)) {
                Ficha ficha = jugador.removeFichaAtril(posicion - 1);
                this.pozo.agregarAlPozo(ficha);
                this.notificarObservadores(Eventos.CAMBIO_TURNO);
            }

        }
    }

    /**
     * @param jugador
     * @return
     */
    @Override
    public int cantidadJuegosMesa(Jugador jugadorActual) {
        switch (jugadores.indexOf(jugadorActual)) {
            case 1, 3 -> {
                return this.tableroEquipo1.sizeJugadaEnMesa();
            }
            case 2, 4 -> {
                return this.tableroEquipo2.sizeJugadaEnMesa();
            }
            default -> {
                return 0;
            }
        }
    }

}