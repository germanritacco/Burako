package ar.edu.unlu.poo.burako.modelo;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import java.rmi.RemoteException;

public interface IBurako extends IObservableRemoto {

    String getTxt() throws RemoteException;

    void setTxt(String txt) throws RemoteException;

}
