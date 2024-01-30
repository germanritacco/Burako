package ar.edu.unlu.poo.burako;


import ar.edu.unlu.poo.burako.modelo.Burako;
import ar.edu.unlu.poo.burako.modelo.IBurako;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.servidor.Servidor;

import java.rmi.RemoteException;

public class AppServidor {

    public static void main(String[] args) {

        String ip = args[0];
        String port = args[1];

        /*
        ArrayList<String> ips = Util.getIpDisponibles();

        String ip = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione la IP en la que escuchará peticiones el servidor", "IP del servidor",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                ips.toArray(),
                null
        );
        String port = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione el puerto en el que escuchará peticiones el servidor", "Puerto del servidor",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                null,
                8888
        );*/

        IBurako modelo = new Burako();
        Servidor servidor = new Servidor(ip, Integer.parseInt(port));

        try {
            servidor.iniciar(modelo);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (RMIMVCException e) {
            e.printStackTrace();
        }
    }

}