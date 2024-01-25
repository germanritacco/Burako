package ar.edu.unlu.poo.burako.controlador;

import ar.edu.unlu.poo.burako.modelo.Eventos;
import ar.edu.unlu.poo.burako.modelo.IBurako;
import ar.edu.unlu.poo.burako.modelo.IFicha;
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


    /**
     * Constructor de clase.
     * <li>Asigna la vista al controlador.</li>
     *
     * @param vista Instancia de vista.
     */
    public Controlador(IVista vista) {
        this.vista = vista;
        this.vista.setControlador(this);
    }

    /**
     * Asigna el modelo al controlador.
     *
     * @param modeloRemoto Clase modelo.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modelo = (IBurako) modeloRemoto;
    }

    /**
     * Informa a las vistas si hay nuevos cambios a mostrar.
     *
     * @param modelo Clase modelo.
     * @param cambio Enumerado que controla que cambio mostrar.
     * @throws RemoteException Se lanza si ocurre un error de red.
     */
    @Override
    public void actualizar(IObservableRemoto modelo, Object cambio) throws RemoteException {
        if (cambio instanceof Eventos) {
            switch ((Eventos) cambio) {
                case NUEVO_MENSAJE -> {
                    vista.mostrarTexto(this.modelo.getMensajeSistema() + "\n", this.modelo.getEstadoMensajeSistema());
                }
                case ABANDONAR_PARTIDA -> {
                    vista.abandonarPartida(jugador.getNombre());
                }
                case MOSTRAR_JUGADORES -> {
                    vista.mostrarTexto(this.modelo.getJugadores(), false);
                }
                case PARTIDA -> {
                    ArrayList<IFicha> atril = this.modelo.getFichas(jugador.getId());
                    ArrayList<IFicha> pozo = this.modelo.mostrarPozo();
                    this.vista.iniciarPartida(atril, pozo);
                }
                case CAMBIO_TURNO -> {
                    if (isJugadorTurno()) {
                        vista.mostrarAtril(mostrarAtril());
                        vista.disableComponents();
                    }
                    this.modelo.cambiarTurno(jugador.getId());
                    // Siguiente jugador
                    if (isJugadorTurno()) {
                        String jugadorActual = this.modelo.mostrarTurno(jugador.getId());
                        vista.mostrarTurno(jugadorActual);
                        vista.mostrarPozo(this.modelo.mostrarPozo());
                        vista.mostrarAtril(mostrarAtril());
                        vista.enableComponents(true);
                    }
                }
                case PUNTAJE -> {
                    vista.mostrarPuntos(this.modelo.mostrarPuntos());
                }
            }
        }
    }

    public void cerrarApp() {
        try {
            if (jugador != null) {
                this.modelo.cerrar(this, jugador.getId());
            }
            System.exit(0);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void nuevoJugador(String string) {
        try {
            jugador = modelo.setJugador(string);
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

    public void tomarFichaMazo() {
        try {
            modelo.recogerFichaMazo(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer cantidadFichasAtril() {
        try {
            return modelo.cantidadFichasAtril(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String abandonarPartida() {
        try {
            modelo.abandonarPartida();
            return jugador.getNombre();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean hayJuegosMesa() {
        try {
            return modelo.hayJuegosMesa(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean agregarNuevaJugada(String[] seleccion) {
        try {
            return modelo.agregarNuevaJugada(jugador.getId(), seleccion);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public int cantidadJuegosMesa() {
        try {
            return modelo.cantidadJuegosMesa(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean agregarFichaJugadaExistente(int posicion, String[] seleccion) {
        try {
            return modelo.agregarFichaJugadaExistente(jugador.getId(), seleccion, posicion);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarFichaPozo(int posicion) {
        try {
            modelo.agregarFichaPozo(jugador.getId(), posicion);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isJugadorTurno() {
        try {
            return modelo.isJugadorActual(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String nombreJugadorTurno() {
        try {
            return modelo.nombreJugadorTurno();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void iniciarPartida() {
        try {
            modelo.comenzarPartida();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPozoVacio() {
        try {
            return modelo.isPozoVacio();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void recogerPozo() {
        try {
            modelo.recogerPozo(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<IFicha> mostrarAtril() {
        try {
            return this.modelo.getFichas(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ArrayList<IFicha>> mostrarJuegosEnMesa() {
        try {
            return modelo.mostrarJuegosEnMesa(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean atrilVacio() {
        try {
            return modelo.atrilVacio(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean tomoMuerto() {
        try {
            return modelo.tomoMuerto(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void tomarMuerto() {
        try {
            modelo.tomarMuerto(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCanasta() {
        try {
            return modelo.isCanasta(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void finalizarPartida() {
        try {
            modelo.calcularPuntos(jugador.getId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String getJugador() {
        return jugador.getNombre();
    }
}
