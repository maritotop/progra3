/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;

public class Arbol<E> extends Observable {

    private Nodo<E> raiz;

    private HashMap<String, Nodo<E>> nodos;

    public Arbol() {
        nodos = new HashMap<>();
    }

    public Arbol(File f, LectorNodo lector) throws FileNotFoundException, IOException {
        
        leerDeArchivo(f, lector);
    }
    
    public void leerDeArchivo(File f, LectorNodo lector) throws FileNotFoundException, IOException {
        nodos = new HashMap<>();
        raiz = null;
        
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String linea = "";
        while (br.ready()) {
            linea = br.readLine();
            if (linea.isEmpty())
                continue;
            
            String[] datos = linea.split("-");
            
            String id = datos[0];
            
            String idPadre = null;
            if (datos.length == 3)
                idPadre = datos[2];
            E c = (E) lector.crearObjeto(datos[1]);
            
            this.insertar(id, c, idPadre);
        }
        br.close();
        fr.close();
    }

    public void insertar(String id, E c, String idPadre) {

        Nodo<E> nuevo = new Nodo(id, c);

        if (idPadre == null) {
            raiz = nuevo;
            nodos.put(id, raiz);
            this.setChanged();
            this.notifyObservers();
            return;
        }

        Nodo<E> padre = nodos.get(idPadre);

        padre.getHijos().insertar(nuevo);
        nuevo.setPadre(padre);
        nodos.put(id, nuevo);

        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public String toString() {
        return raiz.toString();
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<E> raiz) {
        this.raiz = raiz;
    }

    public E getById(String id) {
        if (nodos.containsKey(id)) {
            return nodos.get(id).getContenido();
        }

        return null;
    }

    public void eliminar(String idNodo) {
        if (!nodos.containsKey(idNodo)) {
            // log, exception
            return;
        }

        Nodo<E> objNodo = nodos.get(idNodo);
        if (objNodo == raiz) {
            raiz = null;
            this.setChanged();
            this.notifyObservers();
            return;
        }

        Nodo<E> padre = objNodo.getPadre();
        padre.getHijos().eliminar(objNodo);

        this.setChanged();
        this.notifyObservers();
    }

    public void actualizar(String idNodo, E o) {
        nodos.get(idNodo).setContenido(o);
        this.setChanged();
        this.notifyObservers();

    }

    class Nodo<E> {

        private E contenido;
        private final Lista<Nodo<E>> hijos;
        private final String id;
        private Nodo<E> padre;

        public Nodo(String id, E c) {
            contenido = c;
            hijos = new Lista<>();
            this.id = id;
            this.padre = null;
        }

        public String toString() {
            StringBuilder result = new StringBuilder();

            result.append(id);

            if (hijos.getTamaño() > 0) {
                result.append("(");
                String separador = "";
                for (Nodo<E> objHijo : hijos) {
                    result.append(separador);
                    result.append(objHijo);
                    separador = " ";
                }
                result.append(")");
            }

            return result.toString();
        }

        public E getContenido() {
            return contenido;
        }

        public void setContenido(E contenido) {
            this.contenido = contenido;
        }

        public Lista<Nodo<E>> getHijos() {
            return hijos;
        }

        public String getId() {
            return id;
        }

        public Nodo<E> getPadre() {
            return padre;
        }

        public void setPadre(Nodo<E> padre) {
            this.padre = padre;
        }

    }
}
