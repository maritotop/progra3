import Lista.Lista;
import Lista.Pila;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.io.*;

public class importar extends JFrame implements ActionListener {
    private Logger logger = LogManager.getRootLogger();

    //CREACION DE BOTONES - FILES
    JButton btnImportar, btnSalir, btnEliminar;
    DefaultTableModel registro;
    JTable tabla;
    File archivo;
    FileReader fr;
    BufferedReader bf;
    private Pila _pilaDeFilas = new Pila<Lista<String[]>>();

    public importar() {
        setLayout(null);
        registro = new DefaultTableModel();
        logger.info("Se crea la tabla");
        registro.addColumn("Nombre");
        registro.addColumn("Apellido");
        registro.addColumn("Edad");
        logger.info("Se crean las columnas");
        tabla = new JTable(registro);
        //Creacion de scroll
        JScrollPane scroll = new JScrollPane(tabla);
        // Tabla y scrolls tamaños
        tabla.setBounds(250, 20, 250, 500);
        scroll.setBounds(250, 20, 250, 500);
        add(scroll);
        //Agregacion de botones
        btnImportar = new JButton("Importar");
        btnImportar.setBounds(100, 50, 100, 30);
        btnImportar.addActionListener(this);
        add(btnImportar);
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(100, 600, 100, 30);
        btnSalir.addActionListener(this);
        add(btnSalir);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(100, 90, 100, 30);
        btnEliminar.addActionListener(this);
        add(btnEliminar);
        logger.info("creacion de componentes completada, proceda a importar ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnImportar) {
            archivo = new File("C:\\pro3\\pro3.txt");
            try {
                logger.info("importacion con exito");
                logger.info("leyendo las lineas ");
                fr = new FileReader(archivo);
                // se coloca en el buffered reader
                BufferedReader bf = new BufferedReader(fr);
                Object[] lineas = bf.lines().toArray();
                for (int i = 0; i < lineas.length; i++) {
                    logger.info("leyendo la linea: "+ i );
                    //Obtenemos la informacion de la linea (Nombre apellido y edad)
                    String[] personaInfo = lineas[i].toString().split(" ");
                    //añadimos la informacion a la pila de filas
                    _pilaDeFilas.push(personaInfo);
                    //obtenemos el registro de la pila de fila y se añade al registro del JList
                    registro.addRow((Object[])_pilaDeFilas.pop());
                    logger.info("se agrego la fila ");
                    bf.close();
                    fr.close();
                }
            } catch (Exception ex) {

            }
        }
        if (e.getSource() == btnSalir) {
            System.exit(0);
        }
        if (e.getSource() == btnEliminar) {
            int fila = tabla.getSelectedRow();
            if (fila >= 0) {
                registro.removeRow(fila);
                logger.info("se elimino el elemento ");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccionar fila");
                logger.info("se intento eliminar algo no seleccionado");
            }
        }

    }
}
