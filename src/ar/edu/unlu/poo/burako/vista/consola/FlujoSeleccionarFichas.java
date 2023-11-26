package ar.edu.unlu.poo.burako.vista.consola;

import ar.edu.unlu.poo.burako.controlador.Controlador;

import java.awt.*;

public class FlujoSeleccionarFichas extends Flujo {

    private boolean juegoMesa;

    public FlujoSeleccionarFichas(VistaConsola vista, Controlador controlador, boolean isJuegoMesa) {
        super(vista, controlador);
        juegoMesa = isJuegoMesa;
    }

    /**
     * @param string
     * @return
     */
    @Override
    public Flujo procesarEntrada(String string) {
        try {
            if (string == null) {
                throw new NullPointerException("La cadena de entrada no puede ser nula");
            }
            String[] seleccion = string.split(",");
            if (juegoMesa) {
                return new FlujoAgregarFichaJuegoMesa(vista, controlador, seleccion);

            } else {
                return new FlujoBajarJuego(vista, controlador, seleccion);
            }
        } catch (NullPointerException e) {
            vista.appendColor("Error: " + e.getMessage(), Color.RED);
        } catch (NumberFormatException e) {
            vista.appendColor("Error: La cadena no es un número válido.", Color.RED);
        }
        return this;
    }

    /**
     *
     */
    @Override
    public void mostrarSiguienteTexto() {
        vista.appendColor(" ------------------------------------------------------------------------------\n", Color.CYAN);
        vista.appendColor("\n", Color.CYAN);
        vista.appendColor("  Por favor seleccione las fichas que desea agregar como jugada,", Color.CYAN);
        vista.appendColor("  separado por ',' (Ej: '1,3,4,5'): ", Color.CYAN);

    }
}
