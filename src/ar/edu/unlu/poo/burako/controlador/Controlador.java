package ar.edu.unlu.poo.burako.controlador;

import ar.edu.unlu.poo.burako.modelo.Eventos;
import ar.edu.unlu.poo.burako.modelo.IBurako;
import ar.edu.unlu.poo.burako.modelo.Jugador;
import ar.edu.unlu.poo.burako.vista.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Controlador implements IControladorRemoto {

    private final IVista vista;

    private IBurako modelo;

    private Jugador jugador;

    public Controlador(IVista vista) {
        this.vista = vista;
        this.vista.setControlador(this);
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modelo = (IBurako) modeloRemoto;
    }

    @Override
    public void actualizar(IObservableRemoto modelo, Object cambio) throws RemoteException {
        if (cambio instanceof Eventos) {
            switch ((Eventos) cambio) {
                case NUEVO_MENSAJE -> vista.mostrarTexto(this.modelo.getMensajeSistema() + "\n");
                case MOSTRAR_ATRIL -> {
                    ArrayList<String> fichas = this.modelo.getFichas(jugador);
                    this.vista.mostrarAtril(fichas);
                }
                case MOSTRAR_JUGADORES -> vista.mostrarTexto(this.modelo.getJugadores());
                case PARTIDA -> {
                    ArrayList<String> fichas = this.modelo.getFichas(jugador);
                    ArrayList<String> pozo = this.modelo.mostrarPozo();
                    this.vista.mostrarAtril(fichas);
                }
                case ABANDONAR_PARTIDA -> vista.abandonarPartida(jugador.getNombre());
                case CAMBIO_TURNO -> {

                }
            }
        }
    }

    public void cerrarApp() {
        try {
            this.modelo.cerrar(this, jugador.getNombre());
            System.exit(0);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void nuevoJugador(String string) {
        try {
            jugador = modelo.setJugador(string);
            // jugador = modelo.getJugador(string);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String mostrarJugadores() {
        try {
            return modelo.getJugadores();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void repartirCartas() {
        try {
            modelo.repartirFichas();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isTurno() {
        return jugador.isTurno();
    }

    public void tomarFichaMazo() {
        try {
            modelo.recogerFichaMazo(jugador.getNombre());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer cantidadFichasAtril() {
        try {
            return modelo.cantidadFichasAtril(jugador.getNombre());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void abandonarPartida() {
        try {
            modelo.abandonarPartida();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean hayJuegosMesa() {
        try {
            return modelo.hayJuegosMesa(jugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean agregarNuevaJugada(String[] seleccion) {
        try {
            return modelo.agregarNuevaJugada(jugador, seleccion);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public int cantidadJuegosMesa() {
        return modelo.cantidadJuegosMesa(jugador);
    }

    public boolean agregarFichaJugadaExistente(int posicion, String[] seleccion) {
        try {
            return modelo.agregarFichaJugadaExistente(jugador, seleccion, posicion);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarFichaPozo(int posicion) {
        try {
            modelo.agregarFichaPozo(jugador, posicion);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void cambiarTurno() {
    }
}
