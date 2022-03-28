public class Linea {
    private int posicion;
    private int alto;

    public Linea(int posicion, int alto) {
        this.posicion = posicion;
        this.alto = alto;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}
