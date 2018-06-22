
package excepciones;

/**
 *
 * @author BOR
 */
public class NoMultiploVeinteException extends NoSePuedeException{

    public NoMultiploVeinteException() {
        super.mensaje = "El cajero solo maneja billetes de 20, elija un monto multiplo de 20";  
    }
}
