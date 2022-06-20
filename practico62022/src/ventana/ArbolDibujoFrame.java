/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import arbol.Arbol;
import arbol.ArbolCarpeta;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import javax.swing.*;

import com.sun.glass.events.KeyEvent;

public class ArbolDibujoFrame extends JFrame {

    private ArbolDibujoPanel panel;

    public ArbolDibujoFrame() {
        this.getContentPane().setLayout(new BorderLayout());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Arbol<String> a = new Arbol();
      /*  inicializar(a);
        panel = new ArbolDibujoPanel(a);*/
        panel = new ArbolDibujoPanel();
        this.getContentPane().add(panel, BorderLayout.CENTER);
        /*try {
            leerDeArchivo("C:\\Users\\DOCENTE-302\\Downloads\\arbol2.txt");

            panel = new ArbolDibujoPanel();

            this.getContentPane().add(panel, BorderLayout.CENTER);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error en archivo");
        }*/
        JMenuBar mbar = new JMenuBar();

        // Archivo
        JMenu m = new JMenu("Archivo");

        JMenuItem mi = new JMenuItem("Leer archivo", KeyEvent.VK_A);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerDeArchivo();
            }

        });
        m.add(mi);
        JMenuItem mo = new JMenuItem("Crear carpeta", KeyEvent.VK_A);
        mo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCarpeta();
            }

        });
        m.add(mo);
        mbar.add(m);

        this.setJMenuBar(mbar);
        this.pack();
    }



    public static void main(String[] args) {
        ArbolDibujoFrame f = new ArbolDibujoFrame();
        f.setVisible(true);
    }

    private void leerDeArchivo()  {
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

            ArbolCarpeta v=new ArbolCarpeta(idRandom,nombre,sDesdePrimerPunto,size.intValue());

            try {
                ArbolDibujoPanel.getArch(v);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No pudo leer archivo");
            }
        }

    }
    private void crearCarpeta()  {
        JFileChooser file = new JFileChooser("C:");
        if (file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            File archivo = file.getSelectedFile();
            String path= archivo.getAbsolutePath();
            String nombre= archivo.getName().substring(path.lastIndexOf(":")-1);

            String extension = archivo.getName();
            int PosicionDelPunto = extension.indexOf(".");
            String sDesdePrimerPunto = extension.substring(PosicionDelPunto+1,extension.length()) ;
            Long size= archivo.length();


            try {
               // ArbolDibujoPanel.getArch();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No pudo leer archivo");
            }
        }

    }
}
