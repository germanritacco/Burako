package ar.edu.unlu.poo.burako.controlador;

import ar.edu.unlu.poo.burako.modelo.IBurako;
import ar.edu.unlu.poo.burako.vista.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

import java.rmi.RemoteException;

public class Controlador implements IControladorRemoto {

    private final IVista vista;

    private IBurako modelo;

    public Controlador(IVista vista) {
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void escribirTexto() {
        try {
            modelo.setTxt("Logre que funciona MVC & Observer!!! ");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public String mostrarTexto() {
        try {
            return modelo.getTxt();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modelo = (IBurako) modeloRemoto;
    }

    @Override
    public void actualizar(IObservableRemoto iObservableRemoto, Object o) throws RemoteException {

    }

}
