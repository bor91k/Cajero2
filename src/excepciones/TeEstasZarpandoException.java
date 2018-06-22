
package excepciones;

/**
 *
 * @author BOR
 */
public class TeEstasZarpandoException extends NoSePuedeException{

    public TeEstasZarpandoException() {
        this.mensaje = "Estas excediendo la cantidad que soporta nuestro sistema";
    }
}
