import java.awt.Graphics;
import java.util.List;
import java.awt.Color;

import javax.swing.JPanel;

import com.fasterxml.jackson.core.type.TypeReference;

import modelos.Linea;
import modelos.Nodo;
import modelos.Ovalo;
import modelos.Rectangulo;
import modelos.Trazo;
import modelos.TrazoDTO;

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

    public void desdeJSON(String nombreArchivo) {
        cabeza = null;
        var trazos = Archivo.leerJson(nombreArchivo, new TypeReference<List<TrazoDTO>>() {
        });
        for (var trazoDTO : trazos) {
            Trazo trazo = null;
            switch (TipoTrazo.valueOf(trazoDTO.getTipo())) {
                case LINEA:
                    trazo = new Linea(trazoDTO.getX1(), trazoDTO.getY1(), trazoDTO.getX2(), trazoDTO.getY2());
                    break;
                case RECTANGULO:
                    trazo = new Rectangulo(trazoDTO.getX1(), trazoDTO.getY1(), trazoDTO.getX2(), trazoDTO.getY2());
                    break;
                case OVALO:
                    trazo = new Ovalo(trazoDTO.getX1(), trazoDTO.getY1(), trazoDTO.getX2(), trazoDTO.getY2());
                    break;
            }
            agregar(trazo, new Color(trazoDTO.getRed(), trazoDTO.getGreen(), trazoDTO.getBlue()));
        }
    }

    // ********** Metodos Estaticos **********

    public static void limpiarPanel(JPanel pnl) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, pnl.getWidth(), pnl.getHeight());
    }

}
