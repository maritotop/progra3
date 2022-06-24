package arbol;

public class ArbolCarpeta {
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTamano() {
        return tamano;
    }

    public ArbolCarpeta(String id, String nombre, String tipo, int tamano) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tamano = tamano;
    }

    private final String id;
    private final String nombre;
    private final String tipo;
    private final int tamano;



    @Override
    public String toString() {
        return "ArbolCarpeta{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", tamano=" + tamano +
                '}';
    }

    public void cargar(String archivo) throws Exception {
    }
}
