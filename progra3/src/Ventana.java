import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class Ventana extends JFrame {

    public LinesComponent lineasComp = new LinesComponent();

    private JButton insertarNumeroButton;
    private JPanel panel1;
    private Logger logger = LogManager.getRootLogger();


    public Ventana() {
        super("Principal");
        lineasComp.setPreferredSize(new Dimension(200, 200));
        lineasComp.setBackground(new Color(0,255,0));
        panel1.add(lineasComp, BorderLayout.CENTER);
        panel1.setBackground(new Color(0,0,255));
        setContentPane(panel1);

        //boton de introducir numero
        insertarNumeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //se guarda el numero y la posicion al ser presionado el boton
                String numeroIntroducido;
                String posicionIntrudocida;
                numeroIntroducido = JOptionPane.showInputDialog("Introduce el numero(altura de la linea)");
                posicionIntrudocida = JOptionPane.showInputDialog("Introduce la  posicion en x");

                if (numeroIntroducido.length() == 0 || posicionIntrudocida.length() == 0) {
                    logger.error("Ingrese valores por favor");
                    return;

                }

                int alto = Integer.parseInt(numeroIntroducido);
                int posicion = Integer.parseInt(posicionIntrudocida);
                if (alto >= 0 && alto < 100 && posicion >= 0 && posicion < 100) {
                    logger.info("posicion: " + posicion);
                    logger.info("alto: " + alto);
                    lineasComp.addLinea(new Linea(posicion, alto));

                } else {
                    logger.error("Los valores deben ser mayores o iguales 0 y menores a 100");
                }
            }
        });
    }
}
