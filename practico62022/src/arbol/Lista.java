/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

import java.util.Iterator;

public class Lista<E> implements Iterable<E> {

    /**
     * El nodo incial, puede ser nulo
     */
    protected Nodo<E> inicio;
    
    protected int tamaño;

    /**
     * El constructor no necesita <b>NADA</b>
     */
    public Lista() {
        inicio = null;
        tamaño = 0;
    }

    /**
     * Con este metodo se inserta un elemento en la lista. La
     * insercion siempre se realiza al inicio.
     * @param o Un objeto del tipo de la lista
     */
    public void insertar(E o) {
        Nodo<E> n;
        n = new Nodo(o);
        n.setSiguiente(inicio);
        inicio = n; //2
        tamaño++;
    }
    
    public void eliminar(E o) {
        if (o == inicio.getContenido()) {
            inicio = inicio.getSiguiente();
            return;
        }
        Nodo<E> actual = inicio;
        while (actual != null &&
                o != actual.getSiguiente().getContenido()) {
            actual = actual.getSiguiente();
        }

        if (actual == null) {
            throw new IndexOutOfBoundsException("El indice sobrepasa el tamano del arreglo");

        }
        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        tamaño--;
    }

    /**
     * Para eliminar un elemento de la lista. Da error cuando 
     * nos pasamos del tamano de la lista.
     * No se esta validando si la posicion es positiva solamente.
     * El procedimiento es:
     * <ul>
     * <li>Movernos hasta la posicion - 1</li>
     * <li>El siguiente de actual va al siguiente del siguiente</li>
     * </ul>
     * @param posicion 
     */
    public void eliminar(int posicion) {
        if (posicion == 0) {
            inicio = inicio.getSiguiente();
            tamaño--;
            return;
        }
        Nodo<E> actual = inicio;
        int pos = 0;
        while (pos < posicion - 1 && actual != null) {
            actual = actual.getSiguiente();
            pos++;
        }

        if (actual == null) {
            throw new IndexOutOfBoundsException("El indice sobrepasa el tamano del arreglo");

        }
        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        tamaño--;
    }

    /**
     * Obtiene el elemento en la posicion indicada
     * @param posicion
     * @return 
     */
    public E get(int posicion) {
        Nodo<E> actual = inicio;
        int pos = 0;
        while (pos < posicion && actual != null) {
            actual = actual.getSiguiente();
            pos++;
        }

        if (actual == null) {
            throw new IndexOutOfBoundsException("El indice sobrepasa el tamano del arreglo");
        }

        return actual.getContenido();
    }

    /**
     * Nos da el tamano de la lsita. Para ello <i>recorre</i>
     * toda la lista
     * @return Un entero
     */
    public int getTamaño() {
//        Nodo<E> actual = inicio;
//        int pos = 0;
//        while (actual != null) {
//            actual = actual.getSiguiente();
//            pos++;
//        }
//
//        return pos;
        return tamaño;
    }

    @Override
    public String toString() {
        Nodo<E> actual = inicio;
        StringBuilder result = new StringBuilder();
        String separador = "";
        while (actual != null) {
            result.append(separador).append(actual.getContenido());
            actual = actual.getSiguiente();
            separador = " -> ";
        }

        return result.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new ListaIterator(inicio);
    }
    
    
    public E buscar(E n) {
        int nroTests = 0;
        
        E resultado = null;
//        
//        Iterator<E> iter = this.iterator();
//        while(iter.hasNext()) {
//            E obj = iter.next();
//            
//            nroTests++;
//            if (n.toString().equals(obj.toString())) {
//                resultado = obj;
//                break;
//            }
//        }
        
        for (E obj : this) {
            nroTests++;
            if (n.toString().equals(obj.toString())) {
                resultado = obj;
                break;
            }
        }
        System.out.println("Nro if: " + nroTests);
        return resultado;
    }

    /**
     * Implementacion del patron de diseno iterator
     * @param <E> 
     */
    class ListaIterator<E> implements Iterator<E> {

        private Nodo<E> actual;

        private ListaIterator(Nodo<E> inicio) {
            actual = inicio;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public E next() {
            E obj = actual.getContenido();
            actual = actual.getSiguiente();
            return obj;
        }

    }

    /**
     * Implementacion del nodo de la lista
     * @param <E> 
     */
    class Nodo<E> {

        private E contenido;
        private Nodo<E> siguiente;

        public Nodo(E o) {
            contenido = o;
            siguiente = null;
        }

        public E getContenido() {
            return contenido;
        }

        public void setContenido(E contenido) {
            this.contenido = contenido;
        }

        public Nodo<E> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }
    }
}
