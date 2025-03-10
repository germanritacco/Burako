package ar.edu.unlu.poo.burako.controlador;

import ar.edu.unlu.poo.burako.modelo.*;
import ar.edu.unlu.poo.burako.vista.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Controlador implements IControladorRemoto {

    private final IVista vista;
    private IBurako modelo;
    private IJugador jugador;


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
                    vista.mostrarJuegosMesa(this.modelo.mostrarJuegosEnMesa(jugador.getId(), false));
                }
                case CAMBIO_TURNO -> {
                    vista.mostrarPozo(this.modelo.mostrarPozo());
                    vista.mostrarAtril(mostrarAtril());
                    String jugadorActual = this.modelo.nombreJugadorTurno();
                    vista.mostrarTurno(jugadorActual);
                    if (!isJugadorTurno()) {
                        vista.disableComponents();
                    }
                    // Siguiente jugador
                    if (isJugadorTurno()) {
                        vista.enableComponents(true);
                    }
                }
                case PUNTAJE -> {
                    vista.mostrarPuntos(this.modelo.mostrarPuntosParciales(jugador.getId()));
                    int cantidadJugadores = this.modelo.getCantidadJugadores();
                    int oponente = (jugador.getId() + 1) % cantidadJugadores;
                    vista.mostrarPuntosOponente(this.modelo.mostrarPuntosParciales(oponente));
                }
                case CAMBIO_JUGADAS_OPONENTE -> {
                    if (!isJugadorTurno()) {
                        int cantidadJugadores = this.modelo.getCantidadJugadores();
                        int oponente = (jugador.getId() + 1) % cantidadJugadores;
                        vista.mostrarJuegosMesaOponente(this.modelo.mostrarJuegosEnMesa(oponente, false));
                        if (this.modelo.getIdCompaniero(jugador.getId()).equals(this.modelo.idJugadorActual())) {
                            vista.mostrarJuegosMesa(this.modelo.mostrarJuegosEnMesa(jugador.getId(), false));
                        }
                    }
                }
                case CANTIDAD_FICHAS_ATRIL -> {
                    vista.mostrarCantidadFichasAtril();
                }
                case CANTIDAD_FICHAS_MAZO -> {
                    vista.mostrarCantidadFichasMazo(this.modelo.cantidadFichasMazo());
                }
                case CAMBIO_FICHAS_POZO -> {
                    vista.mostrarPozo(this.modelo.mostrarPozo());
                }
                case TOMAR_MUERTO -> {
                    vista.tomarMuerto();
                }
            }
        }
    }

    public void cerrarApp() {
        try {
            if (jugador != null) {
                this.modelo.cerrar(this, jugador.getId());
            }
            //  System.exit(0);
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
            return modelo.cantidadFichasAtril(jugador.getId(), 0);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer cantidadFichasMazo() {
        try {
            return modelo.cantidadFichasMazo();
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
            return modelo.mostrarJuegosEnMesa(jugador.getId(), true);
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

    public boolean isMuertoTomado() {
        try {
            return modelo.isMuertoTomado(jugador.getId());
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

    public String getOponente(int desplazamiento) {
        try {
            return modelo.getJugadorOponente(jugador.getId(), desplazamiento);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String deserializar() {
        try {
            return modelo.deserializarPuntos();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPuntos() {
        try {
            return modelo.mostrarPuntos();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getCantidadJugadores() {
        try {
            return modelo.getCantidadJugadores();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getCantidadFichasAtril(int desplazamiento) {
        try {
            int jugadorId = jugador.getId();
            return modelo.cantidadFichasAtril(jugadorId, desplazamiento);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardarPartida() {
        try {
            modelo.serializarPartida();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cargarPartida() {
        try {
            return modelo.deserializarPartida();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
