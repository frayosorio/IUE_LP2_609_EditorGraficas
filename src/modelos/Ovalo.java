package modelos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ovalo extends Trazo {

    public Ovalo(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void dibujar(Graphics g, Color color, boolean seleccionado) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(seleccionado ? 4 : 1));
        g2d.drawOval(getXMinimo(), getYMinimo(), getAncho(), getAlto());
    }

    @Override
    public TipoTrazo getTipo() {
        return TipoTrazo.OVALO;
    }

    @Override
    public boolean cercano(int x, int y) {
        int xCentro = (getX1() + getX2()) / 2;
        int yCentro = (getY1() + getY2()) / 2;
        int radioX = Math.abs(getX2() - getX1()) / 2;
        int radioY = Math.abs(getY2() - getY1()) / 2;

        // Ecuación elíptica para calcular la distancia del punto al borde del óvalo
        double ecuacionElipse = Math.pow((x - xCentro) / (double) radioX, 2)
                + Math.pow((y - yCentro) / (double) radioY, 2);

        // Calcular la distancia del punto al borde del óvalo
        double distanciaElipse = Math.abs(1.0 - ecuacionElipse) * Math.max(radioX, radioY);

        return distanciaElipse <= TOLERANCIA;
    }

}
