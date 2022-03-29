import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class LinesComponent extends JComponent {
    private Logger logger = LogManager.getRootLogger();
    public LinkedList<Linea> lineas = new LinkedList<>();

    private Color colorLineas = new Color(246, 0, 0);

    private int staticY = -200;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(colorLineas);
        for (Linea linea : lineas) {
            g.setColor(colorLineas);
            // Todo: verificar de pintar bien la linea
            g.drawLine(linea.getPosicion(), staticY, linea.getPosicion(), linea.getAlto());
            logger.info("Se pinto la linea");

        }
    }

    public LinkedList<Linea> getLineas() {
        return lineas;
    }

    public void addLinea(Linea linea) {
        this.lineas.add(linea);

        // Todo: reemplazar el if/else por el ".notify" del observer
        if (isOrdenado()) {
            repaint();
            logger.info("las lineas estan ordenadas ");
        } else {
            // No esta ordenado
            logger.error("las lineas no estan ordenadas, ordenelas");
        }
    }

    // Esta funcion se tiene que llamar cada que se inserte una linea
    // Todo: (colocar en el observer)
    private boolean isOrdenado() {
        int anterior = 0;
        for (int i = 0; i < this.lineas.size(); i++) {
            if (anterior > this.lineas.get(i).getAlto()) {
                return false;
            }
            anterior = this.lineas.get(i).getAlto();
        }
        return true;
    }
}
