
package arbol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;

public class DibujoArbol<E> implements Dibujable {

    private final int ESPACIO_VERTICAL_NODO = 50;
    private final int ESPACIO_HORIZONTAL_NODO = 15;
    private final int ANCHO_NODO = 30;

    private Arbol<E> modelo;
    
    private HashMap<String,Point> posiciones;

    public DibujoArbol(Arbol<E> modelo) {
        this.modelo = modelo;
        posiciones = new HashMap<>();
    }

    @Override
    public void dibujar(Graphics g, int x, int y) {
        if (modelo.getRaiz() == null) {
            g.drawString("ARBOL VACIO!!", x + 10, y + 10);
            return;
        }

        dibujar(modelo.getRaiz(), g, x, y);
    }

    private void dibujar(Arbol<E>.Nodo<E> n, Graphics g, int x, int y) {

        if (n.getHijos().getTamaño() == 0) {
            dibujarNodo(n, g, x, y);
            return;
        }
        int ancho = getAncho(n);
        int xNodo = x;
        int yNodo = y + ESPACIO_VERTICAL_NODO;

        for (Arbol<E>.Nodo<E> hijo : n.getHijos()) {
            int anchoHijo = getAncho(hijo);

            g.drawLine(x + ancho / 2, y + ANCHO_NODO / 2,
                    xNodo + anchoHijo / 2, yNodo + ANCHO_NODO / 2);

            dibujar(hijo, g, xNodo, yNodo);

            xNodo += (anchoHijo + ESPACIO_HORIZONTAL_NODO);

        }

        dibujarNodo(n, g, x + ancho / 2 - ANCHO_NODO / 2, y);
    }

    private void dibujarNodo(Arbol<E>.Nodo<E> n, Graphics g, int x, int y) {
        g.setColor(Color.white);
        g.fillOval(x, y, ANCHO_NODO, ANCHO_NODO);

        g.setColor(Color.black);
        g.drawOval(x, y, ANCHO_NODO, ANCHO_NODO);
        g.drawString(n.getId(), x + 6, y + 20);
        
        Point p = new Point(x, y);
        posiciones.put(n.getId(), p);
    }

    private int getAncho(Arbol<E>.Nodo<E> n) {
        if (n.getHijos().getTamaño() == 0) {
            return ANCHO_NODO;
        }

        int result = 0;
        int separador = 0;
        for (Arbol<E>.Nodo<E> hijo : n.getHijos()) {
            result += (separador + getAncho(hijo));
            separador = ESPACIO_HORIZONTAL_NODO;
        }

        return result;
    }

    public String getNodoEnPunto(int x, int y) {
        
        for(String idNodo : posiciones.keySet()) {
            Point p = posiciones.get(idNodo);
            
            double x0 = p.getX() + ANCHO_NODO/2;
            double y0 = p.getY() + ANCHO_NODO/2;
            
            double sqrX = Math.pow(x - x0, 2);
            double sqrY = Math.pow(y - y0, 2);
            double sqrR = Math.pow(ANCHO_NODO/2,2);            
            
            if (sqrX + sqrY < sqrR)
                return idNodo;
            
        }
        
        return null;
    }

}
