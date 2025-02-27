package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;
import ar.edu.unlu.poo.burako.vista.ColorRGB;

import java.awt.*;

public class FlujoSeleccionarFichas extends Flujo {

    private boolean juegoMesa;

    public FlujoSeleccionarFichas(VistaConsola vista, Controlador controlador, boolean isJuegoMesa) {
        super(vista, controlador);
        juegoMesa = isJuegoMesa;
    }

    @Override
    public Flujo procesarEntrada(String string) {
        try {
            if (string == null) {
                throw new NullPointerException("La cadena de entrada no puede ser nula");
            }
            if (string.equals("0")) {
                return new FlujoJugada(vista, controlador);
            } else {

                String[] seleccion = string.split(",");
                if (juegoMesa) {
                    return new FlujoAgregarFichaJuegoMesa(vista, controlador, seleccion);

                } else {
                    validarBajarJuego(seleccion);
                    return new FlujoJugada(vista, controlador);
                }
            }
        } catch (NullPointerException e) {
            vista.appendColor("Error: " + e.getMessage(), ColorRGB.RED);
        } catch (NumberFormatException e) {
            vista.appendColor("Error: La cadena no es un número válido.", ColorRGB.RED);
        }

        return this;

    }

    private void validarBajarJuego(String[] seleccion) {
        try {
            vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
            if (controlador.agregarNuevaJugada(seleccion)) {
                vista.appendColor(" Jugada bajada a la mesa correctamente.", ColorRGB.GREEN);
                if (controlador.atrilVacio() && !controlador.isMuertoTomado()) {
                    controlador.tomarMuerto();
                    vista.appendColor(" Atril Vacío. Se ha tomado el muerto.", ColorRGB.GREEN);
                }
            } else {
                vista.appendColor(" Jugada no válida.", ColorRGB.RED);
            }
        } catch (NumberFormatException e) {
            vista.appendColor("Error: La cadena no es un número válido.", ColorRGB.RED);
        }
    }

    @Override
    public void mostrarSiguienteTexto() {
        vista.mostrarAtril(controlador.mostrarAtril());
        vista.appendColor(" ------------------------------------------------------------------------------\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  0  Volver al menu anterior\n", ColorRGB.CYAN);
        vista.appendColor("\n", ColorRGB.CYAN);
        vista.appendColor("  Por favor seleccione las fichas que desea agregar como jugada,", ColorRGB.CYAN);
        vista.appendColor("  separado por ',' (Ej: '1,3,4,5'): ", ColorRGB.CYAN);
    }

}
