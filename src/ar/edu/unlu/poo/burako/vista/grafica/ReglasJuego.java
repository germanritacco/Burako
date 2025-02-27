package ar.edu.unlu.poo.burako.vista.grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReglasJuego {
    private JPanel pnlReglas;
    private JButton btnAceptar;
    private JScrollPane scpReglas;
    private JEditorPane edtReglas;

    public ReglasJuego(Component component) {
        JFrame frame = new JFrame("Reglas de Juego");
        frame.setContentPane(pnlReglas);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 600));
        frame.pack();
        frame.setLocationRelativeTo(component); // Centrar en la pantalla

        StringBuilder reglasHTML = new StringBuilder();
        reglasHTML.append("<html>");
        reglasHTML.append("<head><style>");
        reglasHTML.append("body { font-family: Arial, sans-serif; padding: 10px; line-height: 1.5; color: #FFFFFF}");
        reglasHTML.append("h1, h2 { color: #D19A66; }");
        reglasHTML.append("h1 { font-size: 22px; text-align: center; }");
        reglasHTML.append("h2 { font-size: 18px; margin-top: 20px; }");
        reglasHTML.append("ul { padding-left: 20px; }");
        reglasHTML.append("li { margin-bottom: 8px; }");
        reglasHTML.append("strong { color: #E06C75; }");
        reglasHTML.append("</style></head>");
        reglasHTML.append("<body>");

        reglasHTML.append("<h1>Reglas del Juego</h1>");
        reglasHTML.append("<div style='text-align: center;'><img src='file:src/ar/edu/unlu/poo/burako/texture/titulo.png' width='300'></div>");

        reglasHTML.append("<h2>Jugadores y Objetivo</h2>");
        reglasHTML.append("<ul>");
        reglasHTML.append("<li><strong>2 o 4 jugadores</strong> formando parejas.</li>");
        reglasHTML.append("<li>104 fichas numeradas y 2 comodines.</li>");
        reglasHTML.append("<li>Objetivo: En esta version, ser el primero en quedarse <strong>sin fichas en el atril</strong>.</li>");
        reglasHTML.append("</ul>");

        reglasHTML.append("<h2>Preparación</h2>");
        reglasHTML.append("<ul>");
        reglasHTML.append("<li>Las fichas se encuentran mezcladas con la cara hacia abajo.</li>");
        reglasHTML.append("<li>A cada jugador se le entrega <strong>11 fichas</strong> en su atril.</li>");
        reglasHTML.append("<li>Se separan 2 grupos de 11 fichas (muertos), uno por pareja.</li>");
        reglasHTML.append("<li>El resto de fichas forman el <strong>mazo</strong>.</li>");
        reglasHTML.append("</ul>");

        reglasHTML.append("<h2>Juegos</h2>");
        reglasHTML.append("<ul>");
        reglasHTML.append("<li><strong>Escalera:</strong> Mínimo 3 fichas con números consecutivos del mismo color. Ejemplo: 2-3-4-5 rojos.</li>");
        reglasHTML.append("<li><strong>Pierna:</strong> Mínimo 3 fichas del mismo número, sin importar el color. Ejemplo: 3-3-3-3 en diferentes colores.</li>");
        reglasHTML.append("<li><strong>Canasta:</strong> 7 fichas como mínimo en juego de Pierna o Escalera.</li>");
        reglasHTML.append("<ul>");
        reglasHTML.append("<li><strong>Pura:</strong> Sin comodín.</li>");
        reglasHTML.append("<li><strong>Impura:</strong> Con comodín.</li>");
        reglasHTML.append("</ul>");
        reglasHTML.append("</ul>");
        reglasHTML.append("<p>Se puede bajar un juego con 2 fichas y un comodín.</p>");

        reglasHTML.append("<h2>Valor de las Fichas</h2>");
        reglasHTML.append("<ul>");
        reglasHTML.append("<li><strong>Nº1</strong>: 15 puntos</li>");
        reglasHTML.append("<li><strong>N°2</strong>: 20 puntos</li>");
        reglasHTML.append("<li><strong>Nº3 al 7</strong>: 5 puntos</li>");
        reglasHTML.append("<li><strong>Nº8 al 13</strong>: 10 puntos</li>");
        reglasHTML.append("<li><strong>Comodín</strong>: 50 puntos</li>");
        reglasHTML.append("</ul>");

        reglasHTML.append("<h2>Comodines</h2>");
        reglasHTML.append("<p>Además de las 2 fichas con logo, las fichas <strong>N°2</strong> también funcionan como comodines.</p>");
        reglasHTML.append("<p>Los comodines pueden intercambiarse en juegos y ubicarse al inicio o final de una escalera.</p>");
        reglasHTML.append("<p>Los comodines se utilizan para reemplazar en los juegos la ficha correspondiente. Ubicado el comodín en un juego, puede ser cambiado de la siguiente manera:</p>");
        reglasHTML.append("<p><strong>Ejemplo:</strong> si el comodín sustituye en una Escalera al Nº6 y este número aparece en una mano posterior, se puede canjear por el comodín y éste pasa al principio o al final de la Escalera. Cuando un comodín Nº2 se intercambia en un juego y pasa a ocupar el lugar y el color correspondiente, vale para Canasta Pura.</p>");
        reglasHTML.append("<p><strong>Importante:</strong> los comodines pueden ser cambiados de lugar en el juego bajado, como se indicó anteriormente, pero no pueden ser retirados.</p>");
        reglasHTML.append("<h2>Desarrollo del Juego</h2>");
        reglasHTML.append("<p>El jugador que es mano toma una ficha de la pila: puede quedarse con ella o devolverla al pozo. Con la ficha en su poder podrá bajar los juegos formados y luego cederá el turno al siguiente jugador, dejando previamente una ficha cara arriba en el pozo.</p>");
        reglasHTML.append("<p>El jugador de turno puede tomar la ficha del pozo o levantar una de la pila. El jugador que quiera robar del pozo debe tomarlo con todas las fichas depositadas en él. Los jugadores -en su turno- pueden bajar sus juegos o \"apoyar\" los juegos presentados por sus compañeros.</p>");

        reglasHTML.append("<h2>Muerto</h2>");
        reglasHTML.append("<p>El primer jugador de cada equipo que se quede sin fichas deberá comprar el <strong>muerto</strong>:</p>");
        reglasHTML.append("<ul>");
        reglasHTML.append("<li><strong>Compra directa:</strong> El jugador baja todas sus fichas, toma el \"muerto\" y lo juega en el mismo turno, dejando al finalizar una ficha en el pozo.</li>");
        reglasHTML.append("<li><strong>Compra indirecta:</strong> El jugador se queda sin fichas después de colocar en el pozo. Compra el \"muerto\" y podrá jugarlo en el próximo turno.</li>");
        reglasHTML.append("</ul>");

        reglasHTML.append("<h2>Final</h2>");
        reglasHTML.append("<p>Para cerrar la partida, una pareja debe haber realizado al menos una <strong>Canasta</strong> y haber comprado el <strong>muerto</strong>.</p>");

        reglasHTML.append("<h2>Puntaje</h2>");
        reglasHTML.append("<ul>");
        reglasHTML.append("<li><strong>100 puntos</strong> por cierre.</li>");
        reglasHTML.append("<li><strong>100 puntos</strong> por Canasta Impura.</li>");
        reglasHTML.append("<li><strong>200 puntos</strong> por Canasta Pura.</li>");
        reglasHTML.append("<li><strong>100 puntos</strong> por comprar el muerto.</li>");
        reglasHTML.append("<li><strong>-100 puntos</strong> si no se puede comprar el muerto.</li>");
        reglasHTML.append("<li>A esto se le sumará el puntaje de todas las fichas bajadas y se le descontarán las fichas no bajadas del compañero.</li>");
        reglasHTML.append("</ul>");

        reglasHTML.append("<h2>Ganador</h2>");
        reglasHTML.append("<p>El equipo que llega primero a quedarse <strong>sin fichas para jugar</strong> gana la partida.</p>");

        reglasHTML.append("<div style='text-align: center; font-size: 16px; font-weight: bold; color: #98C379; margin-top: 20px;'>");
        reglasHTML.append("¡A jugar!");
        reglasHTML.append("</div>");

        reglasHTML.append("</body></html>");

        edtReglas.setText(reglasHTML.toString());
        edtReglas.setCaretPosition(0);
        edtReglas.setEditable(false);
        frame.setVisible(true);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

}


