package modelo;

import arbol.Arbol;
import arbol.ArbolCarpeta;

public class ModeloArbol extends Arbol<ArbolCarpeta> {

    private static ModeloArbol singleton = null;

    private ModeloArbol() {

    }

    public static ModeloArbol getSingleton() {
        if (singleton == null)
            singleton = new ModeloArbol();

        return singleton;
    }
}