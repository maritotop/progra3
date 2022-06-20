
package Lista;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pila<E> extends Lista<E> {

    private static final Logger log = LogManager.getLogger();

    public void push(E o) {
        log.info("se esta anadiendo algo a la pila");
        this.insertar(o);

    }

    public E pop() throws PilaVaciaException {
        log.info("se esta sacando el ultimo elemento de la pila");
        if (inicio == null) {
            throw new PilaVaciaException();
        }

        E resultado = inicio.getContenido();
        eliminar(0);
        return resultado;
    }

    public E peek() throws PilaVaciaException {
        log.info("se esta viendo el ultimo elemento de la pila ");
        if (inicio == null) {
            throw new PilaVaciaException();
        }

        E resultado = inicio.getContenido();
        return resultado;
    }

    public int getTamaño() {
        return this.getTamano();
    }
}
