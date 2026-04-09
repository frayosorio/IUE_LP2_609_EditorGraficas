import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;

import modelos.Nodo;
import modelos.Trazo;

public class Dibujo {

    // ********** Variables y Metodos de Clase **********

    private Nodo cabeza;

    public Dibujo() {
        cabeza = null;
    }

    public void agregar(Trazo trazo, Color color) {
        var nodo = new Nodo(trazo, color);
        if (cabeza == null) {
            cabeza = nodo;
        } else {
            // recorrer la lista hasta el ultimo
            var actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nodo;
        }
    }

    public void dibujar(JPanel pnl) {
        limpiarPanel(pnl);
        // recorrer toda la lista
        var actual = cabeza;
        while (actual != null) {
            actual.getTrazo().dibujar(pnl.getGraphics(), actual.getColor());
            actual = actual.siguiente;
        }
    }

    // ********** Metodos Estaticos **********

    public static void limpiarPanel(JPanel pnl) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, pnl.getWidth(), pnl.getHeight());
    }

}
