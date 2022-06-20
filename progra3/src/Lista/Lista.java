
package Lista;

import java.util.Iterator;

public class Lista<T> implements Iterable<T> {

    protected Nodo<T> inicio;
    protected Nodo<T> ultimo;

    public Lista() {
        inicio = null;
    }

    public void adicionar(T o) {

    }

    public int getTamano() {
        int contador = 0;

        Nodo<T> actual = inicio;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }

        return contador;
    }

    public void eliminar(int posicion) {

        if (inicio == null) {
            return;
        }

        if (posicion == 0) {
            inicio = inicio.getSiguiente();
            return;
        }

        int posActual = 1;
        Nodo<T> actual = inicio;
        while (posActual < posicion && actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
            posActual++;
        }

        if (posActual != posicion) {
            return;
        }

        actual.setSiguiente(actual.getSiguiente().getSiguiente());
    }

    public void insertar(T o) {

        Nodo<T> nuevo = new Nodo(o);

        if (inicio == null) {
            inicio = nuevo;
            return;
        }

        nuevo.setSiguiente(inicio);
        inicio = nuevo;
    }

    public String toString() {
        if (inicio == null) {
            return "[VACIA]";
        }

        StringBuilder resultado = new StringBuilder();
        resultado.append("[Lista de " + getTamano() + " nodos]: ");

        Nodo<T> actual = inicio;
        while (actual != null) {
            resultado.append(actual.getContenido());
            resultado.append(" -> ");

            actual = actual.getSiguiente();
        }

        return resultado.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorNodo(inicio);
    }

    int getTamano(int medio) {

        return inicio.hashCode(medio);
    }

//    public void insertarIMG(BufferedImage bi) {
//        System.out.println("Entra a la lista");
//        Nodo<T> nuevo = new Nodo(bi);
//
//        if (ultimo == null) {
//            ultimo = nuevo;
//            inicio = nuevo;
//            return;
//        }
//
//        nuevo.setAnterior(ultimo);
//        ultimo.setSiguiente(nuevo);
//        ultimo = nuevo;
//    }

//    public void insertarIMG2(BufferedImage bi) {
//
//        System.out.println("entro a la lista");
//        Nodo<T> nuevo = new Nodo(bi);
//
//        if (inicio == null) {
//            inicio = nuevo;
//            return;
//        }
//        Nodo<T> actual = inicio;
//        Nodo<T> anterior = null;
//        Comparable<BufferedImage> oc = (Comparable<BufferedImage>) bi;
//        while (actual != null && oc.compareTo((BufferedImage) actual.getContenido()) > 0) {
//            anterior = actual;
//            actual = actual.getSiguiente();
//        }
//        nuevo.setSiguiente(actual);
//        if (anterior != null) {
//            anterior.setSiguiente(nuevo);
//        } else {
//            inicio = nuevo;
//        }
//    }
//
//    public BufferedImage anterior() {
//        System.out.println("entro a anterior");
//        inicio.getAnterior();
//        return (BufferedImage) inicio.contenido;
//    }

    static class Nodo<T> {

        private T contenido;
        private Nodo<T> siguiente;
        private Nodo<T> anterior;

        public Nodo(T c) {
            contenido = c;
            siguiente = null;
            anterior = null;
        }

        public T getContenido() {
            return contenido;
        }

        public void setContenido(T contenido) {
            this.contenido = contenido;
        }

        public Nodo<T> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<T> siguiente) {
            this.siguiente = siguiente;
        }

        public Nodo<T> getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo<T> anterior) {
            this.anterior = anterior;
        }

        private int hashCode(int medio) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    static class IteradorNodo<T> implements Iterator<T> {

        private Nodo<T> actual;

        public IteradorNodo(Nodo<T> n) {
            actual = n;
        }

        @Override
        public boolean hasNext() {
            return (actual != null);
        }

        @Override
        public T next() {
            T objActual = actual.getContenido();
            actual = actual.getSiguiente();

            return objActual;
        }

    }
}
