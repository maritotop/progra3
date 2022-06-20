/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import arbol.Arbol;
import arbol.ArbolCarpeta;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author DOCENTE-302
 */
public class ArbolMenuContextual extends JPopupMenu {

    private Arbol<ArbolCarpeta> modelo;
    private String idNodo;

    public ArbolMenuContextual(Arbol<ArbolCarpeta> m, String id) {
        modelo = m;
        idNodo = id;

        initMenu(idNodo == null);
    }

    private void initMenu(boolean vacio) {
        JMenuItem item = null;

        /*if (!vacio) {
            item = new JMenuItem("Eliminar nodo");

            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menu_eliminarNodo();
                }
            });

            add(item);*/

//            item = new JMenuItem("Editar nodo");
//
//            item.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    menu_editarNodo();
//                }
//            });

            /*add(item);
        }*/

        item = new JMenuItem("Nuevo nodo");

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_nuevoNodo();
            }
        });

        add(item);

    }

    /*public void menu_eliminarNodo() {
        modelo.eliminar(idNodo);

    }*/
    private void menu_nuevoNodo()  {
        JFileChooser file = new JFileChooser("C:");
        if (file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            File archivo = file.getSelectedFile();
            String path= archivo.getAbsolutePath();
            String nombre= archivo.getName().substring(path.lastIndexOf(":")-1);

            String extension = archivo.getName();
            int PosicionDelPunto = extension.indexOf(".");
            String sDesdePrimerPunto = extension.substring(PosicionDelPunto+1,extension.length()) ;
            Long size= archivo.length();
            //CREAR ID RANDOM
            String banco = "abcdefghijklmnopqrstuvwxyz01234567890";
            String idRandom= "";

            for (int x=0; x <=9; x++){
                Random rand = new Random();
                int indiceAleatorio = rand.nextInt( banco.length()-1);
                char caraceterAleatorio =banco.charAt(indiceAleatorio);
                idRandom+=caraceterAleatorio;
            }

            ArbolCarpeta j=new ArbolCarpeta(idRandom,nombre,sDesdePrimerPunto,size.intValue());

            try {
                modelo.insertar(j.getId(),
                        j, idNodo);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No pudo leer archivo");
            }
        }

    }
    /*public void menu_nuevoNodo() {
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JTextField field3 = new JTextField();
        JTextField field4 = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Id:"));
        panel.add(field1);
        panel.add(new JLabel("Nombre:"));
        panel.add(field2);
        panel.add(new JLabel("Tipo"));
        panel.add(field3);
        panel.add(new JLabel("Tamano"));
        panel.add(field4);

        int result = JOptionPane.showConfirmDialog(null, panel, "Nuevo Nodo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            ArbolCarpeta j=new ArbolCarpeta(field1.getText(),field2.getText(),field3.getText(),Integer.parseInt(field4.getText()));
            modelo.insertar(field1.getText(),
                    j, idNodo);
        }
    }*/

//    public void menu_editarNodo() {
//
//        JTextField field2 = new JTextField(modelo.getById(idNodo));
//        JPanel panel = new JPanel(new GridLayout(0, 1));
//
//        panel.add(new JLabel("Nombre:"));
//        panel.add(field2);
//
//        int result = JOptionPane.showConfirmDialog(null, panel, "Editar Nodo",
//                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//        if (result == JOptionPane.OK_OPTION) {
//            modelo.actualizar(idNodo,
//                    field2.getText());
//        }
//
//    }
}
