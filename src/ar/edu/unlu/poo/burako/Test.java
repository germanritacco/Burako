package ar.edu.unlu.poo.burako;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.modelo.Burako;
import ar.edu.unlu.poo.burako.modelo.IBurako;
import ar.edu.unlu.poo.burako.vista.IVista;
import ar.edu.unlu.poo.burako.vista.consola.VistaConsola;
import ar.edu.unlu.poo.burako.vista.grafica.VistaGrafica;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.Util;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import ar.edu.unlu.rmimvc.servidor.Servidor;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        servidor("127.0.0.1", 8888);
        int jugadores = 2;
        if (jugadores == 2) {
            cliente("127.0.0.1", 9999, "127.0.0.1", 8888, -7, 0, 745, 874,
                    true);
            cliente("127.0.0.1", 9998, "127.0.0.1", 8888, 725, 0, 745, 874,
                    false);
        }
        if (jugadores == 4) {
            cliente("127.0.0.1", 9999, "127.0.0.1", 8888, -7, 0, 745, 440,
                    true);
            cliente("127.0.0.1", 9998, "127.0.0.1", 8888, 725, 0, 745, 440,
                    true);
            cliente("127.0.0.1", 9997, "127.0.0.1", 8888, -7, 432, 745, 440,
                    true);
            cliente("127.0.0.1", 9996, "127.0.0.1", 8888, 725, 432, 745, 440,
                    true);
        }
    }

    public static void servidor(String ip, Integer puerto) {
        IBurako modelo = new Burako();
        Servidor servidor = new Servidor(ip, puerto);
        try {
            servidor.iniciar(modelo);
            System.out.println("Servidor iniciado en " + ip + ":" + puerto.toString());
        } catch (RemoteException | RMIMVCException e) {
            e.printStackTrace();
        }
    }

    public static void cliente(String ip, Integer puerto, String ipServidor, Integer puertoServidor, Integer x,
                               Integer y, Integer width, Integer height, boolean isVistaGrafica) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                ArrayList<String> ips = Util.getIpDisponibles();

                IVista vista;
                if (isVistaGrafica) {
                    vista = new VistaGrafica(x, y, width, height);
                } else {
                    vista = new VistaConsola(x, y, width, height);
                }
                Controlador controlador = new Controlador(vista);
                Cliente c = new Cliente(ip, puerto, ipServidor, puertoServidor);

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

