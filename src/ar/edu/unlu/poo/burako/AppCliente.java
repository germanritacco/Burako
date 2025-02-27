package ar.edu.unlu.poo.burako;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.vista.IVista;
import ar.edu.unlu.poo.burako.vista.consola.VistaConsola;
import ar.edu.unlu.poo.burako.vista.grafica.VistaGrafica;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class AppCliente {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                ArrayList<String> ips = Util.getIpDisponibles();

                String ip = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione la IP en la que escuchará peticiones el cliente", "IP del cliente",
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        ips.toArray(),
                        null
                );
                String port = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione el puerto en el que escuchará peticiones el cliente", "Puerto del cliente",
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        null,
                        9999
                );
                String ipServidor = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione la IP en la corre el servidor", "IP del servidor",
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        null,
                        "127.0.0.1"
                );
                String portServidor = (String) JOptionPane.showInputDialog(
                        null,
                        "Seleccione el puerto en el que corre el servidor", "Puerto del servidor",
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        null,
                        8888
                );

                int x = 0;
                int y = 0;
                int width = 500;
                int height = 500;

                IVista vista;
                if (args[0].equals("0")) {
                    vista = new VistaGrafica(x, y, width, height);
                } else {
                    vista = new VistaConsola(x, y, width, height);
                }
                Controlador controlador = new Controlador(vista);
                Cliente c = new Cliente(ip, Integer.parseInt(port), ipServidor, Integer.parseInt(portServidor));

                try {
                    c.iniciar(controlador);
                    vista.nuevoJugador();
                } catch (RemoteException | RMIMVCException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}