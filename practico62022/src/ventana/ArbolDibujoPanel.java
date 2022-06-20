/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import arbol.Arbol;
import arbol.ArbolCarpeta;
import arbol.DibujoArbol;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import modelo.ModeloArbol;

public class ArbolDibujoPanel extends JPanel implements MouseListener, Observer {

    private DibujoArbol<ArbolCarpeta> dibujo;
    private JLabel label = new JLabel();
    //private Arbol<String> modelo;
    private static ArbolCarpeta ac;
    public ArbolDibujoPanel() {
        //label.setText();
        this.addMouseListener(this);
    }
    public ArbolDibujoPanel(Arbol<ArbolCarpeta> a) {
        //modelo = a;
        this.addMouseListener(this);
    }
    public void setArbol(ModeloArbol modelo) {
        modelo.addObserver(this);
    }

    public static ArbolCarpeta getArch(ArbolCarpeta ac) {

        ModeloArbol modelo = ModeloArbol.getSingleton();
        String idNodo = null;
        if (idNodo == null) {
            if (modelo.getRaiz() == null) {
                ArbolMenuContextual menu = new ArbolMenuContextual(modelo, null);
                modelo.insertar(ac.getId(),
                        ac, idNodo);
            }

        }

        return ac;
    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ModeloArbol modelo = ModeloArbol.getSingleton();
        dibujo = new DibujoArbol(modelo);
        dibujo.dibujar(g, 10, 10);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        String idNodo = dibujo.getNodoEnPunto(x, y);
        
        ModeloArbol modelo = ModeloArbol.getSingleton();
        
        if (idNodo == null) {
            if (modelo.getRaiz() == null) {
                ArbolMenuContextual menu = new ArbolMenuContextual(modelo, null);
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
            return;
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            ArbolMenuContextual menu = new ArbolMenuContextual(modelo, idNodo);
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
        if (e.getButton() == MouseEvent.BUTTON1) {
            String nombre = modelo.getById(idNodo).toString();
            JOptionPane.showMessageDialog(this, nombre);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ;
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
