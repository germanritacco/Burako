package ar.edu.unlu.poo.burako;

import ar.edu.unlu.poo.burako.modelo.Burako;
import ar.edu.unlu.poo.burako.modelo.IBurako;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.servidor.Servidor;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AppServidor {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
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
                );

                IBurako modelo = new Burako();
                Servidor servidor = new Servidor(ip, Integer.parseInt(port));

                try {
                    servidor.iniciar(modelo);
                    System.out.println("Servidor iniciado en " + ip + ":" + port);
                } catch (RemoteException | RMIMVCException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}