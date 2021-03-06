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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Arbol<E>  {

    private Nodo<E> raiz;

    private HashMap<String, Nodo<E>> nodos;

    public Arbol() {
        nodos = new HashMap<>();
    }


    

    public void insertar(String id, E c, String idPadre) {

        Nodo<E> nuevo = new Nodo(id, c);

        if (idPadre == null) {
            raiz = nuevo;
            nodos.put(id, raiz);
            return;
        }

        Nodo<E> padre = nodos.get(idPadre);

        padre.getHijos().insertar(nuevo);
        nuevo.setPadre(padre);
        nodos.put(id, nuevo);
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


    public List<E> getHijoById(String id) {
        if (nodos.containsKey(id)) {
            return  nodos.get(id).getHijo();
        }

        return null;
    }

    public E getPadreById(String id) {
        if (nodos.containsKey(id)) {
            return  nodos.get(id).getPadre().getContenido();
        }

        return null;
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

            if (hijos.getTama??o() > 0) {
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

        public List<E> getHijo() {
            Lista<Nodo<E>> g=hijos;
            List<E> val=new ArrayList<>();
            int to=g.getTama??o();
            for (int i=0; i<to; i++){
                val.add(g.get(i).getContenido());
            }

            return val;
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
