
package excepciones;

/**
 *
 * @author BOR
 */
public class EfectivoInsuficienteException extends NoSePuedeException {
    
    public EfectivoInsuficienteException() {
        super.mensaje = "El cajero no tiene suficiente dinero";  
    }
    
}
