package modelos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Linea extends Trazo {

    public Linea(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void dibujar(Graphics g, Color color, boolean seleccionado) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(seleccionado ? 4 : 1));
        g2d.drawLine(getX1(), getY1(), getX2(), getY2());
    }

    @Override
    public TipoTrazo getTipo() {
        return TipoTrazo.LINEA;
    }

    @Override
    public boolean cercano(int x, int y) {
        return esCercanoALinea(x, y, getX1(), getY1(), getX2(), getY2());
    }

}
