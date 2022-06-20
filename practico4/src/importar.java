import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.io.*;

public class importar extends JFrame implements ActionListener {
    JButton btnImportar, btnSalir, btnEliminar;
    DefaultTableModel registro;
    JTable tabla;
    File archivo;
    FileReader fr;
    BufferedReader bf;

    public importar() {
        setLayout(null);
        //Creacion de la tabla
        registro = new DefaultTableModel();
        // creacion de columnas
        registro.addColumn("Nombre");
        registro.addColumn("Apellido");
        registro.addColumn("Edad");
        //Agregacion de columnas a tablas
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnImportar) {
            archivo = new File("C:\\pro3\\pro3.txt");
            try {
                // se lee linea por linea
                fr = new FileReader(archivo);
                // se coloca en el buffered reader
                BufferedReader bf = new BufferedReader(fr);
                Object[] linea = bf.lines().toArray();
                for (int i = 0; i < linea.length; i++) {
                    String[] fila = linea[i].toString().split(" ");
                    registro.addRow(fila);
                    bf.close();
                    fr.close();
                }
            } catch (Exception ex) {

            }
        }
        if (e.getSource() == btnSalir) {
            System.exit(0);
        }
        if (e.getSource() == btnEliminar ){
            int fila =tabla.getSelectedRow();
            if (fila>= 0){
                registro.removeRow(fila);
            }
            else{
                JOptionPane.showMessageDialog(null,  "Seleccionar fila");
            }
        }

    }
}
