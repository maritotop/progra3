import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class Primero {
    private Logger logger = LogManager.getRootLogger();

    public static void main(String[] args) {
        JFrame frame = new Ventana();
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
